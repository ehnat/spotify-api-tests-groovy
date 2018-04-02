package com.spotify.api

import io.restassured.response.Response
import static io.restassured.RestAssured.given

class SearchApiClient {

    public static final String SEARCH_ENDPOINT = "/search"
    ApiContext apiContext

    Response getSearchedArtists(String artistName) {
        given().
            spec(apiContext.getTokenSpec()).
            queryParams("q", artistName, "type", "artist").
        when().
            get(SEARCH_ENDPOINT).
        then().
            extract().
            response()
    }

    Map<String, String> findFirstArtistIdAndName(Response response){
        String artistName = findFirstArtistName(response)
        String artistID = findFirstArtistID(response)
        Map<String, String> map = new HashMap<>()
        map.put(artistID, artistName)
        return map
    }

    private String findFirstArtistName(Response response) {
        return response.then().
                extract().
//                path("artists.items.name[0]")
                path("artists.items[0].name")
    }

    private String findFirstArtistID(Response response) {
        return response.then().
                extract().
//                path("artists.items.id[0]")
                path("artists.items[0].id")
    }
}
