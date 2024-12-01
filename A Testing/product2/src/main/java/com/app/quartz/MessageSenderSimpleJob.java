package com.app.quartz;




import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageSenderSimpleJob extends QuartzJobBean {
	
	@Autowired
	private Scheduler schedular;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap jobDataMap=context.getMergedJobDataMap();
		int id=jobDataMap.getInt("userId");
		String message=jobDataMap.getString("message");
		
		log.info("Simple Job for Sending mail, Excecuted At -> {} for user with id -> {} , message-> {} "
														,new Date(),id,message);
	}

	
	public void simpleJobAsPerMentionedTime(int id, String message) throws SchedulerException  {
		
		JobDataMap jobDataMap=new JobDataMap();
		jobDataMap.put("userId", id);
		jobDataMap.put("message", message);
		
		JobDetail jobDetails = JobBuilder.newJob(MessageSenderSimpleJob.class)
						 .withIdentity("Shubham"+UUID.randomUUID().toString(),"message-job")
						 .withDescription("Send message to User by id given")
						 .usingJobData(jobDataMap)
						 .storeDurably()
						 .build();
		
		Calendar calendar = Calendar.getInstance();
		// Add one minute to the current time
		calendar.add(Calendar.MINUTE, 1);
		Date endTime = calendar.getTime();

		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
							 .forJob(jobDetails)
							 .withIdentity(jobDetails.getKey().getName(),"message-trigger")
							 .withDescription("Send message Trigger")
							 .startAt(endTime)
							 .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
							 .build();
		
		log.info("Scheduled at -> "+ new Date());
		schedular.scheduleJob(jobDetails, simpleTrigger);
		
	}
}























