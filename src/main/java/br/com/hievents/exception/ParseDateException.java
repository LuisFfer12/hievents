package br.com.hievents.exception;

import br.com.hievents.enums.ErrorResponseEnum;
import br.com.hievents.utils.exception.DefaultException;
import br.com.hievents.utils.exception.ErrorResponse;

@SuppressWarnings("serial")
public class ParseDateException extends DefaultException{
	
	@Override
	public ErrorResponse getErrorResponse() {
		return ErrorResponseEnum.PARSE_DATE_ERROR;
	}
}
