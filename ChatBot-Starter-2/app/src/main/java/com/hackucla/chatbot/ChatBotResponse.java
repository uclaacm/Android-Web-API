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
     * TODO: add these fields
     * convo_id
     * usersay
     * botsay
     */
    public String convo_id;
    public String usersay;
    public String botsay;

    /**
     * TODO: write this constructor
     *
     * Note: We don't actually have to call this constructor. When retrofit gets a response, it calls
     * Gson, which will call this constructor and convert the response JSON object to this Java object.
     * The only requirement is that this class correctly maps the JSON fields to properties here.
     *
     * @param convo_id  The ID of the conversation we are currently having with the bot
     * @param usersay  The message we send to the chat bot
     * @param botsay  The chat bot's response to our query
     */





}
