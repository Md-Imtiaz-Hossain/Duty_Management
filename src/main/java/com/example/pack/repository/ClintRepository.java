package com.example.pack.repository;


import com.example.pack.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClintRepository extends JpaRepository<Client, Long> {

}
