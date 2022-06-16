package tg.project.kinopoisk.requests.searchserialbyserialname;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchByNameResponse {

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("pagesCount")
    private Long pagesCount;

    @JsonProperty("films")
    private List<Film> films = new ArrayList<>();

    @JsonProperty("searchFilmsCountResult")
    private Long searchFilmsCountResult;
}