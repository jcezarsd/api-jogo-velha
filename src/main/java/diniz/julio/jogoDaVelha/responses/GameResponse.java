package diniz.julio.jogoDaVelha.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameResponse {

	private Integer id;

	private Character firstPlayer;

	private String msg;

	private String winner;

	public GameResponse() {

	}

	public GameResponse(Integer id, Character firstPlayer) {

		this.id = id;
		this.firstPlayer = firstPlayer;

	}

	public Integer getId() {

		return this.id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	public Character getFirstPlayer() {

		return this.firstPlayer;

	}

	public void setFirstPlayer(Character firstPlayer) {

		this.firstPlayer = firstPlayer;

	}

	public String getMsg() {

		return this.msg;

	}

	public void setMsg(String msg) {

		this.msg = msg;

	}

	public String getWinner() {

		return this.winner;

	}

	public void setWinner(String winner) {

		this.winner = winner;

	}
}
