package com.numble.banking.exception.dto;

import com.numble.banking.exception.ErrorCode;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

	private final ErrorInfo errorInfo;
	private final List<RequestFieldError> fieldErrors;

	public static ErrorResponse of(MethodArgumentNotValidException e) {
		List<RequestFieldError> errors = RequestFieldError.of(e.getBindingResult());
		return new ErrorResponse(ErrorInfo.of(ErrorCode.INVALID_INPUT_VALUE), errors);
	}

	public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
		List<RequestFieldError> errors =
			RequestFieldError.of(e.getName(), String.valueOf(e.getValue()), e.getErrorCode());

		return new ErrorResponse(ErrorInfo.of(ErrorCode.INVALID_INPUT_VALUE), errors);
	}
}
