package com.mitchotieno.mondartmovies

import android.annotation.SuppressLint
import android.os.Bundle
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

        var htmlString: String = "<html>\n" +
                "<head>\n" +
                "    <title>movs</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            font-family: sans-serif;\n" +
                "            font-size: 18px;\n" +
                "            background: url(https://images.pexels.com/photos/479453/pexels-photo-479453.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1);\n" +
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
                "        p {\n" +
                "            width: 90%;\n" +
                "            margin: 15px auto;\n" +
                "            text-align: justify;\n" +
                "        }\n" +
                "        .buffer {\n" +
                "            padding-top: 20px;\n" +
                "            display: table;\n" +
                "            background: rgba(255, 255, 255, 0.8);\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <img src=\"https://image.tmdb.org/t/p/original/eU3pww92aHBTBPRIBMGRS1KceEm.jpg\">\n" +
                "    <!--additional cover-->\n" +
                "    <div class =\"buffer\">\n" +
                "<h3>Avatar: The Last Airbender (2024) - A Live-Action Journey Filled with Potential and Pitfalls</h3> \n" +
                "<p>\n" +
                "    Netflix's 2024 live-action adaptation of Avatar: The Last Airbender \n" +
                "    was a highly anticipated project, aiming to capture the magic of the beloved \n" +
                "    animated series for a new generation. While it succeeds in some aspects, \n" +
                "    the show ultimately stumbles in others, leaving a mixed impression.\n" +
                "</p> \n" +
                "<p>\n" +
                "    <b>Story:</b> \n" +
                "    The core narrative of Aang, Katara, and Sokka's journey remains faithful to the original. \n" +
                "    However, the pacing feels uneven at times, with certain parts rushing through key moments and others \n" +
                "    dragging. Some creative liberties are taken, with some plot points feeling underdeveloped or \n" +
                "    lacking the emotional impact of the source material.\n" +
                "</p> \n" +
                "<p>\n" +
                "    <b>Characters:</b>\n" +
                "    The young cast breathes life into the iconic characters, with particular praise going to Katara and \n" +
                "    Sokka's performances. However, Aang, the central protagonist, occasionally struggles to capture the \n" +
                "    same playful charm and emotional depth as his animated counterpart. The supporting characters, \n" +
                "    while present, don't receive the same level of development, leaving them feeling slightly one-dimensional.\n" +
                "</p> \n" +
                "<p>\n" +
                "    <b>Visual Effects:</b> \n" +
                "    The series boasts impressive visuals, particularly in showcasing the bending styles. \n" +
                "    The vast landscapes and fantastical creatures are brought to life with a blend of practical \n" +
                "    effects and CGI. However, some CGI elements, especially in earlier episodes, appear less polished, \n" +
                "    detracting from the overall immersion.</p> <p><b>Acting:</b> The young actors deliver sincere \n" +
                "    performances, capturing the camaraderie and growth of their characters. \n" +
                "    However, the occasionally clunky dialogue and pacing can hinder their ability \n" +
                "    to fully display their range. The more seasoned actors bring gravitas to their roles, \n" +
                "    with Ozai, the main antagonist, delivering a particularly chilling performance.\n" +
                "</p> \n" +
                "<p>\n" +
                "    <b>Overall: </b>\n" +
                "    Avatar: The Last Airbender (2024) is a valiant attempt to translate a beloved animated series \n" +
                "    into a live-action format. While it succeeds in replicating the core story and world-building, \n" +
                "    its execution is uneven, with some elements falling short of the original's magic. The show remains \n" +
                "    an enjoyable watch for newcomers to the world of Avatar, but dedicated fans might find it lacking \n" +
                "    the emotional resonance and charm of the animated classic.\n" +
                "</p>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>"

        val movWeb: WebView = findViewById(R.id.movWebView)
        movWeb.settings.javaScriptEnabled = true
        movWeb.loadData(htmlString,"text/html", "UTF-8")


    }
}
