package com.spotify

import com.spotify.api.AlbumsApiClient
import com.spotify.api.ApiContext
import com.spotify.api.ArtistsApiClient
import com.spotify.api.SearchApiClient
import com.spotify.api.TokenApiClient
import io.restassured.RestAssured
import spock.lang.Specification
import static com.spotify.configuration.Configuration.CONFIGURATION

abstract class BaseSpec extends Specification {

    protected ApiContext apiContext
    protected SearchApiClient searchApiClient
    protected ArtistsApiClient artistsApiClient
    protected TokenApiClient tokenApiClient
    protected AlbumsApiClient albumsApiClient

    def setup(){
        RestAssured.baseURI = CONFIGURATION.getValue("baseUrl")
        RestAssured.basePath = CONFIGURATION.getValue("basePath")
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        apiContext = new ApiContext()
        searchApiClient = new SearchApiClient(apiContext: apiContext)
        artistsApiClient = new ArtistsApiClient(apiContext: apiContext)
        tokenApiClient = new TokenApiClient(apiContext: apiContext)
        albumsApiClient = new AlbumsApiClient(apiContext: apiContext)
    }



}
