package com.hackucla.chatbot;

/**
 * Created by nkansal on 2/25/17.
 */

/**
 * This class models a response from /chatbot of the chat bot API. Gson will convert JSON to this object
 * for us when we receive a response.
 */
public class ChatBotResponse {
    /**
     * convo_id: The ID of the conversation we are currently having with the bot
     * usersay: The message we sent to the user
     * botsay: The chat bot's response
     */
    public String convo_id;
    public String usersay;
    public String botsay;

    /**
     * Note: We don't actually have to call this constructor. When retrofit gets a response, it calls
     * Gson, which will call this constructor and convert the response JSON object to this Java object.
     * The only requirement is that this class correctly maps the JSON fields to properties here.
     *
     * @param convo_id  The ID of the conversation we are currently having with the bot
     * @param usersay  The message we send to the chat bot
     * @param botsay  The chat bot's response to our query
     */
    public ChatBotResponse(String convo_id, String usersay, String botsay) {
        this.convo_id = convo_id;
        this.usersay = usersay;
        this.botsay = botsay;
    }
}
