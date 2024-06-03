package com.example.electricityandmagnetism

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.example.electricityandmagnetism.JsonManagement.FundamentalsContent
import com.example.electricityandmagnetism.JsonManagement.JsonFundamentalCard
import com.example.electricityandmagnetism.JsonManagement.formatJsonToFundamentalsClass
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random


data class FundamentalCard(
    val id: Int,
    var title: String,
    val imageId: Int = -1,
    var content: String = "",
    val topic: String = "Fundamentals",
    var index: Int = -1,
    val contentComposable: @Composable () -> Unit = {  }
    //var index: String = "-1"
)


data class AppUiState(

    /**Home card image volado*/
    val volado: Int = Random.nextInt(0, 2), //valores de 0 o 1

    /** Fundamentals screen */
    val fundamentalsText: String = "Lecture text",
    val fundamentalCards: MutableList<FundamentalCard> = mutableListOf(),
    val valForUiUpdate: Int = 0,

    /** Variables to show and update fundamentals cards*/
    val anyTextHasChanged: Boolean = false,
    val collectedJsonString: String = "",
    val JsonFumdanemtalsCards: JsonFundamentalCard? = null,
    val showArrowButtons: Boolean = false,

    /**variables to add cards*/
    val addContentButtonVisibility: Boolean = false,

    /**Raw json, I use this variables to move content from one app to the other*/
    val rawJson: String = DEFAULT_RAW_JSON,
    val showRawJson: Boolean = false,

    val yoffset: Double = 0.0,
    val xoffset: Double = 0.0
)

class AppViewModel: ViewModel() {

    private val _appState = MutableStateFlow(AppUiState())
    val appState: StateFlow<AppUiState> = _appState.asStateFlow()


    fun updateXY(x: Float, y: Float){
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        val roundx =  df.format(x).toDouble()
        val roundy =  df.format(y).toDouble()

        _appState.update { cS->cS.copy(
            xoffset = roundx,
            yoffset = roundy
        ) }
    }

    fun buildCardElementsFromSavedDataStore(jsonString: String){

        if (jsonString == "") return //In case there is nothing in the datastore it'll use the fundamentalCard that are in the init section
        //**If theres nothing in datastore you could use this string as a backup from 01 November 2023:
        // {"data":[{"content":"Proton+, electron-. When close, feel a force in the radial ( r̂ ) direction.\nF ∝ qq/r²","id":0,"title":"Electric charges ( q )"},{"content":"A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point.\nE ∝ F/q","id":1,"title":"Electric Field ( E )"},{"content":"Start with a charge, then bring another charge from infinity to a distance r, for that you\u0027ll have to overcome the electric force. µ is the energy that took to assemble that charge together. \nµ ∝ q/r\nGenerally µ \u003d ∫F • dr̂\nElectric fields are conservative, which means that the energy to assemble charges is path independent, calculate it tipically from the straight line that join the charges.","id":2,"title":"Electrostatic Potential Energy ( µ )"},{"content":"Amount of energy to approach a charge at a distance r per unit charge, essentially µ/q where q is the new charge.\nV ∝ q/r\nGenerally V \u003d ∫(F/q) • dr̂ \u003d ∫E • dr̂","id":3,"title":"Electric Potential ( V )"},{"content":"For a given charge assembly in space, they produce surfaces that are at the same potential. Equipotential surfaces cannot intersect, that is a violation to conservation of energy.\nCharges can move alogn these surfaces without doing work.","id":4,"title":"Equipotential surfaces"},{"content":"Electric field lines will always go trough equipotential surfaces at a perpendicular angle. If not, its a contradiction to conservation of energy.\nIn cartesian coordinates they\u0027re related by the equation \nE \u003d -∇V\nWith spherical coordinates is simply minus the derivative of the electric potential.\nBy the definition of the gradient (points in the direction of maximum increse of the scalar function), you can see that the electric field points towards the value of minor potential","id":5,"title":"Electric Field and equipotential surfaces"},{"content":"The surface is an equipotential, but the distribution of charges will only be uniformly distributed if the conductor is a sphere, for any other shape the surface charge density will be higher where the curvature is also high.\n\nσ ∝ curvature\nSurface charge density.","id":6,"title":"Distribution of charge in a conductor"}]}*/
        val fundamentals = formatJsonToFundamentalsClass(jsonString)

        /**Create the fundamentals element list*/
        val fundamentalCards: MutableList<FundamentalCard> = mutableListOf()

        for (card in fundamentals.data!!){

            if (card.id == null ) continue
            fundamentalCards.add(
                FundamentalCard(
                    id = card.id,
                    title = card.title,
                    imageId = card.id, // I use this numbers to find its drawable,  see the constant values to see its Int equivalent
                    content = card.content,
                    index = card.index
                )
            )
        }

        _appState.update { currentState ->
            currentState.copy(
                fundamentalCards = fundamentalCards,
                rawJson = jsonString
            )
        }
    }


