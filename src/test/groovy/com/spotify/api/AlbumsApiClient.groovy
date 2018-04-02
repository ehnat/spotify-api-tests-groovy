package com.spotify.api

import static io.restassured.RestAssured.given

class AlbumsApiClient {

    public static final String TRACKS_ENDPOINT = "/albums/{albumId}/tracks"
    ApiContext apiContext

    int getTotalTracksForAlbum(String albumId) {
        return given().
                    spec(apiContext.getTokenSpec()).
                    pathParam("albumId", albumId).
                when().
                    get(TRACKS_ENDPOINT).
                then().
                    extract().
                    path("total")
    }

    List<String> getTracksForAlbum(String albumId) {
        return given().
                    spec(apiContext.getTokenSpec()).
                    pathParam("albumId", albumId).
                    queryParam("limit", getTotalTracksForAlbum(albumId)).
                when().
                    get(TRACKS_ENDPOINT).
                then().
                    extract().
                    path("items.name")
    }
}
