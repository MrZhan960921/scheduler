package com.zcq;

import com.zcq.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        JobDetail jobDetail=JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail1","group1")
                .build();

        Trigger trigger=TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(10)
                        .repeatForever()
                ).build();

        scheduler.scheduleJob(jobDetail,trigger);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
