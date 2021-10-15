package com.example.pack.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DutyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime start_dateTime;

    @Column
    private float start_location_latitude;

    @Column
    private float start_location_longitude;

    @Column
    private String start_comment;



    @Column
    private LocalDateTime current_dateTime;

    @Column
    private float current_location_latitude;

    @Column
    private float current_location_longitude;



    @Column
    private LocalDateTime end_dateTime;

    @Column
    private float end_location_latitude;

    @Column
    private float end_location_longitude;

    @Column
    private String end_comment;

    @Column
    private Long duty_id;

}
