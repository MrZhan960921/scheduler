package com.zcq.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.*;

import java.time.LocalTime;

/**
 * @author Maybeeeee
 * @date 2019/11/25 9:06 下午
 */
//@PersistJobDataAfterExecution
public class MyJob implements Job {
    @Setter
    @Getter
    private String msg;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString()+"，msg="+msg);

    }
}
