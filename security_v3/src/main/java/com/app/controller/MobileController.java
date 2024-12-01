package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
public class MobileController {
	
	@GetMapping("/one")
	public String apiForMobileOne() {
		return "Sample Response from \"/mobile/one api\", No Auth Required";
	}
	
	@GetMapping("/two")
	public String apiForMobileTwo() {
		return "Sample Response from \"/mobile/two api\", No Auth Required";
	}


}
