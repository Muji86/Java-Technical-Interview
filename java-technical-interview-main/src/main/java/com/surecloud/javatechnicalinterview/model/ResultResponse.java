package com.surecloud.javatechnicalinterview.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResultResponse {
    private String id;
    private String name;
    private int score;
    private LocalDate date_taken;

    public ResultResponse(String id, String name, int score, LocalDate date_taken) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.date_taken = date_taken;
    }
}
