package dev.cbq.backend.controller;

import dev.cbq.backend.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/business-ex")
	public ResponseEntity<String> businessException() {
		throw new BusinessException("Business Exception");
	}

	@GetMapping("/illegal-access-ex")
	public ResponseEntity<String> illegalAccessException() throws IllegalAccessException {
		throw new IllegalAccessException("Business Exception");
	}

}
