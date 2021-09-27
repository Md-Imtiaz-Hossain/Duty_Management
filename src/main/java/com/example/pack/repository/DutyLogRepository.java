package com.example.pack.repository;


import com.example.pack.model.DutyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DutyLogRepository extends JpaRepository<DutyLog, Long> {

}
