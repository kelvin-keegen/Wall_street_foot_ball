package com.example.demo.service;

import com.example.demo.entity.Player;
import com.example.demo.repository.DBrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerService {

    private final DBrepository dBrepository;


    public List<Player> GetData() {

        return dBrepository.findAll();
    }
}
