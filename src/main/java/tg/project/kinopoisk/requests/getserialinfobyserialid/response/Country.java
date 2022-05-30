package tg.project.kinopoisk.requests.getserialinfobyserialid.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @JsonProperty("country")
    private String country;

}