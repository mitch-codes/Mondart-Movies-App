package com.mitchotieno.mondartmovies

// DataRepository.kt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class DataRepository {
    // Replace with your actual JSON URL
    private val JSON_URL = "https://mitch-codes.github.io/movie.json"

    suspend fun getArrayLengthFromRemote(): Int = withContext(Dispatchers.IO) {
        try {
            val jsonString = URL(JSON_URL).readText()
            // Assuming the JSON response is a direct array: e.g., [{"id":1}, {"id":2}]
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("reviews")
            //val jsonArray = JSONArray(jsonString)
            return@withContext jsonArray.length()
        } catch (e: Exception) {
            e.printStackTrace()
            // Return 0 or handle error appropriately
            return@withContext 0
        }
    }
}