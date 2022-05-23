package org.lapshovi.MailingService.service;

import org.lapshovi.MailingService.model.Client;
import org.lapshovi.MailingService.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public void updateClient(Long id, Client client) {
        Client updateClient = clientRepository.getById(id);
        updateClient.setphone(client.getphone());
        updateClient.setTag(client.getTag());
        updateClient.setTimeZone(client.getTimeZone());
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllByTagOrOperatorsCode(String tag, String operatorsCode) {
        return clientRepository.getAllByTagOrOperatorsCode(tag, operatorsCode);
    }
}
