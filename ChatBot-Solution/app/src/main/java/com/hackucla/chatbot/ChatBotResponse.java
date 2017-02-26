package com.hackucla.chatbot;

/**
 * Created by nkansal on 2/25/17.
 */

/**
 * This class models a response from /ask of the chat bot API. Gson will convert this to JSON
 * for us when we send a request. Fields set to null will not be sent unless specified.
 */
public class ChatBotResponse {
    /**
     * status: the response status from the API ("success" if successful, and an error message otherwise)
     * response: the chat bot's response to our query
     */
    public String status;
    public String response;

    /**
     * Note: We don't actually have to call this constructor. When retrofit gets a response, it calls
     * Gson, which will call this constructor and convert the response JSON object to this Java object.
     *
     * @param status    The status returned from the server
     * @param response  The chat bot's response to our query
     */
    public ChatBotResponse(String status, String response) {
        this.status = status;
        this.response = response;
    }
}
