package com.example.demo.config;


import com.example.demo.entity.Player;
import com.example.demo.IndexPublisher;
import com.example.demo.Matchstats;
import com.example.demo.repository.DBrepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;
import java.util.Optional;

@Configuration
@AllArgsConstructor
public class MatchConfig {

    private final DBrepository dBrepository;


    private final Matchstats matchstats = new Matchstats();
    private final IndexPublisher indexPublisher = new IndexPublisher();


    private final String playerId = "22";

    @Bean
    public String SavingDemoPlayer() {

        Player player = new Player(

                playerId,
                "4",
                "ManCity"
        );

        long playersize = dBrepository.count();

        if(playersize > 0) {

            //throw new IllegalStateException("There is a player");
            System.out.println("There is a player");

            Stats_and_Index_Calculation();

        } else {

            dBrepository.save(player);

            Stats_and_Index_Calculation();

        }

        return "Player Saved";

    }

    public void Stats_and_Index_Calculation() {

        double index;

        double nr_goals;
        double nr_fouls;
        double nr_assists;
        double nr_own_goals;
        double nr_goals_conceded;
        Map<String,Double> receivedstats;

        System.out.println("Getting Player from DB");


        Player player = dBrepository.findPlayerByPlayerId(playerId)
                .orElseThrow(() -> new IllegalStateException("No Player found with that ID"));


        // Simulates a controller receiving Match stats parameters
        String matchId = "match001"; // Param
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

        //Debugging result

        System.out.println(index);

        // Publish index

        indexPublisher.publishIndex(player,index);


        }




    }


