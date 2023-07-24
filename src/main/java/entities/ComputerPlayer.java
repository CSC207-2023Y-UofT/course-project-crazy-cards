package entities;

import java.util.*;

public class ComputerPlayer extends Player {

    /**
     * This method implements the logic required for the ComputerPlayer.
     * A random card is selected from the ComputerPlayer's Hand to be put down to the Game.
     * @param game The Game instance where the Card is to be put down.
     */
    public void playCard(Game game) {
        ArrayList<Card> computerCopyCards = getCards();
        Collections.shuffle(computerCopyCards);
        Card selectedCard = computerCopyCards.get(0);
        for (Card i: getCards()) {
            if (game.isValidCard(i)) {
                super.playCard(game, i);
            }

        }

    }
}

