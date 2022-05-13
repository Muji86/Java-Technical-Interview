package com.surecloud.javatechnicalinterview.repository;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "result")
@Data
@NoArgsConstructor
public class ResultEntity {
    @Id
    private UUID id;
    private String name;
    private int score;
    @Temporal(TemporalType.DATE)
    private Date date_taken;

    public ResultEntity(UUID id, String name, int score, Date date_taken) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.date_taken = date_taken;
    }
}
