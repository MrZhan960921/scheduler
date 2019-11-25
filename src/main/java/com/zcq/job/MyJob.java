package com.zcq.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * @author Maybeeeee
 * @date 2019/11/25 9:06 下午
 */
public class MyJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime=LocalTime.now();
        System.out.println("我正在执行"+localTime.toString());
    }
}
