package com.example.pack.service;


import com.example.pack.exception.ClientNotFoundException;
import com.example.pack.model.Client;
import com.example.pack.repository.ClintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClintRepository clintRepository;


    public List<Client> getAllClient() {
        return clintRepository.findAll();
    }


    public void saveClient(Client client) {
        clintRepository.save(client);
    }


    public Client getClientById(Long id) {
        Optional<Client> optional = clintRepository.findById(id);
        Client client = null;
        if (optional.isPresent()) {
            client = optional.get();
        } else {
            throw new ClientNotFoundException("No Client record exist for given id");
        }
        return client;
    }


    public void deleteClientById(Long id) {
        this.clintRepository.deleteById(id);
    }


}
