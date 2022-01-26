package org.iesfm.shop.mongo.controllers;

import org.iesfm.shop.mongo.pojos.Client;
import org.iesfm.shop.mongo.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/clients")
    public void insert(@RequestBody Client client) {
        if (clientRepository.existsById(client.getNif())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe el cliente");
        } else {
            clientRepository.insert(client);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/clients")
    public List<Client> list() {
        return clientRepository.findAll();
    }


}
