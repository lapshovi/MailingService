package org.lapshovi.MailingService.repository;

import org.lapshovi.MailingService.model.MailingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface MailingListRepository extends JpaRepository<MailingList, Long> {
    List<MailingList> getAllByDateTimeStartBeforeAndDateTimeStartAfterAndDateTimeEndAfter(OffsetDateTime dateTimeStartBefore,
                                                                                          OffsetDateTime dateTimeStartAfter,
                                                                                          OffsetDateTime dateTimeEndAfter);
}
