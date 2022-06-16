package tg.project.kinopoisk.requests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.GetEpisodesInfoBySerialIdResponseDto;
import tg.project.kinopoisk.requests.getserialinfobyserialid.GetSerialInfoBySerialIdResponseDto;
import tg.project.kinopoisk.requests.searchserialbyserialname.SearchByNameResponse;

import java.util.Optional;

@HttpExchange
public interface KinopoiskApi {

    @GetExchange("/api/v2.1/films/search-by-keyword")
    ResponseEntity<Optional<SearchByNameResponse>> searchSerialBySerialName(@RequestParam String keyword);

    @GetExchange("/api/v2.2/films/{id}/seasons")
    ResponseEntity<Optional<GetEpisodesInfoBySerialIdResponseDto>> episodesInfoBySerialId(@PathVariable Long id);

    @GetExchange("/api/v2.2/films/{id}")
    ResponseEntity<Optional<GetSerialInfoBySerialIdResponseDto>> serialInfoBySerialId(@PathVariable Long id);

}