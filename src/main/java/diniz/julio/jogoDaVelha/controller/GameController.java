package diniz.julio.jogoDaVelha.controller;

import diniz.julio.jogoDaVelha.models.Movement;
import diniz.julio.jogoDaVelha.responses.GameResponse;
import diniz.julio.jogoDaVelha.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

	private final GameService gameService;

	@Autowired
	public GameController(GameService gameService) {

		this.gameService = gameService;

	}

	@PostMapping
	public GameResponse create() {

		return this.gameService.createGame();

	}

	@PostMapping("{id}/movement")
	public GameResponse doMovement(@RequestBody Movement movement) {

		return this.gameService.doMovement(movement);

	}

}
