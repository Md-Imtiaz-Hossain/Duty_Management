package com.example.pack.repository;


import com.example.pack.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where "
            + "CONCAT(e.firstName, ' ', e.lastName, ' ', e.email, ' ', e.phone, ' ', e.address)"
            + "like %?1%")
    Page<Employee> findAll(String keyword, Pageable pageable);

}
