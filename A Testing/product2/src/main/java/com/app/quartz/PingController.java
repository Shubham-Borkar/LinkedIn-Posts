package com.app.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PingController {
	@Autowired
	private PingClassCronJob pingClassCronJob;

	@PostMapping("/ping/{startedBy}")
	public ResponseEntity<?> pingSchedular(@PathVariable String startedBy) {
		try {

			pingClassCronJob.cronJobAsPerExpression(startedBy);

			return ResponseEntity.status(HttpStatus.OK).body("Message Scheduled Successfully");

		} catch (SchedulerException se) {
			log.error("Error while Sceduling ->{}", se.getMessage());
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Error in Schedular");
		}
	}

}








//String expression = "* "
//+ startmin+"-"+endmin+" "
//+ hours+"-"+hoursplusone+" "
//+ date+" "
//+ month+" "
//+ "? "
//+ year;
