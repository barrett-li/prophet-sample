/**
 * Prophet 1.0
 * CHINA RESOFT COMPUTER SYSTEM ENGINEERING CO.,LTD
 * 
 * http://resoft.css.com.cn
 */


package com.resoft.prophet.scheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 一个简单的Quartz Job。<br/>
 * 
 * @author 伍军军
 * */
public class SimpleQuartzJob implements Job {

    public SimpleQuartzJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("In com.resoft.prophet.scheduler.SimpleQuartzJob - executing its JOB at "
				+ new Date() + " by " + context.getTrigger().getName());
    }
}
