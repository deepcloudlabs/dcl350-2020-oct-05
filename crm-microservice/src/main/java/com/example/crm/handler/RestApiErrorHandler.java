package com.example.crm.handler;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crm.dto.CrmApiErrorCodes;
import com.example.crm.dto.RestErrorMessage;

@RestControllerAdvice
public class RestApiErrorHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
		return new RestErrorMessage(
				e.getMessage(), 
				CrmApiErrorCodes.MISSING_CUSTOMER.getErrorId(), 
				"2c2996af-1887-4bd6-ae8b-2d41cac12b7b"
		);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleRuntimeException(RuntimeException e) {
		return new RestErrorMessage(
				e.getMessage(), 
				CrmApiErrorCodes.BAD_BACKEND.getErrorId(), 
				"29cb60df-5edf-4502-9488-e1a34b90c2ca"
				);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleConstraintViolationException(ConstraintViolationException e) {
		var violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining("|"));
		return new RestErrorMessage(violations, CrmApiErrorCodes.BAD_CUSTOMER.getErrorId(), "7c248b97-9aa8-46fd-b5b2-9f8ea537635c");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		var violations = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
		return new RestErrorMessage(violations, CrmApiErrorCodes.BAD_PARAMETER.getErrorId(), "7c248b97-9aa8-46fd-b5b2-9f8ea537635c");
	}
}
