package br.com.hievents.exception;

import br.com.hievents.enums.ErrorResponseEnum;
import br.com.hievents.utils.exception.DefaultException;
import br.com.hievents.utils.exception.ErrorResponse;

@SuppressWarnings("serial")
public class AnuncianteNotFoundException extends DefaultException{

	@Override
	public ErrorResponse getErrorResponse() {
		return ErrorResponseEnum.ANUNCIANTE_NOT_FOUND;
	}
} 
