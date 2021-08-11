package com.example.demo.repository;

import com.example.demo.externalObjects.Matchstats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MatchRepository extends MongoRepository<Matchstats,String> {

    Optional<Matchstats> findMatchstatsByMatchId(String matchId);
}
