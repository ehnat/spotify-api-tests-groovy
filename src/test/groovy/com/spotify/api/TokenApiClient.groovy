package com.spotify.api

import static io.restassured.RestAssured.given

import static io.restassured.http.ContentType.URLENC

class TokenApiClient {

    public static final TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token"
    ApiContext apiContext

    String getRefreshedToken() {
        return given().
                    contentType(URLENC).
                    params(apiContext.credentialsForRefreshToken).
                when().
                    post(TOKEN_ENDPOINT).
                then().
                    extract().
                    path("access_token")
    }


}
