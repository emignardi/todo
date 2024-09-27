package com.algonquincollege.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "Todo")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String task;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private LocalDate deadline;
}
