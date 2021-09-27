package com.example.pack.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Duty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date dutyDate;

    @Column
    private Time startAt;

    @Column
    private Time endAt;

    @Column
    private String location;

    @Column
    private String location_optional;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Client client;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<Employee> employees = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private List<DutyLog> dutyLogs;

    public Duty() {
    }

    public List<String> getEmployeePhoto() {
        return employees.stream().
                map(Employee::getPhoto).
                collect(Collectors.toList());
    }

    public List<String> getEmployeeName() {
        return employees.stream().
                map(Employee::getFirstName).
                collect(Collectors.toList());
    }

    public List<String> getEmployeeLastName() {
        return employees.stream().
                map(Employee::getLastName).
                collect(Collectors.toList());
    }

    public List<String> getEmployeeNumber() {
        return employees.stream().
                map(Employee::getPhone).
                collect(Collectors.toList());
    }

    public Time getStart(){
        return startAt;
    }

    public Time getEnd(){
        return endAt;
    }


}
