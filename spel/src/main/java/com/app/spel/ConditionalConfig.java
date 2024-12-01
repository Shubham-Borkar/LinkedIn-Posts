package com.app.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
class ConditionalConfig {

    @Bean
    @ConditionalOnExpression("#{systemProperties['user.name'] == 'Shubham'}")
    public UserSpecificBean specificBean() {
        System.err.println("Creating UserSpecificBean");
        return new UserSpecificBean();
    }
}

class UserSpecificBean {}













@RestController
@RequestMapping("/spel")
class CEController {
    @Autowired(required = false)
    private UserSpecificBean bean;

    @GetMapping("/conditional-bean")
    public String apiForMobileOne() {
        if (bean == null) {
            return "Bean not created";
        }
        return "Bean is Created";
    }
}











