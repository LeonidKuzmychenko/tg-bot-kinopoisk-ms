package tg.project.kinopoisk.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tg.project.kinopoisk.exceptions.SearchByNameException;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.GetEpisodesInfoBySerialIdResponseDto;
import tg.project.kinopoisk.requests.getserialinfobyserialid.GetSerialInfoBySerialIdResponseDto;
import tg.project.kinopoisk.requests.searchserialbyserialname.SearchByNameResponse;

import java.util.Optional;

@Service
public class KinopoiskRequests {

    private final KinopoiskApi kinopoisk;

    public KinopoiskRequests(KinopoiskApi kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public Optional<GetEpisodesInfoBySerialIdResponseDto> episodesInfoBySerialId(Long serialId) {
        ResponseEntity<Optional<GetEpisodesInfoBySerialIdResponseDto>> response = kinopoisk.episodesInfoBySerialId(serialId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public Optional<GetSerialInfoBySerialIdResponseDto> serialInfoBySerialId(Long serialId) {
        ResponseEntity<Optional<GetSerialInfoBySerialIdResponseDto>> response = kinopoisk.serialInfoBySerialId(serialId);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public Optional<SearchByNameResponse> searchSerialBySerialName(String serialName) {
        ResponseEntity<Optional<SearchByNameResponse>> response = kinopoisk.searchSerialBySerialName(serialName);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw SearchByNameException.requestError();
        }
        return response.getBody();
    }
}