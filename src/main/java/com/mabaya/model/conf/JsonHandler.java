package com.mabaya.model.conf;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonHandler {

    public static boolean verifyValidJson(String jsonString) {
        try {
            JsonElement jsonElement = JsonParser.parseString(jsonString);
            if(jsonElement!=null){
                return true;
            }
        } catch (JsonSyntaxException jse) {
            System.out.println("Not a valid Json String:" + jse.getMessage());
        }
        return false;
    }
}
