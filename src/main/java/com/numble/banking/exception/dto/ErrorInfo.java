package com.numble.banking.exception.dto;

import com.numble.banking.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ErrorInfo {

	private final String error;
	private final String message;
	private final String code;
	private final LocalDateTime createTime;

	@Builder
	private ErrorInfo(String error, String message, String code, LocalDateTime createTime) {
		this.error = error;
		this.message = message;
		this.code = code;
		this.createTime = createTime;
	}

	public static ErrorInfo of(ErrorCode code) {
		return ErrorInfo.builder()
			.error(code.getStatus().name())
			.code(code.getCode())
			.message(code.getMessage())
			.createTime(LocalDateTime.now())
			.build();
	}
}
