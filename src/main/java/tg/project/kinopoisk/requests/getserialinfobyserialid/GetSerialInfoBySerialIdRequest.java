package tg.project.kinopoisk.requests.getserialinfobyserialid;

import tg.project.kinopoisk.properties.KinopoiskProperties;
import tg.project.kinopoisk.providers.KinopoiskUrlProvider;
import tg.project.kinopoisk.requests.AbstractKinopoiskRequest;
import tg.project.kinopoisk.requests.getserialinfobyserialid.response.GetSerialInfoBySerialIdResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;

@Component
public class GetSerialInfoBySerialIdRequest extends AbstractKinopoiskRequest {


    public GetSerialInfoBySerialIdRequest(KinopoiskProperties.Headers headers, RestTemplate restTemplate, KinopoiskUrlProvider urlProvider) {
        super(headers, restTemplate, urlProvider);
    }

    public Optional<GetSerialInfoBySerialIdResponseDto> execute(Long serialId) {
        String url = urlProvider.serialInfoBySerialId(serialId);
        ResponseEntity<GetSerialInfoBySerialIdResponseDto> response =
                restTemplate.exchange(url, GET, defaultEntity(), GetSerialInfoBySerialIdResponseDto.class);
        return Optional.ofNullable(response.getBody());
    }
}
