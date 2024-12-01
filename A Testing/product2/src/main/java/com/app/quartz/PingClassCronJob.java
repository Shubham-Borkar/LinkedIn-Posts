package com.app.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PingClassCronJob implements Job {
	
	@Autowired
	private Scheduler scheduler;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap jobDataMap=context.getMergedJobDataMap();
		String startedBy=jobDataMap.getString("Started_By");
		
		log.info("Cron Job for Ping, Started By -> {} , Executed At -> {}",startedBy , new Date());
	}
	
	public void cronJobAsPerExpression(String startedBy) throws SchedulerException {
		JobDataMap jobDataMap=new JobDataMap();
		jobDataMap.put("Started_By", startedBy );
		
		JobDetail jobDetails = JobBuilder.newJob(PingClassCronJob.class)
		 .withIdentity("Shubham"+UUID.randomUUID().toString(), "ping-job")
		 .withDescription("Ping as per Cron Given")
		 .usingJobData(jobDataMap)
		 .storeDurably()
		 .build();
		
		String expression = "*/5 * * * * ?";

		Calendar calendar = Calendar.getInstance();		
		// Add one minute to the current time
		calendar.add(Calendar.MINUTE, 1);
		Date endTime = calendar.getTime();

		CronTrigger cronTrigger = TriggerBuilder.newTrigger()
		 .forJob(jobDetails)
		 .withIdentity(jobDetails.getKey().getName(),"ping-trigger")
		 .withDescription("Send Ping Trigger")
		 .startAt(new Date())
		 .endAt(endTime)
		 .withSchedule(CronScheduleBuilder.cronSchedule(expression))
		 .build();
		
		log.info("Scheduled at -> "+ new Date());
		scheduler.scheduleJob(jobDetails, cronTrigger);		
	}
	

	
}



















