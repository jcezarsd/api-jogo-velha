package diniz.julio.jogoDaVelha.services.impl;

import diniz.julio.jogoDaVelha.exceptions.BaseException;
import diniz.julio.jogoDaVelha.exceptions.ValidationException;
import diniz.julio.jogoDaVelha.helpers.GameHelpers;
import diniz.julio.jogoDaVelha.helpers.MessagesHelpers;
import diniz.julio.jogoDaVelha.models.Game;
import diniz.julio.jogoDaVelha.models.Movement;
import diniz.julio.jogoDaVelha.repositories.GameRepository;
import diniz.julio.jogoDaVelha.responses.GameResponse;
import diniz.julio.jogoDaVelha.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

	private GameRepository gameRepository;

	@Autowired
	public GameServiceImpl(GameRepository gameRepository) {

		this.gameRepository = gameRepository;

	}

	@Override
	public GameResponse createGame() {

		Game game = new Game();
		game.setBoard(GameHelpers.newBoard());

		this.gameRepository.save(game);

		return new GameResponse(game.getId(), game.getPlayerOne().getType());

	}

	@Override
	public GameResponse doMovement(Movement movement) {

		Game game = this.gameRepository.findById(movement.getId())
				.orElseThrow(() -> new BaseException("log.game.notFound").userMessage("game.notFound"));

		GameResponse gameResponse = new GameResponse();
		GameHelpers.doMovement(game, movement);

		if(GameHelpers.checkWinner(game.getBoard())) {

			game.setWinner(movement.getPlayer().toString());

			gameResponse.setWinner(movement.getPlayer().toString());
			gameResponse.setMsg(MessagesHelpers.get("game.finished"));

		} else if(GameHelpers.isDraw(game.getBoard())) {

			game.setWinner("Draw");

			gameResponse.setWinner("Draw");
			gameResponse.setMsg(MessagesHelpers.get("game.finished"));

		} else {

			GameHelpers.changePlayer(game);

		}

		this.gameRepository.save(game);

		return gameResponse;

	}

}
