package com.example.pack.controller;

import com.example.pack.exception.ClientIdNotFoundException;
import com.example.pack.model.Client;
import com.example.pack.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    // display list of Client
    @GetMapping("/home")
    public String home(Model model) {
        List<Client> listClients = clientService.getAllClient();
        model.addAttribute("title", "Home - Client Management");
        model.addAttribute("listClients", listClients);
        return "/client/home";
    }


    // display the Client create form
    @GetMapping("/show-new-client-form")
    public String showNewClientForm(Model model) {
        Client client = new Client();
        model.addAttribute("title", "Create - Client Create Form");
        model.addAttribute("client", client);
        return "/client/new-client";
    }


    // Process the Client create form
    @PostMapping("/save-client")
    public String saveClient(@Valid @ModelAttribute("client") Client client,
                             Errors errors,
                             Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("client", client);
            return "/client/new-client";
        } else {
            clientService.saveClient(client);
            return "redirect:/client/home";
        }
    }


    // display the client Update form
    @GetMapping("/show-form-for-update")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {

        Optional<Client> optional = Optional.ofNullable(clientService.getClientById(id));
        Client client = null;
        if (optional.isPresent()) {
            client = optional.get();
            model.addAttribute("title", "Update - Client Update Form");
            model.addAttribute("client", client);
            return "/client/new-client";
        } else
            throw new ClientIdNotFoundException("Id Not Found !!! ");

    }


    // Delete the client and redirect to the current page
    @GetMapping("/delete-client")
    public String deleteClient(@RequestParam("id") Long id) {
        this.clientService.deleteClientById(id);
        return "redirect:/client/home";
    }


}
