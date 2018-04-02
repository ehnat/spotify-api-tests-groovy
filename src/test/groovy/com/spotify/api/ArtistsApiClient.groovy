package com.spotify.api

import static io.restassured.RestAssured.given

class ArtistsApiClient {

    static final String ARTISTS_ENDPOINT = "/artists"
    static final String ALBUMS_FOR_ARTIST_ENDPOINT = "/artists/{artistId}/albums"
    ApiContext apiContext

    String getArtistName(String artistId) {
        return given().
            spec(apiContext.getTokenSpec()).
            pathParam("artistId", artistId).
        when().
            get(ARTISTS_ENDPOINT + "/{artistId}").
        then().
            extract().
            path("name")
    }

    List<String> getAlbumIdsForArtist(String artistId){
        return given().
                    spec(apiContext.getTokenSpec()).
                    pathParam("artistId", artistId).
                when().
                    get(ALBUMS_FOR_ARTIST_ENDPOINT).
                then().
                    extract().
                    path("items.id")
    }
}
