package com.hackucla.chatbot;

/**
 * Created by nkansal on 2/25/17.
 */

/**
 * This class models a request sent to the chat bot. Gson will convert this to JSON for us when we
 * send a request. Fields set to null will not be sent unless specified.
 */
public class ChatBotRequest {
    /**
     * TODO: add these fields
     * user
     * key
     * nick
     * text
     */

    /**
     * TODO: write this constructor
     * Use this constructor if we're creating a chat bot. The nickname will be generated by
     * the API and provided in the ChatBotInstance.
     *
     * @param user   pass API_USER to this parameter
     * @param key    pass API_KEY to this parameter
     */




    /**
     * TODO: write this constructor
     * Use this constructor if we're sending a message to an existing chat bot, identified with
     * the "nick" field. The response from the chat bot is serialized in a ChatBotResponse.
     *
     * @param user  pass API_USER to this parameter
     * @param key   pass API_KEY to this parameter
     * @param nick  The nickname of the chat bot to send the message to
     * @param text  The message to send to the chat bot
     */






}
