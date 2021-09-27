package com.example.pack.model;

import com.example.pack.enums.Designation;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This filed is required !!")
    @Size(min = 2, max = 20, message = "Min 2 and maximum 20 are allowed.")
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !! ")
    @Column
    private String email;

    @NotBlank(message = "This filed is required !!")
    @Column
    private String phone;


    @NotBlank(message = "This filed is required !!")
    @Size(min = 2, max = 50, message = "Min 2 and maximum 50 are allowed.")
    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private Designation designation;

    @Column
    private String status;


    @Column
    private String photo;

    @NotBlank(message = "This filed is required !!")
    @Column
    private String userName;

    @NotBlank(message = "This filed is required !!")
    @Column
    private String password;

    @Column
    private String userType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Duty duty;


    public Employee() {
    }

//    @Override
//    public String toString() {
//        return  ' ' + firstName + ' ' + lastName + ' ';
//    }
}
