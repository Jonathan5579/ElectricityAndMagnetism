package com.example.electricityandmagnetism.JsonManagement

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class JsonFundamentalCard(
    val data: List<FundamentalsContent>? = null
)

data class FundamentalsContent(
    val id: Int? = null,
    val title: String = "",
    val content: String = ""
)

fun formatJsonToFundamentalsClass(jsonData: String): JsonFundamentalCard {
    //initialize a new gson object, to format the data to te class
    val gson = Gson()
    val WorkerObjectType = object : TypeToken<JsonFundamentalCard>() {}.type
    //Reads the info and puts it into the class you've predefined
    return gson.fromJson(jsonData, WorkerObjectType)
}