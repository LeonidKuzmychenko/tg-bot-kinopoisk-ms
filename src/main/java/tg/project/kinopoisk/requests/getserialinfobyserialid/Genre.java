package tg.project.kinopoisk.requests.getserialinfobyserialid;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @JsonProperty("genre")
    private String genre;
}