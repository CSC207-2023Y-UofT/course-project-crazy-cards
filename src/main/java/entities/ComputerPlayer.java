package entities;

import java.util.*;

public class ComputerPlayer extends Player {

    public void putCard(Game game) {
        ArrayList<Card> computerCopyCards = getCards();
        Collections.shuffle(computerCopyCards);
        Card selectedCard = computerCopyCards.get(0);
        super.putCard(game, selectedCard);

    }

}
