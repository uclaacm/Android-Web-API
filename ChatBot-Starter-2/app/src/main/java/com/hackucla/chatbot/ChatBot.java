package com.hackucla.chatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBot extends AppCompatActivity {
    /**
     * These are parameters for the API call.
     * API_BASEURL should not be changed
     * CHAT_BOT_NUM is the chatbot we want (see the docs for other chatbots)
     * CHAT_BOT_FORMAT is what format of response we want (JSON)
     */
    private static final String API_BASEURL = "http://api.program-o.com/v2/";
    private static final int    CHAT_BOT_NUM = 12;
    private static final String CHAT_BOT_FORMAT = "json";

    /**
     * To use retrofit, we defined an interface ChatBotHTTP, which is turned
     * into a class by retrofit. chatBotService is how we send/receive data
     * to/from the chatbot API.
     *
     * chatBotId is a unique conversation identifier. The chatbot service we use the context in
     * a conversation to make responses, so for one "chat session" we want a unique ID. This will be
     * generated in the initializeChatbot() method and this ID needs to be sent in every request
     * to the chat bot.
     */
    private ChatBotHTTP chatBotService = null;
    private String chatBotId = null;

    /**
     * This represent the UI elements.
     * They are instantiated later.
     *
     * submitButton: the button
     * botChatText: the text entered by the user
     * botResponseText: the text received from the bot API
     */
    private Button submitButton;
    private EditText botChatText;
    private TextView botResponseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);


        // TODO: Initialize the chatbot


        // TODO: Load references to the UI elements




        // TODO: implement onClickListener for submit button







    }

    /**
     * This function initializes the chat bot. It does several important things:
     *   1. Use Retrofit to turn the ChatBotHTTP interface into an actual service
     *      that we can use to interact with the API
     *   2. Registers the API_BASEURL (the path to prepend in all API calls)
     *   3. Loads our JSON interpreter
     *   4. Generates a conversation ID to send in the API request
     */
    private void initializeChatbot() {
        // TODO: create a retrofit instance and the chatBotService





        // TODO: generate a random 32-character string as the conversation ID

    }

    /**
     * This method actually sends a message to the chat bot via the established service
     * and updates the UI with the chat bot's response.
     * @param chatMessage The message to send the chat bot
     */
    private void sendChat(String chatMessage) {
        // TODO: add some bounds checking (chatBot != null, chatMessage.length() > 0)




        // TODO: Set the response text to "Thinking..." while we wait for the API call to return


        // TODO: Create the API call for GET /chatbot
        //       Make sure to call .toMap() on the ChatBotRequest you create


        // TODO: Asynchronously handle the response










    }
}
