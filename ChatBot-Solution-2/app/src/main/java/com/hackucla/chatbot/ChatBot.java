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

        // Initialize the chatbot
        initializeChatbot();

        // Load references to the UI elements
        botChatText = (EditText) findViewById(R.id.chatText);
        botResponseText = (TextView) findViewById(R.id.chatResponse);
        submitButton = (Button) findViewById(R.id.submit);

        // When we click the "Send Chat" button, this is the action we
        // want to execute. The important part is the body of the onClick
        // method.
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is the function call that actually attempts to send
                // the chat message.
                sendChat(botChatText.getText().toString());
            }
        });
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatBotService = retrofit.create(ChatBotHTTP.class);

        // generate a random 32-character string
        chatBotId = (new BigInteger(160, new Random())).toString(32);
        Log.i("Conversation ID", chatBotId);
    }

    /**
     * This method actually sends a message to the chat bot via the established service
     * and updates the UI with the chat bot's response.
     * @param chatMessage The message to send the chat bot
     */
    private void sendChat(String chatMessage) {
        // Check to make sure we have a conversation ID and have initialized the chatBotService
        // Check to make sure that we're not sending an empty message
        if (chatBotService == null || chatBotId == null || chatMessage.trim().length() == 0)
            return;

        // Set the response text while we wait for the API call to return
        botResponseText.setText("Thinking...");

        // Create the API call
        Call<ChatBotResponse> call = chatBotService.askChatBot(new ChatBotRequest(CHAT_BOT_NUM, CHAT_BOT_FORMAT, chatBotId, chatMessage).toMap());

        // Asynchronously handle the response
        call.enqueue(new Callback<ChatBotResponse>() {
            @Override
            public void onResponse(Call<ChatBotResponse> call, Response<ChatBotResponse> response) {
                // update the UI with the chatbot's response
                botResponseText.setText(response.body().botsay);
            }

            @Override
            public void onFailure(Call<ChatBotResponse> call, Throwable t) {
                // If there was an error, update the UI with a message
                botResponseText.setText("Whoops! There was an error: " + t.toString());
            }
        });
    }
}
