package br.unb.sigma.controller.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseOperationException extends RuntimeException {
	public DatabaseOperationException(DataIntegrityViolationException ex) {
		super(ex.getCause().getMessage());
	}
}
