package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Matchstats {

    private String playerId;
    private Map<String, Double> stats;

    private String matchId; // added to tie statistics to a match


    private Map<String,Double> configuringDemoData() {

        stats = new HashMap<>();

        String nr_goals = "nr_goals";
        stats.put(nr_goals,5.0);
        stats.put("nr_fouls",2.0);
        stats.put("nr_assists",1.0);
        stats.put("nr_own_goals",0.0);
        stats.put("nr_goals_conceded",2.0);

        return stats;

    }

    public Matchstats retrieveStats(String matchId,String playerId) {

        //Assume this is a business logic for querying DB for match
        if (matchId.equals("match001") && playerId.equals("22")) {

           Matchstats currentBuffer = new Matchstats(

                    playerId = "22",
                    configuringDemoData(),
                   matchId = "match001"
            );

           return currentBuffer;
        }

        return null;
    }

}
