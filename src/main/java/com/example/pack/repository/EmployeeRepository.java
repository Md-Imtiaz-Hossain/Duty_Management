package com.example.pack.repository;


import com.example.pack.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select u from Employee u where u.userName = ?1")
    Employee findByUserName(String userName);

}
