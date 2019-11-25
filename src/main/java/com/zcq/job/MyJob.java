package com.zcq.job;

import org.quartz.*;

import java.time.LocalTime;

/**
 * @author Maybeeeee
 * @date 2019/11/25 9:06 下午
 */
@PersistJobDataAfterExecution
public class MyJob implements Job {
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //job每次执行都会创建一个新的对象，所以不要引用外部变量来操作传递
        //jobExecutionContext可以获取jobdetail和 trigger等信息
//        String name = jobExecutionContext.getJobDetail().getJobDataMap().getString("name");
        int count= (int) jobExecutionContext.getJobDetail().getJobDataMap().get("count");
        count++;
        jobExecutionContext.getJobDetail().getJobDataMap().put("count",count);
        LocalTime localTime = LocalTime.now();
        System.out.println(count);

    }
}
