package org.lapshovi.MailingService.util;

import org.lapshovi.MailingService.job.MailingListJob;
import org.lapshovi.MailingService.model.MailingList;
import org.quartz.*;

import java.util.Date;

public final class MailingListUtil {

    private MailingListUtil() {
    }

    public static JobDetail buildJobDetail(MailingList mailingList) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("mailingListId", mailingList.getId());
        return JobBuilder.newJob(MailingListJob.class)
                .withIdentity("mailingListJob", "mailingListJobs")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public static Trigger buildTrigger(JobDetail jobDetail, MailingList mailingList) {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "mailingListJobsTrigger")
                .withSchedule(simpleScheduleBuilder)
                .startAt(Date.from(mailingList.getDateTimeStart().toInstant()))
                .build();
    }
}
