package tg.project.kinopoisk.dtos;

import tg.project.kinopoisk.requests.getepisodesinfobyserialid.response.Episode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SerialWithReleaseTodayEpisodes {

    private Long serialId;
    private String serialName;
    private List<Episode> releasesEpisode = new ArrayList<>();
}