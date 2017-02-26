package com.hackucla.chatbot;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nkansal on 2/25/17.
 */

public interface ChatBotHTTP {
    /**
     * TODO: write this
     * This is a call to POST /create. It sends a ChatBotRequest (which consists of "user" and "key" fields)
     * and returns with a ChatBotInstance (which consists of a "status" and a "nick"). The "nick" field
     * is how we uniquely identify which chat bot we want to talk to in future requests
     *
     * @param request The parameters to send in the API call (see ChatBotResponse)
     * @return a ChatBotInstance, which contains the identifier for the chat bot (see ChatBotInstance)
     */



    /**
     * TODO: write this
     * This is a call to POST /ask. It sends a ChatBotRequest (which consists of "user", "key", "nick", and
     * "text" fields) and returns with a ChatBotResponse (which consists of "status" and "response"
     * fields). The "response" field is what the Chatbot replied to us with.
     *
     * @param request Contains the API Key, User, Chat bot nickname, and actual text to send to the chat bot
     *                (see ChatBotRequest)
     * @return a ChatBotResponse, which contains the chat bot's response (see ChatBotResponse)
     */


}
