package org.lapshovi.MailingService.service;

import org.lapshovi.MailingService.model.Client;
import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.model.Message;
import org.lapshovi.MailingService.request.MessageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    private final String URL = "https://probe.fbrq.cloud/v1/send/";
    private final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODIwMDE4MjAsImlzcyI6ImZhYnJpcXVlIiwibmFtZSI6IkJhcmxvZyJ9.g7SvTiwTB_YxUhj6pf6nk0jR-d0P1zB0LP3MuEp9OKI";
    private final HttpHeaders headers = new HttpHeaders(
            new MultiValueMapAdapter<>(Map.of("Authorization", List.of("Bearer " + TOKEN))));
    private final MessageService messageService;
    private final MailingListService mailingListService;
    private final ClientService clientService;

    public JobService(MessageService messageService, MailingListService mailingListService, ClientService clientService) {
        this.messageService = messageService;
        this.mailingListService = mailingListService;
        this.clientService = clientService;
    }

    @Transactional
    public void filterAndSendMessages(Long id) {
        MailingList mailingList = mailingListService.findById(id);
        String filter = mailingList.getFilter();
        List<Client> clientsList = clientService.getAllByTagOrOperatorsCode(filter, filter);
        for (Client client : clientsList) {
            sendMessages(client, mailingList);
        }
    }

    private void sendMessages(Client client, MailingList mailingList) throws HttpStatusCodeException {
        RestTemplate restTemplate = new RestTemplate();
        Message message = new Message(OffsetDateTime.now(), "Не отправлено", mailingList, client);
        messageService.saveMessage(message);
        try {
            MessageRequest messageRequest = new MessageRequest(message.getId(), client.getphone(), mailingList.getMessage());
            HttpEntity<?> httpEntity = new HttpEntity<>(messageRequest, headers);
            ResponseEntity<?> response = restTemplate.postForEntity(URL + message.getId(), httpEntity, MessageRequest.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                message.setStatus("Отправлено");
                messageService.updateMessage(message.getId(), message);
            }
        } catch (HttpStatusCodeException ex) {
            System.out.println("Сообщение не отправлено");
        }
    }
}
