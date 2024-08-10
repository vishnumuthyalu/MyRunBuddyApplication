package com.example.myrunbuddyapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class chatBot_GeminiAPI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot_gemini_api)

        val userQuestion = findViewById<EditText>(R.id.message_box)
        val sendButton = findViewById<Button>(R.id.submit_button)
        val back_btn_one = findViewById<Button>(R.id.backButton2)
        val geminiResponse = findViewById<TextView>(R.id.gemini_response)
        val scrollView = findViewById<ScrollView>(R.id.scroll_view)

        sendButton.setOnClickListener {
            val prompt = userQuestion.text.toString()

            if (prompt.isNotBlank()) {
                val generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = "API-KEY"
                )

                lifecycleScope.launch {
                    try {
                        val response = withContext(Dispatchers.IO) {
                            generativeModel.generateContent(prompt)
                        }
                        geminiResponse.text = response.text
                        scrollView.post {
                            scrollView.fullScroll(ScrollView.FOCUS_UP)
                        }
                    } catch (e: Exception) {
                        geminiResponse.text = "Error: ${e.message}"
                        scrollView.post {
                            scrollView.fullScroll(ScrollView.FOCUS_UP)
                        }
                    }
                }
            } else {
                geminiResponse.text = "Please enter a question to Ask RunBuddy"
                scrollView.post {
                    scrollView.fullScroll(ScrollView.FOCUS_UP)
                }
            }
        }

        back_btn_one.setOnClickListener{
            finish()
        }
    }
}

