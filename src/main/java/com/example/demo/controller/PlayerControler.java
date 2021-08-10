package com.example.demo.controller;

import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class PlayerControler {

    private final PlayerService playerService;

    @GetMapping(path = "/get")
    public List<Player> GetAllPlayers() {

        return playerService.GetData();
    }

}
