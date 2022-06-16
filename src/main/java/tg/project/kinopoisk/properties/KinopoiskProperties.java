package tg.project.kinopoisk.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "kinopoisk")
public class KinopoiskProperties {

    private final String authHeaderKey;
    private final String authHeaderValue;
    private final String baseUrl;

    @ConstructorBinding
    public KinopoiskProperties(String authHeaderKey, String authHeaderValue, String baseUrl) {
        this.authHeaderKey = authHeaderKey;
        this.authHeaderValue = authHeaderValue;
        this.baseUrl = baseUrl;
    }
}