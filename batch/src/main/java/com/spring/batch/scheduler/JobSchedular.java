package com.spring.batch.scheduler;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobSchedular {

	@Autowired
	private JobLauncher jobLauncher;
	
	// Edited Config using @Bean
	@Autowired
	private Job dbToFileBatchJob;
	
	@Scheduled(fixedDelay = 60000)
	void trigger() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		String fileName = LocalDateTime.now().toString().concat("_product.csv");
		
		 fileName = fileName.replace(":", "_");
		
		JobParameters jobParameters = new JobParametersBuilder()
		.addDate("processed at", new Date())
		.addString("output.file.name", fileName)
		.toJobParameters();
		
		this.jobLauncher.run(dbToFileBatchJob, jobParameters);
		 
	}

}








