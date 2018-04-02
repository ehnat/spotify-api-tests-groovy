package com.spotify

import io.restassured.response.Response
import spock.lang.Unroll


class TracksForAlbumSpec extends BaseSpec {

    @Unroll
    def "test if tracks number: #tracksNumber is found for correct artist #artistName"(String artistName, int tracksNumber) {
        given:
        Response artists = searchApiClient.getSearchedArtists(artistName)
        Map<String, String> artistIdAndName = searchApiClient.findFirstArtistIdAndName(artists)
        String artistId = ++artistIdAndName.keySet().iterator()
        List<String> albumsIds = artistsApiClient.getAlbumIdsForArtist(artistId)

        when:
        List<String> trackNames = albumsApiClient.getTracksForAlbum(albumsIds[0])

        then:
        trackNames.size() == tracksNumber

        where:
        artistName | tracksNumber
        "Eleni"    | 40
        "Madonna"  | 22
        "Prince" | 35
    }
}
