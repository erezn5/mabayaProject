package com.mabaya.model.api;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

public class SimpleHttpClient {

    private final OkHttpClient httpClient = new OkHttpClient();

    Headers.Builder builder = new Headers.Builder();
    public String sendGetRequest(String url, HashMap<String, String> headersMap) throws IOException {
        Headers headers = addHeaders(headersMap);
        Request request = new Request.Builder()
                .url(url).headers(headers).build();
        return getResponse(request);
    }

    private String getResponse(Request request) throws IOException {
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            final String responseBody = response.body().string();
            if (!response.isSuccessful()) {
                System.out.print("Request failed. Returning response for further analysis");
                System.out.println("Request Failed");
            }
            System.out.printf("Response data is: =[%s]", responseBody);
            return responseBody;
        }
    }

    private Headers addHeaders(HashMap<String, String> headersMap){
        headersMap.forEach((key, value) -> builder.add(key, value));
        return builder.build();
    }

    public String sendPostRequest(String url, String body, HashMap<String, String> headersMap) throws IOException {
        return "IF WE WANT TO ADD POST REQUEST";
    }

}