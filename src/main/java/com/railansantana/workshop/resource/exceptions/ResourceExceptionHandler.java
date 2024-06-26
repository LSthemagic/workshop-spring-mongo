package com.railansantana.workshop.resource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.railansantana.workshop.services.exceptions.ObjectNotFound;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFound.class)
	public ResponseEntity<StandardError> notFound(ObjectNotFound e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not Found - Não encontrado",
				e.getMessage(), req.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
