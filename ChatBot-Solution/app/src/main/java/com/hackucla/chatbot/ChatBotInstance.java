package com.hackucla.chatbot;

/**
 * Created by nkansal on 2/25/17.
 */

/**
 * This class models a response from /create of the chat bot API. Gson will convert this to JSON
 * for us when we send a request. Fields set to null will not be sent unless specified.
 */
public class ChatBotInstance {
    /**
     * status: the response status from the API ("success" if successful, and an error message otherwise)
     * nick: the nickname of the chat bot created for us
     */
    public String status;
    public String nick;

    /**
     * Note: We don't actually have to call this constructor. When retrofit gets a response, it calls
     * Gson, which will call this constructor and convert the response JSON object to this Java object.
     *
     * @param status  The status returned from the server
     * @param nick    The nickname of the chat bot just created
     */
    public ChatBotInstance(String status, String nick) {
        this.status = status;
        this.nick = nick;
    }
}
