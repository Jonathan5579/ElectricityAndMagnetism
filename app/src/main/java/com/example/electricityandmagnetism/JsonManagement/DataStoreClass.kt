package com.example.electricityandmagnetism.JsonManagement

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class JsonFundamentalCard(
    val data: List<FundamentalsContent>? = null
)

data class FundamentalsContent(
    val id: Int? = null,
    val title: String = "",
    val content: String = "",
    //val index: String = ""
    @SerializedName("itemOrdering") val index: Int = -1
)

fun formatJsonToFundamentalsClass(jsonData: String): JsonFundamentalCard {
    //initialize a new gson object, to format the data to te class
    val gson = Gson()
    val WorkerObjectType = object : TypeToken<JsonFundamentalCard>() {}.type
    //Reads the info and puts it into the class you've predefined
    return gson.fromJson(jsonData, WorkerObjectType)
}



/**Json Example
 * {
 *    "data":[
 *       {
 *          "content":"Proton+ and electron-, when close, feel a force in the radial ( r̂ ) direction, \nF ∝ qq/r²",
 *          "id":0,
 *          "title":"Electric charges ( q )"
 *       },
 *       {
 *          "content":"A charge produces a field that fills all space. If a test charge q is placed at a certain point P, E is the force per unit charge at that given point, \nE ∝ F/q",
 *          "id":1,
 *          "title":"Electric Field ( E )"
 *       }
 *    ]
 *  }
 * */