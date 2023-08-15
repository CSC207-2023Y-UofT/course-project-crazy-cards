package entities.game_logic;

import entities.player_logic.Player;
import entities.deck_logic.Deck;

import java.util.ArrayList;

public interface CreationAccess {
    void buildGame(ArrayList<Player> players, Deck deck);
}
