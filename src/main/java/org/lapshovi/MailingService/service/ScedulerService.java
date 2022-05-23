package org.lapshovi.MailingService.service;

import org.lapshovi.MailingService.job.UpdateMailingListJob;
import org.lapshovi.MailingService.model.MailingList;
import org.lapshovi.MailingService.util.MailingListUtil;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class ScedulerService {
    private final Scheduler scheduler;

    @Autowired
    public ScedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(MailingList mailingList) throws SchedulerException {
        JobDetail jobDetail = MailingListUtil.buildJobDetail(mailingList);
        Trigger trigger = MailingListUtil.buildTrigger(jobDetail, mailingList);
        try {
            if (scheduler.checkExists(jobDetail.getKey())) {
                scheduler.deleteJob(jobDetail.getKey());
            }

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public void scheduleUpdate() throws SchedulerException {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .repeatForever()
                .withIntervalInHours(1);
        JobDetail updateMailingListJob = JobBuilder.newJob(UpdateMailingListJob.class)
                .withIdentity("updateMailingListJob", "mailingListJobs")
                .storeDurably()
                .build();
        Trigger updateTrigger = TriggerBuilder.newTrigger()
                .withIdentity(updateMailingListJob.getKey().getName(), "updateMailingListJobsTrigger")
                .withSchedule(simpleScheduleBuilder)
                .forJob(updateMailingListJob)
                .startNow()
                .build();
        scheduler.scheduleJob(updateMailingListJob, updateTrigger);
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
