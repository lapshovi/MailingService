package org.lapshovi.MailingService.job;

import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.model.Message;
import org.lapshovi.MailingService.service.MailingListService;
import org.lapshovi.MailingService.service.MessageService;
import org.lapshovi.MailingService.service.ScedulerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import java.time.OffsetDateTime;
import java.util.List;

public class UpdateMailingListJob implements Job {
    private final MailingListService mailingListService;
    private final ScedulerService scedulerService;
    private final MessageService messageService;

    public UpdateMailingListJob(MailingListService mailingListService, ScedulerService scedulerService, MessageService messageService) {
        this.mailingListService = mailingListService;
        this.scedulerService = scedulerService;
        this.messageService = messageService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        List<MailingList> mailingLists = mailingListService.getAllByDateTimeStartBeforeAndDateTimeStartAfterAndDateTimeEndAfter(
                OffsetDateTime.now(), OffsetDateTime.now().minusHours(1), OffsetDateTime.now());
        for (MailingList mailingList : mailingLists) {
            try {
                List<Message> messageList = messageService.getAllByMailingList(mailingList);
                for (Message message : messageList) {
                    if (!message.getStatus().equals("Отправлено")) {
                        scedulerService.schedule(mailingList);
                        break;
                    }
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}




