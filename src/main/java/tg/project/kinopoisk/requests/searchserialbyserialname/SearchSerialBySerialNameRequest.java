package tg.project.kinopoisk.requests.searchserialbyserialname;

import tg.project.kinopoisk.properties.KinopoiskProperties;
import tg.project.kinopoisk.providers.KinopoiskUrlProvider;
import tg.project.kinopoisk.exceptions.SearchByNameException;
import tg.project.kinopoisk.requests.AbstractKinopoiskRequest;
import tg.project.kinopoisk.requests.searchserialbyserialname.response.Film;
import tg.project.kinopoisk.requests.searchserialbyserialname.response.SearchByNameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class SearchSerialBySerialNameRequest extends AbstractKinopoiskRequest {

    public SearchSerialBySerialNameRequest(KinopoiskProperties.Headers headers, RestTemplate restTemplate, KinopoiskUrlProvider urlProvider) {
        super(headers, restTemplate, urlProvider);
    }

    public Film get(String text) {
        String url = urlProvider.searchSerialBySerialName(text);
        ResponseEntity<SearchByNameResponse> response = restTemplate.exchange(url, GET, defaultEntity(), SearchByNameResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw SearchByNameException.requestError();
        }
        SearchByNameResponse searchByNameResponse = response.getBody();
        if (searchByNameResponse == null) {
            throw SearchByNameException.bodyIsNull();
        }
        List<Film> films = searchByNameResponse.getFilms();
        if (films.isEmpty()) {
            throw SearchByNameException.filmNotFound();
        }
        return films.get(0);
    }
}