package com.zcq.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * @author Maybeeeee
 * @date 2019/11/25 9:06 下午
 */
@DisallowConcurrentExecution
public class MyJob implements Job {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //job每次执行都会创建一个新的对象，所以不要引用外部变量来操作传递
        //jobExecutionContext可以获取jobdetail和 trigger等信息
//        String name = jobExecutionContext.getJobDetail().getJobDataMap().getString("name");
        LocalTime localTime = LocalTime.now();
        System.out.println(Thread.currentThread().getName() + "开始执行" + localTime.toString());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime endTime = LocalTime.now();
        System.out.println(Thread.currentThread().getName() + "结束执行" + endTime.toString());
    }
}
