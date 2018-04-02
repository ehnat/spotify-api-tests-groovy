package com.spotify

import io.restassured.response.Response
import spock.lang.Unroll

class SearchAlbumSpec extends BaseSpec {

    @Unroll
    def "test if album is found for correct artist #artistName" (String artistName, String albumId){
        given:
        Response searchedArtists = searchApiClient.getSearchedArtists(artistName)
        Map<String, String> artistIdAndName = searchApiClient.findFirstArtistIdAndName(searchedArtists)

        when:
        List<String> albumIds = artistsApiClient.getAlbumIdsForArtist(++artistIdAndName.keySet().iterator())

        then:
        albumIds[0] == albumId

        where:
        artistName | albumId
        "Eleni" | "3n37MZ65RMCbjWKJtIfBIZ"
        "Madonna" | "4SzRR3gUsuMttb7HFFfId1"
    }
}
