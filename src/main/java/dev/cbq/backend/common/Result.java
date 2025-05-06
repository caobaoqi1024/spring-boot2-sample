package dev.cbq.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	private static final String REQUEST_ID = "request-id";
	private static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
	private static final String DEFAULT_ERROR_MESSAGE = "内部错误 请联系管理员";

	private Long id;
	private Integer code;
	private String message;
	private T data;

	public static <T> Result<T> success(T data) {
		return new Result<>(requestId(), HttpStatus.OK.value(), DEFAULT_SUCCESS_MESSAGE, data);
	}

	public static <T> Result<T> failure(int code, String message) {
		return new Result<>(requestId(), code, message, null);
	}

	public static <T> Result<T> error() {
		return new Result<>(requestId(), HttpStatus.INTERNAL_SERVER_ERROR.value(), DEFAULT_ERROR_MESSAGE, null);
	}

	public static <T> Result<T> forbidden(String message) {
		return failure(HttpStatus.FORBIDDEN.value(), message);
	}

	public static <T> Result<T> unauthorized(String message) {
		return failure(HttpStatus.UNAUTHORIZED.value(), message);
	}


	private static long requestId() {
		String requestId = Optional.ofNullable(MDC.get(REQUEST_ID)).orElse("0");
		try {
			return Long.parseLong(requestId);
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

}
