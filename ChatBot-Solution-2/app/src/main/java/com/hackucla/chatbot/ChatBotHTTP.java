package com.hackucla.chatbot;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by nkansal on 2/25/17.
 */

public interface ChatBotHTTP {
    /**
     * This is a call to GET /chatbot. It sends a Map<String, String> from a ChatBotRequest
     * and returns with a ChatBotResponse. To use this method, you need to create a ChatBotRequest
     * and pass in the object returned from calling the .toMap() method.
     *
     * @param request A Map<String, String> that is returned from a ChatBotRequest object using
     *                its .toMap() method. e.g. (new ChatBotRequest(...).toMap())
     * @return a ChatBotResponse, which contains the chat bot's response (see ChatBotResponse)
     */
    @GET("chatbot")
    Call<ChatBotResponse> askChatBot(@QueryMap Map<String,String> request);
}
