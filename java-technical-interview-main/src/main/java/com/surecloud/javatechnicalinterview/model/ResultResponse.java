package com.surecloud.javatechnicalinterview.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class ResultResponse {
    private String id;
    private String name;
    private int score;
    private Date date_taken;

    public ResultResponse(String id, String name, int score, Date date_taken) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.date_taken = date_taken;
    }
}
