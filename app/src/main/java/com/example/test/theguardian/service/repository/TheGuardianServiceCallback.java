package com.example.test.theguardian.service.repository;

public interface TheGuardianServiceCallback<RESPONSE> {

    void onResponse(RESPONSE response);

    void onError(String errorStatus);

}
