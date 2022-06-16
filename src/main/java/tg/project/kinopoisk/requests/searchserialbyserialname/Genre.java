package tg.project.kinopoisk.requests.searchserialbyserialname;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @JsonProperty("genre")
    private String genre;
}