package tg.project.kinopoisk.requests.getepisodesinfobyserialid;

import tg.project.kinopoisk.properties.KinopoiskProperties;
import tg.project.kinopoisk.providers.KinopoiskUrlProvider;
import tg.project.kinopoisk.requests.AbstractKinopoiskRequest;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.response.GetEpisodesInfoBySerialIdResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;

@Component
public class GetEpisodesInfoBySerialIdRequest extends AbstractKinopoiskRequest {


    public GetEpisodesInfoBySerialIdRequest(KinopoiskProperties.Headers headers, RestTemplate restTemplate, KinopoiskUrlProvider urlProvider) {
        super(headers, restTemplate, urlProvider);
    }

    public Optional<GetEpisodesInfoBySerialIdResponseDto> execute(Long serialId) {
        String url = urlProvider.episodesInfoBySerialId(serialId);
        System.out.println("URL: " + url);
        System.out.println("ENTITY: " + defaultEntity());
        ResponseEntity<GetEpisodesInfoBySerialIdResponseDto> response =
                restTemplate.exchange(url, GET, defaultEntity(), GetEpisodesInfoBySerialIdResponseDto.class);
        return Optional.ofNullable(response.getBody());
    }
}