package org.lapshovi.MailingService.repository;

import org.lapshovi.MailingService.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> getAllByTagOrOperatorsCode(String tag, String operatorsCode);
}
