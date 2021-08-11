package com.example.demo.config;


import com.example.demo.entity.Player;
import com.example.demo.externalObjects.Matchstats;
import com.example.demo.repository.DBrepository;
import com.example.demo.repository.MatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class MatchConfig implements IndexPublisher{

    private final DBrepository dBrepository;
    private final MatchRepository matchRepository;
    private final Matchstats matchstats;


    private final String playerId = "22";
    private final String matchId = "match001";

    @Bean
    public void SavingDemoMatch() {

       Map<String,Double> stats = new HashMap<>();

        stats.put("nr_goals",5.0);
        stats.put("nr_fouls",2.0);
        stats.put("nr_assists",1.0);
        stats.put("nr_own_goals",0.0);
        stats.put("nr_goals_conceded",2.0);

        Matchstats matchstats = new Matchstats(
                playerId,
                stats,
                matchId
        );

        long matchSize = matchRepository.count();

        // console messages added for debugging
        if(matchSize > 0) {
            System.out.println(matchstats);
        }else {
            matchRepository.save(matchstats);
            System.out.println(matchstats);
            System.out.println("saving...");
        }
    }

    @Bean
    public void SavingDemoPlayer() {

        Player player = new Player(

                playerId,
                "4",
                "ManCity"
        );

        long playersize = dBrepository.count();

        if(playersize > 0) {
            System.out.println(player);
        } else {
            dBrepository.save(player);
            System.out.println(player);
            System.out.println("saving...");
        }
    }

    @Bean
    public void Stats_and_Index_Calculation() {

        double index;

        double nr_goals;
        double nr_fouls;
        double nr_assists;
        double nr_own_goals;
        double nr_goals_conceded;
        Map<String,Double> receivedstats;

        Player player = dBrepository.findPlayerByPlayerId(playerId)
                .orElseThrow(() -> new IllegalStateException("No Player found with that ID"));

        // Simulates a controller receiving Match stats
        Matchstats currentMatch = matchstats.retrieveStats(matchId,playerId);

        receivedstats = currentMatch.getStats();

        nr_goals = receivedstats.get("nr_goals");
        nr_fouls = receivedstats.get("nr_fouls");
        nr_assists = receivedstats.get("nr_assists");
        nr_own_goals = receivedstats.get("nr_own_goals");
        nr_goals_conceded = receivedstats.get("nr_goals_conceded");

        // Calculation of index

        index = (nr_goals*3) + (nr_assists*1) + (nr_own_goals*(-3))
                + (nr_fouls*(-0.2)) + (nr_goals_conceded*(-1));

        //Showing Debug result

        System.out.println(index);

        // Publish index

        IndexPublisher.publishIndex(player,index);

        }
    }


