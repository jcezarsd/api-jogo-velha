package diniz.julio.jogoDaVelha.exceptions;

public class ValidationException extends BaseException {

	public ValidationException() {

		super();

	}

	public ValidationException(String messageKey, Object... messageArgs) {

		super(messageKey, messageArgs);

	}

}
