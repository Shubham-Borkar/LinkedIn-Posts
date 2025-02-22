package com.app.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationChecker {
	@NotBlank(message="Can not be blank, null at least 1 non whitespace needed ")
	private String notBlank;
	@NotNull(message = "Can not be null, Allowed -> Empty and 1 whitespace")
	private String notNull;
	@NotEmpty(message = "Can not be Empty or blank at least 1 whitespace needed")
	private String notEmpty;
	@Size(min=3,max=5,message="min 3 and max 5")
	private String size;
	@Past
	private LocalDateTime past;
	@Future
	private LocalDateTime future;
	@PastOrPresent
	private LocalDateTime pastOrPresent;
	@FutureOrPresent
	private LocalDateTime futureOrPresent;
	
	
	

}
