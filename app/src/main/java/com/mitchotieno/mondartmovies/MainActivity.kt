package com.mitchotieno.mondartmovies

import android.annotation.SuppressLint
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

    var myStr: String? = null
    var titleList: ArrayList<String> = ArrayList<String>()
    var yearList: ArrayList<String> = ArrayList<String>()
    var imgList: ArrayList<String> = ArrayList<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRec: RecyclerView = findViewById(R.id.myRecycle)
        myRec.layoutManager = LinearLayoutManager(this)

        val myScope = CoroutineScope(Dispatchers.IO)

        myScope.launch {
            //var myStr = retrieveData("https://jsonplaceholder.typicode.com/todos/1")
            myStr = retrieveData("https://mitch-codes.github.io/movie.json")
            Log.i("result is", myStr.toString())
        }

        var myJson = JSONObject(myStr)
        val myJsonArray = myJson.get("reviews") as JSONArray
        if (myJsonArray != null) {

            for (k in 0..4) {
                val myMov: JSONObject = myJsonArray[k] as JSONObject
                titleList.add(myMov.get("name").toString())
                yearList.add(myMov.get("type").toString())
                imgList.add(myMov.get("backdrop1").toString())

            }
        }
        else {
            Toast.makeText(applicationContext, "failed", Toast.LENGTH_LONG).show()
        }
        
        val data = ArrayList<Item>()
        for (i in 0 until titleList.size) {
            data.add(Item(R.drawable.ic_launcher_foreground,titleList[i],yearList[i]))
        }
        val adapter = Adapter(data)
        myRec.adapter = adapter

    }
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
}
