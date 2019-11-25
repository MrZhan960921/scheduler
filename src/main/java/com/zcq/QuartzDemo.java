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



        Date date=DateBuilder.futureDate(5,DateBuilder.IntervalUnit.SECOND);
        Trigger trigger1=TriggerBuilder.newTrigger()
                .startAt(date)
                .usingJobData("msg","trigger1")
                //设置优先级，只有在同等时间且线程不够的情况下才会触发优先级
                .withPriority(1)
                //cron表达式周和月内每一天有冲突，有一个要用？表示什么都不匹配防止冲突
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                        .withMisfireHandlingInstructionFireAndProceed()
                )
                .build();


        //一个jobDetail只能对应一个trigger
        scheduler.scheduleJob(jobDetail1,trigger1);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
