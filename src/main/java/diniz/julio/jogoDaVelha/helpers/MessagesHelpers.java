package diniz.julio.jogoDaVelha.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Ajuda com o acesso ao messages.properties em codigo.
 *
 * Encontra os arquivos de messages em src/main/resources
 *
 * Locale default
 *
 */
@Component
public class MessagesHelpers {

	@Autowired
	private MessageSource messageSource;

	private static MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {

		accessor = new MessageSourceAccessor(messageSource);

	}

	public static String get(String code, Object... args) {

		return accessor.getMessage(code, args);

	}

}
