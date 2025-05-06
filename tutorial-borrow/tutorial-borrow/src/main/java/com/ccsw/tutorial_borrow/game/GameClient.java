package com.ccsw.tutorial_borrow.game;

import com.ccsw.tutorial_borrow.game.model.GameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "SPRING-CLOUD-EUREKA-CLIENT-GAME", url = "http://localhost:8080")
public interface GameClient {

    @GetMapping(value = "/game")
    List<GameDto> findAll();
}
