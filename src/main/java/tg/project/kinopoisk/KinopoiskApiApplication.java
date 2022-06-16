package tg.project.kinopoisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = "tg.project.kinopoisk.properties")
public class KinopoiskApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinopoiskApiApplication.class, args);
    }
}