package br.com.hievents.utils.exception;

import java.util.List;

import br.com.hievents.enums.ErrorResponseEnum;
import br.com.hievents.utils.dto.ErrorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class GeneralExceptionErrorList extends DefaultException {

	private List<ErrorResponseDTO> errors;
	
	@Override
	public ErrorResponse getErrorResponse() {
		return ErrorResponseEnum.GENERAL_ERROR_VALIDATION;
	}

}
