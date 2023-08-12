package controllers;

import enums.Rank;
import enums.Suit;
import enums.TurnAction;
import use_cases.PlayerGameResponseModel;

/**
 * Defines a controller that handles the interaction between players and a game.
 */
public interface GameBridge {
    PlayerGameResponseModel getResponse(String playerName, Suit suit, Rank rank, TurnAction action);
}
