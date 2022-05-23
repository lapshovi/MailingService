package org.lapshovi.MailingService.service;

import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.model.Message;
import org.lapshovi.MailingService.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MailingListService mailingListService;

    @Autowired
    public MessageService(MessageRepository messageRepository, MailingListService mailingListService) {
        this.messageRepository = messageRepository;
        this.mailingListService = mailingListService;
    }

    @Transactional
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Transactional
    public void updateMessage(Long id, Message message) {
        Message updateMessage = messageRepository.getById(id);
        updateMessage.setDateTimeSending(message.getDateTimeSending());
        updateMessage.setStatus(message.getStatus());
        updateMessage.setMailingList(message.getMailingList());
        updateMessage.setClient(message.getClient());
    }

    public List<Message> getAllByMailingList(MailingList mailingList) {
        return messageRepository.getAllByMailingList(mailingList);
    }

    public List<Message> getAllByMailingListId(Long id) {
        MailingList mailingList=mailingListService.findById(id);
        return messageRepository.getAllByMailingList(mailingList);
    }



    public List<Message> getStats() {
        return messageRepository.findAll();
    }
}
