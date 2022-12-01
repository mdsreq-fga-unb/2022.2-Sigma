package br.unb.sigma.controller.exception;

public class RegisterNotFoundException extends RuntimeException {
	public RegisterNotFoundException() {
		super("Register not found");
	}
}
