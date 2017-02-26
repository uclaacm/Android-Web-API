package com.hackucla.chatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBot extends AppCompatActivity {
    /**
     * These are parameters for the API call.
     * API_USER and API_KEY are found from the cleverbot.io user dashboard
     * API_BASEURL should not be changed
     */
    private static final String API_BASEURL = "https://cleverbot.io/1.0/";
    private static final String API_USER = "bLJ1ZZ9k1wrd6ObA";
    private static final String API_KEY = "mOLm97rXMzjoIOizrIzzvoU3Wz2t0XAd";

    /**
     * To use retrofit, we defined an interface ChatBotHTTP, which is turned
     * into a class by retrofit. chatBotService is how we send/receive data
     * to/from the chatbot API.
     *
     * chatBot is an instance of ChatBotInstance. This is the information we get
     * back from the API call when we create a chatbot. The important field is
     * chatBot.nick, which is a like a unique identifier for our chatbot.
     */
    private ChatBotHTTP chatBotService;
    private ChatBotInstance chatBot = null;

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
     *   4. Sends an API request to /create (via the created service) to get a unique
     *      identifier for a new chat bot and set the response object globally
     */
    private void initializeChatbot() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASEURL)                               // 2
                .addConverterFactory(GsonConverterFactory.create()) // 3
                .build();

        chatBotService = retrofit.create(ChatBotHTTP.class);        // 1

        // Create the API call
        Call<ChatBotInstance> call = chatBotService.createChatBot(new ChatBotRequest(API_USER, API_KEY));

        // Asynchronously handle the response/failure
        call.enqueue(new Callback<ChatBotInstance>() {
            @Override
            public void onResponse(Call<ChatBotInstance> call, Response<ChatBotInstance> response) {
                // on success, set the response to the global variable.
                // we use chatBot.nick to refer to the chatbot's ID
                chatBot = response.body();
            }

            @Override
            public void onFailure(Call<ChatBotInstance> call, Throwable t) {
                Log.e("API Error", t.toString());
            }
        });
    }

    /**
     * This method actually sends a message to the chat bot via the established service
     * and updates the UI with the chat bot's response.
     * @param chatMessage The message to send the chat bot
     */
    private void sendChat(String chatMessage) {
        // Check to make sure we've initialized a chat bot (the important thing is we have the .nick)
        // Check to make sure that we're not sending an empty message
        if (chatBot == null || chatMessage.trim().length() == 0)
            return;

        // Set the response text while we wait for the API call to return
        botResponseText.setText("Thinking...");

        // Create the API call
        Call<ChatBotResponse> call = chatBotService.askChatBot(new ChatBotRequest(API_USER, API_KEY, chatBot.nick, chatMessage));

        // Asynchronously handle the response
        call.enqueue(new Callback<ChatBotResponse>() {
            @Override
            public void onResponse(Call<ChatBotResponse> call, Response<ChatBotResponse> response) {
                // update the UI with the chatbot's response
                botResponseText.setText(response.body().response);
            }

            @Override
            public void onFailure(Call<ChatBotResponse> call, Throwable t) {
                // If there was an error, update the UI with a message
                botResponseText.setText("Whoops! There was an error: " + t.toString());
            }
        });
    }
}
