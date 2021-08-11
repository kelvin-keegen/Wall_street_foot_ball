package com.example.demo.config;

import com.example.demo.entity.Player;


public interface IndexPublisher {

       static void publishIndex(Player player, double index) {

        System.out.println("Index to be published is: " + index);
    }




}
