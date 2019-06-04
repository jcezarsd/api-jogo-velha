package diniz.julio.jogoDaVelha.helpers;

import diniz.julio.jogoDaVelha.exceptions.ValidationException;
import diniz.julio.jogoDaVelha.models.Game;
import diniz.julio.jogoDaVelha.models.Movement;
import diniz.julio.jogoDaVelha.models.Player;
import diniz.julio.jogoDaVelha.models.Position;

public class GameHelpers {

	/* Cria um novo tabuleiro vazio */
	public static Character[][] newBoard() {

		Character[][] board = new Character[3][3];

		for(int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				board[i][j] = '-';

			}

		}

		return board;

	}

	/* Encontra o player que deve jogar durante a rodada em execução */
	public static Player findTurnPlayer(Game game) {

		if(game.getPlayerOne().getTurn()) {

			return game.getPlayerOne();

		} else {

			return game.getPlayerTwo();

		}

	}

	/* Encontra o player que deve jogar durante a rodada em execução */
	public static void changePlayer(Game game) {

		if(game.getPlayerOne().getTurn()) {

			game.getPlayerOne().setTurn(false);
			game.getPlayerTwo().setTurn(true);

		} else {

			game.getPlayerOne().setTurn(true);
			game.getPlayerTwo().setTurn(false);

		}

	}

	private static void validateMovement(Game game, Movement movement) {

		Position realPosition = GameHelpers.getRealPosition(movement.getPosition());

		if(realPosition.getX() < 0
				|| realPosition.getX() > 3
				|| realPosition.getY() < 0
				|| realPosition.getY() > 3) {

			throw new ValidationException("log.position.outOfBounds").userMessage("position.outOfBounds");

		} else if(GameHelpers.findTurnPlayer(game).getType() != movement.getPlayer()) {

			throw new ValidationException("log.player.cantPlay", movement.getPlayer()).userMessage("player.cantPlay");

		} else if(game.getBoard()[realPosition.getX()][realPosition.getY()] != '-') {

			throw new ValidationException("log.position.filled", movement.getPlayer()).userMessage("position.filled");

		}

	}

	/* Faz o movimento e atualiza o turno dos jogadores */
	public static void doMovement(Game game, Movement movement) {

		GameHelpers.validateMovement(game, movement);

		Position realPosition = GameHelpers.getRealPosition(movement.getPosition());

		game.getBoard()[realPosition.getX()][realPosition.getY()] = movement.getPlayer();

	}

	/* Verifica se os caracteres das linhas são iguais */
	private static Boolean checkRows(Character[][] board) {

		for(int i = 0; i < 3; i++) {

			if(checkIfIsEqual(board[i][0], board[i][1], board[i][2])) {

				return true;

			}

		}

		return false;

	}

	/* Verifica se os caracteres das colunas são iguais */
	private static Boolean checkColumns(Character[][] board) {

		for(int i = 0; i < 3; i++) {

			if(checkIfIsEqual(board[0][i], board[1][i], board[2][i])) {

				return true;

			}

		}

		return false;

	}

	/* Verifica se os caracteres das diagonais são iguais */
	private static Boolean checkDiagonal(Character[][] board) {

		return ((checkIfIsEqual(board[0][0], board[1][1], board[2][2])) || (checkIfIsEqual(board[0][2], board[1][1], board[2][0])));

	}

	/* Verifica se os 3 caracteres informados são iguais e diferentes de hífen (-) */
	private static Boolean checkIfIsEqual(Character c1, Character c2, Character c3) {

		return ((!c1.equals('-')) && (c1.equals(c2)) && (c2.equals(c3)));

	}

	/* Verifica se algum player ja ganhou */
	public static Boolean checkWinner(Character[][] board) {

		return (GameHelpers.checkRows(board) || GameHelpers.checkColumns(board) || GameHelpers.checkDiagonal(board));

	}

	/* Verifica se o tabuleiro ja esta completo e não houve ganhadores */
	public static Boolean isDraw(Character[][] board) {

		boolean isDraw = true;

		for(int i = 0; i < 3; i++) {

			for(int j = 0; j < 3; j++) {

				if (board[i][j].equals('-')) {

					isDraw = false;

				}

			}

		}

		return isDraw;

	}

	/*
		Pega a posição real do tabuleiro, onde o tabuleiro utilizado no jogo é:

		-------------------
		| 0,2 | 1,2 | 2,2 |
		| 0,1 | 1,1 | 2,1 |
		| 0,0 | 1,0 | 2,0 |
		-------------------

		E a posição utilizada no código é uma matrix sequencial normal:

		-------------------
		| 0,0 | 0,1 | 0,2 |
		| 1,0 | 1,1 | 1,2 |
		| 2,0 | 2,1 | 2,2 |
		-------------------
	*/
	private static Position getRealPosition(Position position) {

		if(position.getX() == 0 && position.getY() == 0) {

			return new Position(2, 0);

		} else if(position.getX() == 2 && position.getY() == 0) {

			return new Position(2, 2);

		} else if(position.getX() == 2 && position.getY() == 2) {

			return new Position(0, 2);

		} else if(position.getX() == 0 && position.getY() == 2) {

			return new Position(0, 0);

		} else if(position.getX() == 1 && position.getY() == 2) {

			return new Position(0, 1);

		} else if(position.getX() == 1 && position.getY() == 0) {

			return new Position(2, 1);

		} else {

			return new Position(position.getY(), position.getX());

		}

	}

}
