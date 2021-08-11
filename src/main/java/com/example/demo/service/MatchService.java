package com.example.demo.service;

import com.example.demo.externalObjects.Matchstats;
import com.example.demo.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public List<Matchstats> GetDataofMatch() {

        return matchRepository.findAll();
    }

    public Matchstats GetMatchStats(String matchId, String playerId) {

        Matchstats matchstats = matchRepository.findMatchstatsByMatchId(matchId)
                .orElseThrow(() -> new IllegalStateException("Match ID not found"));

        if (!Objects.equals(playerId, matchstats.getPlayerId())) {
            throw new IllegalArgumentException("Player ID not found, please check ID");
        }else {
         return matchstats;
        }
    }
}
