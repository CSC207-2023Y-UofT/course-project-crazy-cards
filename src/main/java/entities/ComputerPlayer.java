package entities;

import java.util.*;

public class ComputerPlayer extends Player {

    /**
     * Construct a ComputerPlayer with the given Hand.
     * @param hand The Hand instance to be this ComputerPlayer's Hand.
     */
    public ComputerPlayer(Hand hand) {
        super(hand);
    }


    /**
     * Select a random valid Card to play in this Game
     * @param game The Game instance this ComputerPlayer is a part of.
     * @return a Card that is to be placed down in the Game.
     * @throws NoValidCardException iff there is no valid Cards to play for the given Game.
     */
    public Card selectRandomCard(Game game) throws NoValidCardException {
        ArrayList<Card> computerCopyCards = getCards();
        Collections.shuffle(computerCopyCards);
        for (Card card : getCards()) {
            if (game.isValidCard(card)) {
                return card;
            }
        }
        throw new NoValidCardException("This ComputerPlayer has no valid Cards to play.");
    }

}

