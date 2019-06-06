package com.example.test.theguardian.service.dto;

import com.google.gson.annotations.SerializedName;

public class MainResponse {
    @SerializedName("response")
    private GetNewsResponse response;

    public GetNewsResponse getNewsResponse() {
        return response;
    }

    public void setResponse(GetNewsResponse response) {
        this.response = response;
    }
}
