package com.example.electricityandmagnetism

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.electricityandmagnetism.JsonManagement.FundamentalsContent
import com.example.electricityandmagnetism.JsonManagement.JsonFundamentalCard
import com.example.electricityandmagnetism.JsonManagement.formatJsonToFundamentalsClass
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class FundamentalCard(
    val id: Int,
    val title: String,
    val imageId: Int = -1,
    var content: String,
    val topic: String = "Fundamentals"
)


data class AppUiState(
    /** Fundamentals screen */
    val fundamentalsText: String = "Lecture text",
    val fundamentalCards: MutableList<FundamentalCard> = mutableListOf(),
    val valForUiUpdate: Int = 0,

    val anyTextHasChanged: Boolean = false,

    val collectedJsonString: String = "",
    val JsonFumdanemtalsCards: JsonFundamentalCard? = null
)

class AppViewModel: ViewModel() {

    private val _appState = MutableStateFlow(AppUiState())
    val appState: StateFlow<AppUiState> = _appState.asStateFlow()


    fun buildCardElementsFromSavedDataStore(jsonString: String){

        if (jsonString == "") return //In case there is nothing in the datastore it'll use the fundamentalCard that are in the init section
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
                    content = card.content
                )
            )
        }

        _appState.update { currentState ->
            currentState.copy(fundamentalCards = fundamentalCards)
        }
    }


    init{
        /**Create the fundamentals element list*/
        val fundamentalCards: MutableList<FundamentalCard> = mutableListOf()

        var cardId = 0

        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Electric charges ( q )",
                imageId = RDrawableElectricCharges,
                content = "Proton+ and electron-, when close, feel a force in the radial ( r̂ ) direction, \nF ∝ qq/r²"
            )
        )

        cardId += 1
        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Electric Field ( E )",
                imageId = RDrawableElectricField,
                content = "A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point, \nE ∝ F/q"
            )
        )

        cardId += 1
        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Electrostatic Potential Energy ( µ )",
                imageId = RDrawableElectrostaticPotentialEnergyIcon,
                content = "Start with a charge, then bring another charge from infinity to a distance r. µ is the energy that took to assemble that charge together. " +
                        "\nµ ∝ q/r" +
                        "\nGenerally µ = ∫F • dr̂" +
                        "\nElectric field are conservative, which means that the energy to assemble charges is path independent, calculate it tipically from the straight line that join the charges" //Dot Symbols • ◦ • ‣ ⁃
            )
        )

        cardId += 1
        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Electric Potential ( V )",
                imageId = RDrawableElectricPotentialIcon,
                content = "Amount of energy to approach a charge at a distance r per unit charge " +
                        "\nV ∝ q/r" +
                        "\nGenerally V = ∫(F/q) • dr̂ = ∫E • dr̂"
            )
        )

        cardId += 1
        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Equipotential surfaces",
                imageId = RDrawableEquipotentialSurfacesIcon,
                content = "for a given charge assembly in space, they produce surfaces that are at the same potential. Equipotential surfaces cannot intersect, that is a violation to conservation of energy." +
                        "\nCharges can move alogn these surfaces without requiring energy."
            )
        )

        cardId += 1
        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Electric Field and equipotential surfaces",
                imageId = RDrawableElectricFieldAndEquipotential,
                content = "Electric field lines will always go trough equipotential surfaces at a perpendicular angle. If not its a contradictory to conservation of energy." +
                        "\nIn cartesian coordinates they're related by the equation " +
                        "\nE = -∇V" +
                        "\nWith spherical coordinates is simply minus the derivative" +
                        "\nBy the definition of the gradient (points in the direction of maximum increse of function V), you can see that the electric field point towards the value of minor potential"
            )
        )

        cardId += 1
        fundamentalCards.add(
            FundamentalCard(
                id = cardId,
                title = "Distribution of charge in a conductor",
                imageId = RDrawableDistributionOfCharges,
                content = "The surface is an equipotential, but the distribution of charges will only be uniformly distributed if the conductor is a sphere, for any other shape the surface charge density will be higher where the curvature is also high\n" +
                        "\nσ ∝ curvature"
            )
        )

        _appState.update { currentState ->
            currentState.copy(fundamentalCards = fundamentalCards)
        }
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
        _appState.update { cS -> cS.copy(anyTextHasChanged=false) }
    }

    fun collectCardText(): String{
        val content: MutableList<FundamentalsContent> = mutableListOf()

        for (card in appState.value.fundamentalCards){
            content.add(
                FundamentalsContent(
                    id = card.id,
                    title = card.title,
                    content = card.content
                )
            )
        }

        val fundamentalCard = JsonFundamentalCard(data = content)

        val gson = Gson()
        val jsonString = gson.toJson(fundamentalCard)

        Log.i("CollectText", "Json: $jsonString")
        _appState.update { cS-> cS.copy(collectedJsonString= jsonString) }
        return jsonString
    }

    fun jsonStringToCardClass(){
        if (appState.value.collectedJsonString == "") return
        val fundamentals = formatJsonToFundamentalsClass(appState.value.collectedJsonString)

        _appState.update { cS ->
            cS.copy(JsonFumdanemtalsCards = fundamentals)
        }
    }
}

