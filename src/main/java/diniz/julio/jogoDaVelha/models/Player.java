package diniz.julio.jogoDaVelha.models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

@Entity
@Table
public class Player {

	public static final char TYPE_X = 'X';
	public static final char TYPE_O = 'O';

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	private char type;

	private Boolean isTurn;

	public Player() {
	}

	public Player(char type, Boolean isTurn) {

		this.type = type;
		this.isTurn = isTurn;

	}

	public Integer getId() {

		return this.id;

	}

	public void setId(Integer id) {

		this.id = id;

	}

	public char getType() {

		return this.type;

	}

	public void setType(char type) {

		this.type = type;

	}

	public Boolean getTurn() {

		return this.isTurn;

	}

	public void setTurn(Boolean turn) {

		isTurn = turn;

	}
}
