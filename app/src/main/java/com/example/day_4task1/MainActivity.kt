package com.example.day_4task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputLayout1=findViewById<TextInputLayout>(R.id.textInputLayout).editText?.text
        val textInputLayout2=findViewById<TextInputLayout>(R.id.countryName).editText?.text
        val button1=findViewById<Button>(R.id.button)
        val textView=findViewById<TextView>(R.id.data)

        button1.setOnClickListener()
        {
            var count=0
            val myApplication=application as MyAppilication
            val httpApiService=myApplication.httpApiService

            CoroutineScope(Dispatchers.IO).launch {
                val decodedSolution=httpApiService.getMyBook()
                withContext(Dispatchers.Main)
                {
                    val myBuilder=StringBuilder()
                    for(myData in decodedSolution){
                        if(count<4) {
                            if (textInputLayout1.toString() == myData.author && textInputLayout2.toString() == myData.country) {
                                myBuilder.append("Result : "+myData.title)
                                myBuilder.append("\n")
                                count++
                            }

                        }

                    }
                    textView.text= "Results : "+count+"\n$myBuilder"

                }
            }
        }
    }
}