package com.surecloud.javatechnicalinterview.model;

import lombok.Getter;

@Getter
public class ResultRequest {
    private String name;
    private int score;

    public ResultRequest(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
