package com.ecomerce.api.domain;

import com.ecomerce.api.config.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "todos")
public class Todo extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String title;

    private LocalDate deadLine;

    private Boolean isDone;

    private Boolean isMyDay;

    private Boolean isImportant;

}
