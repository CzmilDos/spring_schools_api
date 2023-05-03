package com.estiam.final_project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="classe")
@Setter
@Getter
@NoArgsConstructor
public class Classroom{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private School school;

}
