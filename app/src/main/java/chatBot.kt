package com.example.firebase_register

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase_register.R
import com.example.firebase_register.data.Message
import com.example.firebase_register.nono
import com.example.firebase_register.utils.Constants.RECEIVE_ID
import com.example.firebase_register.utils.Constants.SEND_ID
import com.example.firebase_register.utils.BotResponse
import com.example.firebase_register.utils.Constants.OPEN_ASSIGNMENT
import com.example.firebase_register.utils.Constants.OPEN_EXAM_FORM
import com.example.firebase_register.utils.Constants.OPEN_GOOGLE
import com.example.firebase_register.utils.Constants.OPEN_RESULT
import com.example.firebase_register.utils.Constants.OPEN_SEARCH
import com.example.firebase_register.utils.Time
import kotlinx.android.synthetic.main.activity_chat_bot.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class chatBot : AppCompatActivity() {
    private val TAG = "MainActivity"

    //You can ignore this messageList if you're coming from the tutorial,
    // it was used only for my personal debugging
    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Sanyukta", "Isha", "Omkar","Ayush","Amir","Sanskriti")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        recyclerView()

        clickEvents()

        val random = (0..5).random()
        customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")
    }


    private fun clickEvents() {

        //Send a message
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }

                    OPEN_EXAM_FORM -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://sim.unipune.ac.in/SIM_APP/")
                        startActivity(site)
                    }

                    OPEN_RESULT -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("http://results.unipune.ac.in/MCOM2013_Credit.aspx?Course_Code=70219&Course_Name=SE%202019%20CREDIT%20PATTERN%20EXAM%20PERIOD%20OCT%202020")
                        startActivity(site)
                    }

                    OPEN_ASSIGNMENT -> {

                    }



                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    fun assignment_btn(view: View) {
        var intent = Intent(this,newMod::class.java)
        startActivity(intent)
    }
}