    init{
        val fundamentalCards = buildDefaultFundamentals()
        _appState.update { currentState ->
            currentState.copy(fundamentalCards = fundamentalCards)
        }
    }

    fun updateFundamentalsTitleTextField(
        cardId: Int,
        title: String
    ){

        val fundamentalCards = appState.value.fundamentalCards

        fundamentalCards[cardId].title = title

        _appState.update { currentState ->
            currentState.copy(
                anyTextHasChanged = true,
                fundamentalCards = fundamentalCards,
                valForUiUpdate = appState.value.valForUiUpdate+1
            )
        }
    }
/*
    fun updateFundamentalItemOrdering(
        cardId: Int,
        itemOrdering: String
    ) {
/*
        val fundamentalCards = appState.value.fundamentalCards
        fundamentalCards[cardId]
        fundamentalCards[cardId].itemOrdering = itemOrdering
*/

        Log.i("itemOrdering", "trying to update card with id $cardId, that is: $")
        val fundamentalCards = appState.value.fundamentalCards
        for (card in fundamentalCards){
            if (card.id == cardId){
                Log.i("itemOrdering", "updating card: $card")
                card.index = itemOrdering
                //fundamentalCards[cardId].itemOrdering = itemOrdering
                break
            }
        }

        _appState.update { currentState ->
            currentState.copy(
                anyTextHasChanged = true,
                fundamentalCards = fundamentalCards,
                valForUiUpdate = appState.value.valForUiUpdate+1
            )
        }
    }
