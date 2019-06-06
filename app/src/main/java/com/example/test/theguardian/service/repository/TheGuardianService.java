package com.example.test.theguardian.service.repository;

import com.example.test.theguardian.service.dto.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TheGuardianService {
    String BASE_URL = "https://content.guardianapis.com/";

    //change place for api key in case of multiple usage
    @GET("search?api-key=6a6ffd61-a67d-4503-a8ae-994dcbb7b9ea")
    Call<MainResponse> getNewsList();

}
