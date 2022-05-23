package org.lapshovi.MailingService.controller;

import org.lapshovi.MailingService.model.Message;
import org.lapshovi.MailingService.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/stats")
    public List<Message> getStats() {
        return messageService.getStats();
    }

    @GetMapping("/stats/{id}")
    public List<Message> getStatsById(@PathVariable Long id) {
        return messageService.getAllByMailingListId(id);
    }
}


