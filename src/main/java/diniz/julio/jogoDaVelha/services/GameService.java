package diniz.julio.jogoDaVelha.services;

import diniz.julio.jogoDaVelha.models.Movement;
import diniz.julio.jogoDaVelha.responses.GameResponse;

public interface GameService {

	GameResponse createGame();

	GameResponse doMovement(Movement movement);

}
