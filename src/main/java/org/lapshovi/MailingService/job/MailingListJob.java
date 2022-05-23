package org.lapshovi.MailingService.job;

import org.lapshovi.MailingService.service.JobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class MailingListJob implements Job {
    final JobService jobService;

    public MailingListJob(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        jobService.filterAndSendMessages(jobExecutionContext.getJobDetail().getJobDataMap().getLong("mailingListId"));
    }
}
