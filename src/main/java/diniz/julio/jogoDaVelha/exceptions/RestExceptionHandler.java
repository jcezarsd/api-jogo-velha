package diniz.julio.jogoDaVelha.exceptions;

import diniz.julio.jogoDaVelha.helpers.MessagesHelpers;
import diniz.julio.jogoDaVelha.responses.ValidationErrorResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private Log logger = LogFactory.getLog(RestExceptionHandler.class);

	@ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {

		logger.error(ex.getMessage(), ex);

		return handleExceptionInternal(
				ex,
				new ValidationErrorResponse(MessagesHelpers.get("error.default")),
				new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR,
				request
		);

	}

	@ExceptionHandler(value = {BaseException.class})
	protected ResponseEntity<Object> handleConflict(BaseException ex, WebRequest request) {

		logger.error(ex.getMessage(), ex);

		return handleExceptionInternal(
				ex,
				new ValidationErrorResponse(ex.getUserMessage()),
				new HttpHeaders(),
				HttpStatus.NOT_IMPLEMENTED,
				request
		);

	}

	@ExceptionHandler(value = {ValidationException.class})
	protected ResponseEntity<Object> handleConflict(ValidationException ex, WebRequest request) {

		logger.error(ex.getMessage(), ex);

		return handleExceptionInternal(
				ex,
				new ValidationErrorResponse(ex.getUserMessage()),
				new HttpHeaders(),
				HttpStatus.UNPROCESSABLE_ENTITY,
				request
		);

	}

}
