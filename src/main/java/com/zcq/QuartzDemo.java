package com.zcq;

import com.zcq.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Random;

public class QuartzDemo {
    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail jobDetail1=JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail1","group1")
                .build();

        JobDetail jobDetail2=JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail2","group1")
                .build();

        Date date=DateBuilder.futureDate(5,DateBuilder.IntervalUnit.SECOND);
        Trigger trigger1=TriggerBuilder.newTrigger()
                .startAt(date)
                .usingJobData("msg","trigger1")
                //设置优先级，只有在同等时间且线程不够的情况下才会触发优先级
                .withPriority(1)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5)
                        .repeatForever()
                ).build();

        Trigger trigger2=TriggerBuilder.newTrigger()
                .startAt(date)
                .usingJobData("msg","trigger2")
                .withPriority(2)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever()
                ).build();
        //一个jobDetail只能对应一个trigger
        scheduler.scheduleJob(jobDetail1,trigger1);
        scheduler.scheduleJob(jobDetail2,trigger2);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
