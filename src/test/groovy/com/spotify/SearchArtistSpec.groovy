package com.spotify

import io.restassured.response.Response
import spock.lang.Unroll

class SearchArtistSpec extends BaseSpec {

    @Unroll
    def "test if correct artist #artistName is found"(String artistName) {
        given:

        when:
        Response searchedArtists = searchApiClient.getSearchedArtists(artistName)

        Map<String, String> artistIdAndName = searchApiClient.findFirstArtistIdAndName(searchedArtists)

        then:
        artistIdAndName.get(++artistIdAndName.keySet().iterator()) == artistName
        assertThatCorrectArtistIsFound(artistIdAndName)

        where:
        artistName | _
        "Eleni" | _
        "Madonna" | _
    }

    private void assertThatCorrectArtistIsFound(Map<String, String> artistIdAndName) {
        String artistId = ++artistIdAndName.keySet().iterator()
        artistsApiClient.getArtistName(artistId) == artistIdAndName[artistId]
    }
}
