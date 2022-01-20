package com.mercadolibre.examen.error;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataException(String customMessage) {
        super(customMessage);
    }

}