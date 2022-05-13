package com.surecloud.javatechnicalinterview.repository;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "result")
@Getter
public class ResultEntity {
    @Id
    private UUID id;
    private String name;
    private int score;
    @Temporal(TemporalType.DATE)
    private Date date_taken;
}
