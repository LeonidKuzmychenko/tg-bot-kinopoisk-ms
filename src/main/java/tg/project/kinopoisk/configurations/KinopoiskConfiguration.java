package tg.project.kinopoisk.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tg.project.kinopoisk.properties.KinopoiskProperties;
import tg.project.kinopoisk.requests.KinopoiskApi;

@Configuration
public class KinopoiskConfiguration {

    @Bean
    public KinopoiskApi getClient(HttpServiceProxyFactory factory) {
        return factory.createClient(KinopoiskApi.class);
    }

    @Bean
    public HttpServiceProxyFactory getHttpServiceProxyFactory(KinopoiskProperties properties) {
        WebClient webClient = WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(properties.getAuthHeaderKey(), properties.getAuthHeaderValue())
                .build();
        return new HttpServiceProxyFactory(new WebClientAdapter(webClient));
    }
}