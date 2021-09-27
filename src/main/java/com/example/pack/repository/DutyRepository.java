package com.example.pack.repository;


import com.example.pack.model.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DutyRepository extends JpaRepository<Duty, Long> {


//    @Query("select u from Duty u where u.dutyDate = ?1")
//    Duty findByEmail(String email);
}
