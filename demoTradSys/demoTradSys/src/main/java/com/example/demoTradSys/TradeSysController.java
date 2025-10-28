package com.example.demoTradSys;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TradeSysController {
	
	@GetMapping("/health-check")
	public String getHealthCheck() {
		return "Health is Normal";
	}
	

}
