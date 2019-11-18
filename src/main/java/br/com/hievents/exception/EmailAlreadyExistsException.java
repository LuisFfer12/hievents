package br.com.hievents.exception;

import br.com.hievents.enums.ErrorResponseEnum;
import br.com.hievents.utils.exception.DefaultException;
import br.com.hievents.utils.exception.ErrorResponse;

@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends DefaultException{

	@Override
	public ErrorResponse getErrorResponse() {
		return ErrorResponseEnum.EMAIL_ALREADY_EXISTS;
	}
}
