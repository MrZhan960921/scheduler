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
        //job每次执行都会创建一个新的对象，所以不要引用外部变量来操作传递
        //jobExecutionContext可以获取jobdetail和 trigger等信息
        String name = jobExecutionContext.getJobDetail().getJobDataMap().getString("name");
        LocalTime localTime = LocalTime.now();
        System.out.println(name + "正在执行" + localTime.toString());
    }
}
