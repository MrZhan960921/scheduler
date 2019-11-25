package com.zcq;

import com.zcq.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Random;

public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        Random random=new Random();
        int count=random.nextInt(10);
        JobDetail jobDetail=JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail1","group1")
                .usingJobData("count",count)
                .build();

        Trigger trigger=TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
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
