package com.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MiniMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniMarketApplication.class, args);
		BCryptPasswordEncoder x = new BCryptPasswordEncoder();
		System.out.println(x.encode("kasir"));
	}
}
