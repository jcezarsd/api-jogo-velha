package diniz.julio.jogoDaVelha.models;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	private String winner;

	private Character[][] board;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_player_one")
	private Player playerOne;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_player_two")
	private Player playerTwo;

	public Game() {

		Random r = new Random();
		char typeFirstPlayer = r.nextBoolean() ? Player.TYPE_X : Player.TYPE_O;
		char typeSecondPlayer = typeFirstPlayer == Player.TYPE_X ? Player.TYPE_O : Player.TYPE_X;

		this.board = new Character[3][3];
		this.playerOne = new Player(typeFirstPlayer, true);
		this.playerTwo = new Player(typeSecondPlayer, false);

	}

	public String getWinner() {

		return this.winner;

	}

	public void setWinner(String winner) {

		this.winner = winner;

	}

	public Player getPlayerOne() {

		return this.playerOne;

	}

	public Player getPlayerTwo() {

		return this.playerTwo;

	}

	public Integer getId() {

		return this.id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	public Character[][] getBoard() {

		return this.board;

	}

	public void setBoard(Character[][] board) {

		this.board = board;

	}

	public void setPlayerOne(Player playerOne) {

		this.playerOne = playerOne;

	}

	public void setPlayerTwo(Player playerTwo) {

		this.playerTwo = playerTwo;

	}
}
