package org.lapshovi.MailingService.controller;

import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.service.MailingListService;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MailingListController {

    private MailingListService mailingListService;

    public MailingListController(MailingListService mailingListService) {
        this.mailingListService = mailingListService;
    }

    @PostMapping(value = "/mailingList")
    public ResponseEntity<?> save(@RequestBody @Valid MailingList mailingList) throws SchedulerException {
        mailingListService.saveMailingList(mailingList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/mailingList/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid MailingList mailingList) throws SchedulerException {
        mailingListService.updateMailingList(id, mailingList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/mailingList/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        mailingListService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
