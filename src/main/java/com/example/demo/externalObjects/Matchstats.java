package com.example.demo.externalObjects;

import com.example.demo.service.MatchService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Document
@Data
@NoArgsConstructor
@Component
public class Matchstats {

    private String playerId;
    private Map<String, Double> stats;

    private String matchId; // added to tie statistics to a match

    @Transient
    @Autowired
    private MatchService matchService;

    public Matchstats(String playerId, Map<String, Double> stats, String matchId) {
        this.playerId = playerId;
        this.stats = stats;
        this.matchId = matchId;
    }

    public Matchstats retrieveStats(String matchId,String playerId) {

        //Assume this is a business logic for querying DB for match
        return matchService.GetMatchStats(matchId,playerId);
    }

}
