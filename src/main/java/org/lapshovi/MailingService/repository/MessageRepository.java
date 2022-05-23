package org.lapshovi.MailingService.repository;

import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllByMailingList(MailingList mailingList);
}
