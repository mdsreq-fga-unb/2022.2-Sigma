package br.unb.sigma.controller.exception.advice;

import java.util.Date;

public record ExceptionResponse(
		String message,
		Date timestamp,
		String details
) {
}
