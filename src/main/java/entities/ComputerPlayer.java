package entities;

import java.util.*;

public class ComputerPlayer extends Player {


    /**
     * Construct a ComputerPlayer with the given Hand.
     * @param name The name to distinguish between the different ComputerPlayers.
     * @param hand The Hand instance to be this ComputerPlayer's Hand.
     */
    public ComputerPlayer(String name, Hand hand) {
        super(name, hand);
    }


    /**
     * Select a random valid Card to play in this Game
     * @param game The Game instance this ComputerPlayer is a part of.
     * @return a Card that is to be placed down in the Game or null is there is no valid Card.
     */
    public Card selectRandomValidCard(Game game) {
        ArrayList<Card> computerCopyCards = getCards();
        Collections.shuffle(computerCopyCards);
        for (Card card : getCards()) {
            if (game.isValidCard(card)) {
                return card;
            }
        }
        return null;
    }

}

