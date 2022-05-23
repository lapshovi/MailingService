package org.lapshovi.MailingService.service;

import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.repository.MailingListRepository;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class MailingListService {
    private final MailingListRepository mailingListRepository;
    private final ScedulerService scedulerService;

    @Autowired
    public MailingListService(MailingListRepository mailingListRepository, ScedulerService scedulerService) {
        this.mailingListRepository = mailingListRepository;
        this.scedulerService = scedulerService;
    }

    public MailingList findById(Long id) {
        return mailingListRepository.getById(id);
    }

    @Transactional
    public void saveMailingList(MailingList mailingList) throws SchedulerException {
        if (mailingList.getDateTimeStart().isAfter(mailingList.getDateTimeEnd()))
            throw new IllegalArgumentException("Время конца рассылки должно быть позже времени начала рассылки");
        mailingListRepository.save(mailingList);
        scedulerService.schedule(mailingList);
    }

    @Transactional
    public void updateMailingList(Long id, MailingList mailingList) throws SchedulerException {
        MailingList updateMailingList = mailingListRepository.getById(id);
        updateMailingList.setDateTimeStart(mailingList.getDateTimeStart());
        updateMailingList.setDateTimeEnd(mailingList.getDateTimeEnd());
        updateMailingList.setFilter(mailingList.getFilter());
        updateMailingList.setMessage(mailingList.getMessage());
        saveMailingList(mailingList);
    }

    public void deleteById(Long id) {
        mailingListRepository.deleteById(id);
    }

    public List<MailingList> getAllByDateTimeStartBeforeAndDateTimeStartAfterAndDateTimeEndAfter(OffsetDateTime dateTimeStartBefore,
                                                                                                 OffsetDateTime dateTimeStartAfter,
                                                                                                 OffsetDateTime dateTimeEndAfter) {
        return mailingListRepository.getAllByDateTimeStartBeforeAndDateTimeStartAfterAndDateTimeEndAfter(dateTimeStartBefore,
                dateTimeStartAfter, dateTimeEndAfter);
    }
}

