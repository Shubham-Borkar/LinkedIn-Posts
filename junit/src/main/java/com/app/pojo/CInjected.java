package com.app.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CInjected {
	private String one;
	private String two;
	
	//builder method to set field one
	public CInjected buildOne(String onee) {
		System.out.println("Setting field one at "+LocalDateTime.now().toString());
	this.setOne(onee);
	return this;
	}
	
	//builder method to set field one
	public CInjected buildTwo(String twoo) {
		System.out.println("Setting field two at "+LocalDateTime.now().toString());
	this.setTwo(twoo);
	return this;
	}

	
}
