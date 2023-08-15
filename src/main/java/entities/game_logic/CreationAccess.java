package entities.game_logic;

import entities.deck_logic.Deck;
import entities.player_logic.Player;

import java.util.ArrayList;

public interface CreationAccess {
    void buildGame(ArrayList<Player> players, Deck deck);
}
