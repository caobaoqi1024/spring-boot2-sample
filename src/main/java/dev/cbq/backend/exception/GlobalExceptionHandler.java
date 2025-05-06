package dev.cbq.backend.exception;

import dev.cbq.backend.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(BusinessException.class)
	public Result<String> handleCustomNotFoundException(BusinessException ex) {
		log.info("GlobalExceptionHandler.handleCustomNotFoundException");
		return Result.failure(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public Result<String> handleAllExceptions(Exception ex) {
		log.info("GlobalExceptionHandler.handleAllExceptions {}", ex.getMessage());
		return Result.error();
	}


}
