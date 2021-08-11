package com.example.demo.controller;

import com.example.demo.externalObjects.Matchstats;
import com.example.demo.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping(path = "/get")
    public List<Matchstats> GetDataofMatch() {

        return matchService.GetDataofMatch();
    }


}
