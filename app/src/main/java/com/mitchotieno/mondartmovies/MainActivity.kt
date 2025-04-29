package com.mitchotieno.mondartmovies

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {

    var titleList: ArrayList<String> = ArrayList<String>()
    var yearList: ArrayList<String> = ArrayList<String>()
    var imgList: ArrayList<String> = ArrayList<String>()
    var myStr: String? = "initial"
    var finalStr: String? = null
    lateinit var myRec: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myScope = CoroutineScope(Dispatchers.IO)

        myScope.launch {
            myStr = retrieveData("https://mitch-codes.github.io/movie.json")
            createRec(myStr.toString())
        }

        Thread.sleep(7000)
        Log.i("RESULT HOPE", finalStr.toString())
        myRec = findViewById(R.id.myRecycle)
        myRec.layoutManager = LinearLayoutManager(this)
        var myJson = JSONObject(finalStr)
        var myJsonArray: JSONArray = myJson.get("reviews") as JSONArray
        println("BELIEVE IT" + myJsonArray::class.simpleName)

        for (k in 0..2) {
            var indArray: JSONObject = myJsonArray[k] as JSONObject
            var backdrop = indArray.get("backdrop1")
            println("think of the children" + backdrop + backdrop::class.simpleName)
        }
/**
        try {
            for (k in 0..4) {
                var myMov = myJsonArray.getJSONObject(k)
                titleList.add(myMov.get("name").toString())
                yearList.add(myMov.get("type").toString())
                imgList.add(myMov.get("backdrop1").toString())

            }
        }
        catch(e: Exception) {
            Log.e("JSON FAILED","couldn't find valid JSON")
            e.printStackTrace()
        }
        **/


        val data = ArrayList<Item>()
        for (i in 0..4) {
            data.add(Item(R.drawable.ic_launcher_foreground,"titleList[i]","yearList[i]"))
        }
        val adapter = Adapter(data)
        myRec.adapter = adapter

    }


    //suspend fun retrieveData(urlString: String): String = withContext(Dispatchers.IO){
    suspend fun retrieveData(urlString: String): String = withContext(Dispatchers.IO){
        var connection: HttpURLConnection? = null

        try {
            val url = URL(urlString)
            connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw IOException("HTTP ERROR: $responseCode")
            }
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()
            var line: String?

            while(reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            return@withContext response.toString()
        }
        catch(e: IOException){
            Log.e("http error", "error: $e")
            throw e
        }
        finally {
            connection?.disconnect()
        }

    }

    fun createRec(myStr2: String) {
        //var myJson = JSONObject(myStr2)
        //var myJsonArray = myJson.get("reviews")

        Log.i("Hope", myStr2)
        finalStr = myStr2


            /**for (k in 0..4) {
                val myMov = myJsonArray.get(k) as JSONObject
                titleList.add(myMov.get("name").toString())
                yearList.add(myMov.get("type").toString())
                imgList.add(myMov.get("backdrop1").toString())

            }**/

    }
}
