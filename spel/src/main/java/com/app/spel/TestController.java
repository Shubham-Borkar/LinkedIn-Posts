package com.app.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.UserEntity;
import com.app.repository.UserEntityRepository;
import com.app.spel.asp.SampleService;

@RestController
@RequestMapping("/spel")
public class TestController {
	
	@Value("#{1 + 1}")
	private int sum;
	
	@GetMapping("/value-arithmetic-operation")
	public int getSum() {
		return sum;
	}
	
	
	
	
	
	
	
	
	

	
	
	@Value("#{ systemProperties['user.name'] }")
	private String userName;

	@GetMapping("/value-system-properties")
	public String getUserName() {
		return userName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// Use a ternary operator to assign value based on the presence of the system property
	@Value("#{systemProperties['server.name'] != null ? systemProperties['server.name'] : 'Unknown Server'}")
	private String optionalName;

	@GetMapping("/ternary-optional-value-annotation")
	public String getoptionalName() {
		return optionalName;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Generate a random number between 0 and 1 and assign it to randomValue
	@Value("#{T(java.lang.Math).random()}")
	private double randomValue;

	@GetMapping("/random-number")
	public String getRandomNumber() {
		return "Random number is: " + randomValue;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String foo() {
		return "foo return value";
	}

	@Value("#{T(com.app.spel.TestController).foo()}")
	private String fooValue;

	@GetMapping("/method-output")
	public String getMethodOutput() {
		return "Setting Method Output : " + fooValue;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Autowired
	private SampleService sampleService;

	@GetMapping("/aspect-1-args")
	public String aspectOneArg() {
		sampleService.someMethod("Shubham");
		return "Method as per Aspect condition called";
	}

	@GetMapping("/aspect-2-args")
	public String aspectTwoArg() {
		sampleService.someMethod("Shubham", "Vedant");
		return "Method as per Aspect condition called";
	}

	
	
	
	
	
	
	
	
	
	
	
	@Autowired
	private UserEntityRepository userRepo;

	@PostAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
	@GetMapping("/data-access-to-admin-or-owner/{id}")
	public UserEntity getUserInfo(@PathVariable Long id) {

		// get Principle object to verify behavior
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.err.println("Token User Data, Email -> " + ((UserEntity) principal).getEmail() + ", Roles -> "
				+ ((UserEntity) principal).getRoles() + ", User Id -> " + ((UserEntity) principal).getId());
		// Find and return the user info if belong to user or an admin
		UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		System.err.println("Return Object -> " + userEntity);
		return userEntity;
	}

	
	
	
	
	
	
	
	
	

}
