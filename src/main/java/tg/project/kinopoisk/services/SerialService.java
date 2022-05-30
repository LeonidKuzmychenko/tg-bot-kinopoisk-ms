package tg.project.kinopoisk.services;

import tg.project.kinopoisk.dtos.SearchByNameDto;
import tg.project.kinopoisk.dtos.SerialWithReleaseTodayEpisodes;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.GetEpisodesInfoBySerialIdRequest;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.response.Episode;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.response.GetEpisodesInfoBySerialIdResponseDto;
import tg.project.kinopoisk.requests.getepisodesinfobyserialid.response.Item;
import tg.project.kinopoisk.requests.getserialinfobyserialid.GetSerialInfoBySerialIdRequest;
import tg.project.kinopoisk.requests.getserialinfobyserialid.response.GetSerialInfoBySerialIdResponseDto;
import tg.project.kinopoisk.requests.searchserialbyserialname.SearchSerialBySerialNameRequest;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasLength;

@Service
public class SerialService {

    private final GetSerialInfoBySerialIdRequest getSerialInfoBySerialIdRequest;
    private final SearchSerialBySerialNameRequest searchSerialBySerialNameRequest;
    private final GetEpisodesInfoBySerialIdRequest getEpisodesInfoBySerialIdRequest;

    public SerialService(GetSerialInfoBySerialIdRequest getSerialInfoBySerialIdRequest, SearchSerialBySerialNameRequest searchSerialBySerialNameRequest, GetEpisodesInfoBySerialIdRequest getEpisodesInfoBySerialIdRequest) {
        this.getSerialInfoBySerialIdRequest = getSerialInfoBySerialIdRequest;
        this.searchSerialBySerialNameRequest = searchSerialBySerialNameRequest;
        this.getEpisodesInfoBySerialIdRequest = getEpisodesInfoBySerialIdRequest;
    }

    public String getSerialNameBySerialId(Long serialId) {
        System.out.println("Get name from serial " + serialId);
        Optional<GetSerialInfoBySerialIdResponseDto> oSerialInfo = getSerialInfoBySerialIdRequest.execute(serialId);
        if (oSerialInfo.isPresent()) {
            GetSerialInfoBySerialIdResponseDto serialInfo = oSerialInfo.get();
            if (hasLength(serialInfo.getNameRu())) {
                return serialInfo.getNameRu();
            }
            if (hasLength(serialInfo.getNameEn())) {
                return serialInfo.getNameEn();
            }
            if (hasLength(serialInfo.getNameOriginal())) {
                return serialInfo.getNameOriginal();
            }
        }
        return "";
    }

    public SearchByNameDto getSearchByNameDto(String name){
        return searchSerialBySerialNameRequest.get(name).toSearchByNameDto();
    }

    public List<Episode> getTodayReleasesEpisodes(Long serialId) {
        //Достаю все эпизоды сериала
        Optional<GetEpisodesInfoBySerialIdResponseDto> oSerial = getEpisodesInfoBySerialIdRequest.execute(serialId);
        if (oSerial.isPresent()) {
            List<Item> items = oSerial.get().getItems();
            if (items != null && !items.isEmpty()) {
                //Фильтрую все эпизоды на те, у которых есть название и дата выхода сегодня
                return filterTodayReleasesEpisodes(items);
            }
        }
        return null;
    }

    private List<Episode> filterTodayReleasesEpisodes(List<Item> items) {
        return items.stream()
                .map(Item::getEpisodes)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(episode -> episode.getReleaseDate() != null)
//                .filter(episode -> LocalDate.now().isEqual(episode.getReleaseDate()))
                .filter(episode -> LocalDate.parse("2022-02-02").isEqual(episode.getReleaseDate()))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public SerialWithReleaseTodayEpisodes getSerialTodayReleaseEpisodesBySerialId(Long serialId) {
        //Достаю все серии сериала, которые сегодня вышли
        List<Episode> episodes = getTodayReleasesEpisodes(serialId);
        SerialWithReleaseTodayEpisodes serialWithReleaseTodayEpisodes = null;
        if (episodes != null && !episodes.isEmpty()) {
            //Если список серий не пустой, то достаю название сериала
            String serialName = getSerialNameBySerialId(serialId);
            serialWithReleaseTodayEpisodes = new SerialWithReleaseTodayEpisodes(serialId, serialName, episodes);
        }
        return serialWithReleaseTodayEpisodes;
    }
}
