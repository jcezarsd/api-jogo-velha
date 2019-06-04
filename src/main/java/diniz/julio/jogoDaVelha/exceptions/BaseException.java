package diniz.julio.jogoDaVelha.exceptions;

import diniz.julio.jogoDaVelha.helpers.MessagesHelpers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class BaseException extends RuntimeException {

	private String messageKey;
	private Object [] messageArgs;

	public BaseException() {

	}

	public BaseException(Throwable throwable) {

		super(throwable);

	}

	public BaseException(String messageKey, Object... args) {

		super(MessagesHelpers.get(messageKey, args));

	}

	public String getUserMessage() {

		return MessagesHelpers.get(messageKey, messageArgs);

	}

	public BaseException userMessage(String messageKey, Object... messageArgs) {

		this.messageArgs = messageArgs;
		this.messageKey = messageKey;

		return this;

	}

}
