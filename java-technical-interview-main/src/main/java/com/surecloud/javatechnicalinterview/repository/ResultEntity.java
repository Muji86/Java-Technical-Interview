package com.surecloud.javatechnicalinterview.repository;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "date_taken", columnDefinition = "DATE")
    private LocalDate date_taken;

    public ResultEntity(UUID id, String name, int score, LocalDate date_taken) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.date_taken = date_taken;
    }
}
