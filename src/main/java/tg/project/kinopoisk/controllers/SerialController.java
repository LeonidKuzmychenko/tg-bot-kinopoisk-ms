package tg.project.kinopoisk.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tg.project.kinopoisk.dtos.SearchByNameDto;
import tg.project.kinopoisk.dtos.SerialWithReleaseTodayEpisodes;
import tg.project.kinopoisk.services.SerialService;

@RestController
@RequestMapping
public class SerialController {

    private final SerialService service;

    public SerialController(SerialService service) {
        this.service = service;
    }

    @GetMapping("/getSerialByName")
    public SearchByNameDto getSerialByName(@RequestParam("name") String name) {
        return service.getSearchByNameDto(name);
    }

    @GetMapping("/getSerialTodayReleaseEpisodesBySerialId")
    public SerialWithReleaseTodayEpisodes getSerialTodayReleaseEpisodesBySerialId(@RequestParam("serialId") Long serialId) {
        return service.getSerialTodayReleaseEpisodesBySerialId(serialId);
    }
}