package com.example.pack.repository;


import com.example.pack.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClintRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where "
            + "CONCAT(c.companyName, ' ', c.contactPerson, ' ', c.contactNumber, ' ', c.email)"
            + "like %?1%")
    Page<Client> findAll(String keyword, Pageable pageable);

}
