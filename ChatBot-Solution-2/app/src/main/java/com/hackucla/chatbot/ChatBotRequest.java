package com.hackucla.chatbot;

/**
 * Created by nkansal on 2/25/17.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * This class models a request sent to the chat bot. Since we are using this in a GET request to
 * /chatbot, we can't simply pass this object to Retrofit. We can't use Gson to convert this object to
 * JSON for GET requests, so instead, Retrofit requires us to provided a Map<String, String> that
 * describe the query parameters. Thus, in order to actually send the information encapsulated in
 * this class, you MUST call the .toMap() method before sending the request using Retrofit.
 */
public class ChatBotRequest {
    /**
     * bot_id: CHAT_BOT_NUM
     * format: CHAT_BOT_FORMAT
     * convo_id: the conversation id
     * say: the actual text to send the chat bot
     */
    public int bot_id;
    public String format;
    public String convo_id;
    public String say;


    /**
     * Use this constructor if we're sending a message to an existing chat bot, identified with
     * the "convo_id" field. The response from the chat bot is serialized in a ChatBotResponse.
     *
     * @param bot_id  CHAT_BOT_NUM
     * @param format   CHAT_BOT_FORMAT
     * @param convo_id  The conversation ID we want to connect with
     * @param say  The message to send to the chat bot
     */
    public ChatBotRequest(int bot_id, String format, String convo_id, String say) {
        this.bot_id = bot_id;
        this.format = format;
        this.convo_id = convo_id;
        this.say = say;
    }

    /**
     * Convert this class to a Map so that it can be sent in the query parameters through retrofit.
     * NOTE: For the Retrofit request to work, you need to create a ChatBotRequest, but pass in the
     * object returned by calling this .toMap() method to Retrofit, instead of the ChatBotRequest
     * itself.
     */

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String,String>();
        map.put("bot_id", (new Integer(this.bot_id)).toString());
        map.put("format", this.format);
        map.put("convo_id", this.convo_id);
        map.put("say", this.say);

        return map;
    }
}
