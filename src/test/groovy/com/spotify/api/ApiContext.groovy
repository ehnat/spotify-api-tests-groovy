package com.spotify.api

import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification

import static com.spotify.configuration.Configuration.CONFIGURATION

class ApiContext {

    private String getRefreshedToken() {
        TokenApiClient tokenApiClient = new TokenApiClient(apiContext: this)
        return tokenApiClient.getRefreshedToken()
    }

    RequestSpecification getTokenSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
        return builder.addHeader("Authorization", "Bearer " + getRefreshedToken())
                        .build()
    }

    Map<String, String> getCredentialsForRefreshToken() {
        Map<String, String> credentials = new HashMap<>()
        credentials.put("grant_type", "refresh_token")
        credentials.put("refresh_token", CONFIGURATION.getValue("refresh_token"))
        credentials.put("client_id", CONFIGURATION.getValue("client_id"))
        credentials.put("client_secret", CONFIGURATION.getValue("client_secret"))
        return credentials
    }
}
