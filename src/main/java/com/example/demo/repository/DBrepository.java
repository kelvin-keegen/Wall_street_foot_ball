package com.example.demo.repository;

import com.example.demo.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DBrepository extends MongoRepository <Player,String> {

    Optional<Player> findPlayerByPlayerId(String playerId);
}