*/
    fun sortFundamentalCardsByItemOrdering(
    ){
        val fundamentalCards = appState.value.fundamentalCards
        val sortedFundamentalCards = mutableListOf<FundamentalCard>()
        var continuePrintng = 0
        var cardOrdering = 0

        val elementsToPrint = fundamentalCards.size-1


        while (continuePrintng < 100){ //en principio el break debe´ri a de sacarlo siempre pero por si acaso le pongo es alimitación numerica
            for (card in fundamentalCards){

                if (card.index.toInt() != cardOrdering) continue

                sortedFundamentalCards.add( card )
                cardOrdering++
            }
            if (cardOrdering > elementsToPrint) break

            continuePrintng++
        }

        _appState.update { currentState ->
            currentState.copy(
                anyTextHasChanged = true,
                fundamentalCards = sortedFundamentalCards,
                valForUiUpdate = appState.value.valForUiUpdate+1
            )
        }
    }

    /*
    fun sortFundamentalByChanging1Value(
        /**Estos son de la tarjeta que solicitó el cambio*/
        cardId: Int,
        cardIndex: String,
        desiredIndex: String
    ){
        var cardIndexNumber: Int
        var desiredIndexNumber: Int
        try {
            desiredIndexNumber = desiredIndex.toInt()
            cardIndexNumber = cardIndex.toInt() //esto en principio ni debería de elevar uan excepvción
        }catch(e: Exception) {
            /**Eto significa que el valor no es un número*/
            Log.i("itemOrdering", "exception at sortFundamentalByChanging1Value $e")
            return
        }

        if cardId

        val fundamentalCards = appState.value.fundamentalCards

        fundamentalCards[cardIndex.toInt].

        if (cardIndex < desiredIndex)
    }
    */

    fun moveCardUp(
        /**Estos son de la tarjeta que solicitó el cambio*/
        cardIndex: Int,
    ) {
        val fundamentalCards = appState.value.fundamentalCards
        val cardIndexInt =  cardIndex.toInt()

        val desiredIndex = cardIndexInt - 1

        if (desiredIndex < 0) return

        fundamentalCards[cardIndexInt].index = desiredIndex//.toString()
        fundamentalCards[desiredIndex].index = cardIndex

        sortFundamentalCardsByItemOrdering()
    }

    fun moveCardDown(
        /**Estos son de la tarjeta que solicitó el cambio*/
        cardIndex: Int,
    ) {
        val fundamentalCards = appState.value.fundamentalCards
        val cardIndexInt =  cardIndex.toInt()

        val desiredIndex = cardIndexInt + 1

        if (desiredIndex >= fundamentalCards.size) return

        fundamentalCards[cardIndexInt].index = desiredIndex//.toString()
        fundamentalCards[desiredIndex].index = cardIndex

        sortFundamentalCardsByItemOrdering()
    }

    fun updateShowArrowButtons(){
        _appState.update { cS->cS.copy(showArrowButtons = !appState.value.showArrowButtons) }
    }
    fun updateFundamentalsTextField(
        cardId: Int,
        textfieldText: String
    ){

        val fundamentalCards = appState.value.fundamentalCards

        fundamentalCards[cardId].content = textfieldText

        _appState.update { currentState ->
            currentState.copy(
                anyTextHasChanged = true,
                fundamentalCards = fundamentalCards,
                valForUiUpdate = appState.value.valForUiUpdate+1
            )
        }
    }

    fun resetanyTextHasChanged(){
        _appState.update { cS -> cS.copy(
            anyTextHasChanged = false,
            showArrowButtons = false
        ) }
    }

    fun collectCardText(): String{
        val content: MutableList<FundamentalsContent> = mutableListOf()

        for (card in appState.value.fundamentalCards){
            content.add(
                FundamentalsContent(
                    id = card.id,
                    title = card.title,
                    content = card.content,
                    index = card.index
                )
            )
        }

        val fundamentalCard = JsonFundamentalCard(data = content)

        val gson = Gson()
        val jsonString = gson.toJson(fundamentalCard)

        Log.i("CollectText", "Json: $jsonString")
        _appState.update { cS-> cS.copy(
            collectedJsonString = jsonString,
            rawJson = jsonString
            )
        }
        return jsonString
    }

    fun jsonStringToCardClass(){
        if (appState.value.collectedJsonString == "") return
        val fundamentals = formatJsonToFundamentalsClass(appState.value.collectedJsonString)

        _appState.update { cS ->
            cS.copy(JsonFumdanemtalsCards = fundamentals)
        }
    }

    fun updateRawJson(json: String){
        _appState.update { cS -> cS.copy(rawJson = json) }
    }

    fun showHideRawJson(){
        val showRawJson = appState.value.showRawJson
        _appState.update { cS -> cS.copy(showRawJson = !showRawJson) }
    }

    fun addFundamentalCard(){
        val listOfCards = appState.value.fundamentalCards

        listOfCards.add(
            FundamentalCard(
                id = listOfCards.size,
                title = "New card",
                imageId = listOfCards.size,
                content = "New card content",
                index = listOfCards.size
            )
        )

        _appState.update { cS-> cS.copy(
            fundamentalCards = listOfCards,
            anyTextHasChanged = true,
            valForUiUpdate = appState.value.valForUiUpdate+1
        ) }
    }

    fun removeCard(
        cardIndex: Int
    ){
        val fundamentalCard = appState.value.fundamentalCards

        fundamentalCard.removeAt(cardIndex)
        _appState.update { cS-> cS.copy(
            fundamentalCards = fundamentalCard,
            anyTextHasChanged = true,
            valForUiUpdate = appState.value.valForUiUpdate+1
            )
        }
    }
}

