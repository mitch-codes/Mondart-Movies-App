package com.mitchotieno.mondartmovies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MovieActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie)

        var intent: Intent = intent
        var myMovTitle = intent.getStringExtra("myMovTitle")
        var myMovImg = intent.getStringExtra("myMovImg")
        var myMovDesc = intent.getStringExtra("myMovDesc")
        Log.i("MOVIE TITLE", myMovTitle.toString())

        supportActionBar?.title = "$myMovTitle - Mondart Movies"
        //supportActionBar?.setLogo(R.drawable.icon)

        var htmlString: String = "<html>\n" +
                "<head>\n" +
                "    <title>movs</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: sans-serif;\n" +
                "            font-size: 18px;\n" +
                "            background-image: linear-gradient(rgba(255, 255, 255, 0.8), rgba(255, 255, 255, 0.8)), url(https://images.pexels.com/photos/479453/pexels-photo-479453.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1);\n" +
                "            background-position: center;\n" +
                "            background-size: cover;\n" +
                "            background-repeat: no-repeat;\n" +
                "        }\n" +
                "        img {\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "        h3 {\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .buffer {\n" +
                "            padding-top: 20px;\n" +
                "            display: table;\n" +
                "            width: 90%;\n" +
                "            margin: 15px auto;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\"$myMovImg\">\n" +
                "    <!--additional cover-->\n" +
                "    <div class =\"buffer\">\n" +
                "$myMovDesc"+
                "</div>\n" +
                "</body>\n" +
                "</html>"

        val movWeb: WebView = findViewById(R.id.movWebView)
        movWeb.settings.javaScriptEnabled = true
        movWeb.loadData(htmlString,"text/html", "UTF-8")


    }
}
