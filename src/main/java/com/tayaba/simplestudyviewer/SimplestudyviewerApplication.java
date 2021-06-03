package com.tayaba.simplestudyviewer;

import com.tayaba.simplestudyviewer.web.api.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimplestudyviewerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplestudyviewerApplication.class, args);
	}
}
