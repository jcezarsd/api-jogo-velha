package diniz.julio.jogoDaVelha.responses;

public class ValidationErrorResponse {

	private final String msg;

	public ValidationErrorResponse(String msg) {

		this.msg = msg;

	}

	public String getMsg() {

		return this.msg;

	}
}
