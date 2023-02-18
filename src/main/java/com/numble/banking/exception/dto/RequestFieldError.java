package com.numble.banking.exception.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RequestFieldError {

	private final String field;
	private final String value;
	private final String reason;

	private RequestFieldError(FieldError fieldError) {
		this.field = fieldError.getField();
		this.value = fieldError.getRejectedValue() == null ? "" : String.valueOf(fieldError.getRejectedValue());
		this.reason = fieldError.getDefaultMessage();
	}


	public static List<RequestFieldError> of(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		return fieldErrors.stream()
			.map(RequestFieldError::new)
			.collect(Collectors.toList());
	}

	public static List<RequestFieldError> of(String field, String value, String reason) {
		List<RequestFieldError> requestFieldErrors = new ArrayList<>();
		requestFieldErrors.add(new RequestFieldError(field, value, reason));
		return requestFieldErrors;
	}
}
