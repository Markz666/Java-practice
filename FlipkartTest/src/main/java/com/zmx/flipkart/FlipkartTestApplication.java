package com.zmx.flipkart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class FlipkartTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipkartTestApplication.class, args);
		System.out.println("flipkart main start ok");
	}
	
	@RequestMapping(value = "/home")  
    public String home() {  
        System.out.println("home");  
        return "Hello World";  
    }  
}
