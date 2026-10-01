package com.swagger.coderestapi.exception;

public class PartnerNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2633859207042344237L;

	public PartnerNotFoundException(String message) {
        super(message);
    }
}