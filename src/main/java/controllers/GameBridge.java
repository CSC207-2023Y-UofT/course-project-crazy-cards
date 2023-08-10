package controllers;

import enums.Rank;
import enums.Suit;
import enums.TurnAction;
import use_cases.PlayerGameResponseModel;

public interface GameBridge {
    PlayerGameResponseModel getResponse(String playerName, Suit suit, Rank rank, TurnAction action);
}
