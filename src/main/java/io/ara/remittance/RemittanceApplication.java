package io.ara.remittance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class RemittanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemittanceApplication.class, args);
	}

}
