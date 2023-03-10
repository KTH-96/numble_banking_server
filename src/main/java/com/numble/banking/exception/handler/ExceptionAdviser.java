package com.numble.banking.exception.handler;

import com.numble.banking.exception.ApplicationException;
import com.numble.banking.exception.dto.ErrorInfo;
import com.numble.banking.exception.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler(ApplicationException.class)
	private ResponseEntity<ErrorInfo> applicationExceptionHandler(ApplicationException e) {
		return ErrorInfo.toResponseEntity(e.getErrorCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(
		MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(ErrorResponse.of(e));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<ErrorResponse> methodArgumentTypeMismatchExceptionHandler(
		MethodArgumentTypeMismatchException e) {
		return ResponseEntity.badRequest().body(ErrorResponse.of(e));
	}
}
