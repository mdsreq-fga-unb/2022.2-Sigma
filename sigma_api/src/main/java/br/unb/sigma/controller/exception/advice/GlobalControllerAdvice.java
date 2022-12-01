package br.unb.sigma.controller.exception.advice;

import br.unb.sigma.controller.exception.DatabaseOperationException;
import br.unb.sigma.controller.exception.RegisterNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(RegisterNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleRegisterNotFoundException(RegisterNotFoundException e) {
		var response = new ExceptionResponse(
				"Registro não encontrado",
				new Date(),
				e.getMessage()
		);

		return ResponseEntity.status(NOT_FOUND).body(response);
	}

	@ExceptionHandler(DatabaseOperationException.class)
	public ResponseEntity<ExceptionResponse> handleDatabaseOperationException(DatabaseOperationException e) {
		var response = new ExceptionResponse(
				"Erro ao realizar operação no banco de dados",
				new Date(),
				e.getMessage()
		);

		return ResponseEntity.status(BAD_REQUEST).body(response);
	}
}
