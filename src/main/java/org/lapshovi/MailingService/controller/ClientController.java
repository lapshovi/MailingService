package org.lapshovi.MailingService.controller;

import org.lapshovi.MailingService.model.Client;
import org.lapshovi.MailingService.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/client")
    public ResponseEntity<?> save(@RequestBody @Valid Client client) {
        clientService.saveClient(client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid Client client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
