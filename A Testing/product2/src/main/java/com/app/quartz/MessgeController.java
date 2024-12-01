package com.app.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/quartz-schedular")
public class MessgeController {

	@Autowired
	private MessageSenderSimpleJob messageSenderSimpleJob;

	@PostMapping("/message/{id}/{message}")
	public ResponseEntity<?> scheduleMessage(@PathVariable int id, @PathVariable String message) {
		try {

			messageSenderSimpleJob.simpleJobAsPerMentionedTime(id, message);

			return ResponseEntity.status(HttpStatus.OK).body("Message Scheduled Successfully");

		} catch (SchedulerException se) {
			log.error("Error while Sceduling ->{}", se.getMessage());
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error in Schedular");
		}
	}

}

