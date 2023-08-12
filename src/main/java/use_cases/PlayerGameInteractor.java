package use_cases;

import entities.*;
import enums.Rank;
import enums.Suit;

/**
 * This class is responsible for the logic whenever a User/Player interacts with the game screen.
 * That is, when they request to play a Card, pick up a Card, or skip their turn.
 * This class also implements the logic for the ComputerPlayer.
 */
public class PlayerGameInteractor implements PlayerGameInputBoundary {
    private GameAccess access;
    private ObserverNotifier notifier;
    private GameState gameState;

    /**
     * Construct a PlayerGameInteractor with the given GameAccess, ObserverNotifier and GameState.
     * @param access The GameAccess which allows the Interactor to access the Game.
     * @param notifier The ObserverNotifier which allows the Interactor to notify the Observers.
     * @param gameState The GameState which allows the Interactor to access the Game's state.
     */
    public PlayerGameInteractor(GameAccess access, ObserverNotifier notifier, GameState gameState) {
        this.access = access;
        this.notifier = notifier;
        this.gameState = gameState;
    }

    /**
     * Create a new PlayerGameResponseModel given a PlayerGameRequestModel.
     * @param pgrm A PlayerGameRequestModel containing information on the User's request.
     * @return A PlayerGameResponseModel containing the information necessary to update the view a User sees.
     */
    @Override
    public PlayerGameResponseModel createResponse(PlayerGameRequestModel pgrm) {
        Player currPlayer = findPlayerFromString(pgrm.getPlayerName());
        if (currPlayer != null) {
            Card chosenCard = findCard(pgrm.getCardValue(), pgrm.getCardSuit(), currPlayer);

            if (chosenCard != null && pgrm.getPlayCardRequest()) {
                playCardRequestLogic(chosenCard, currPlayer);
            } 
            else if (pgrm.getPickUpCardRequest()) {
                pickUpCardRequestLogic(currPlayer);
            } 
            else {
                // Requested to skip turn, check if the Player can play a Card first
                skipTurnLogic(currPlayer);
            }
            // Now we assume that the Game has been successfully updated, but first check that the Player whose
            // turn it IS NOT a ComputerPlayer, as a User should not play as a ComputerPlayer
            Player newCurrPlayer = access.getCurrentTurn();
            while ((newCurrPlayer instanceof ComputerPlayer) & !(access.hasWinner())) {
                computerPlayerLogic((ComputerPlayer) newCurrPlayer);
                newCurrPlayer = access.getCurrentTurn();
            }
            // Return the current state of the game with a response model.
            return new PlayerGameResponseModel(gameState);
        } 
        else {
            return null;
        }
    }

    /**
     * Find the Player in the Game with the given name.
     * This method assumes no Player in the current Game share the same name.
     * @param name A name of a Player in the Game.
     * @return The Player whose name matches the 'name' parameter.
     */
    private Player findPlayerFromString(String name) {
        for(Player p: access.getPlayers()) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Find the Card in the given Player's Hand matching the description given.
     * This method assumes the Card is in the Player's Hand (it wouldn't be called otherwise).
     * @param rank The value of the Card to return.
     * @param suit The suit of the Card to return.
     * @param player The player object of the player.
     * @return The Card matching the given suit and value.
     */
    private Card findCard(Rank rank, Suit suit, Player player) {
        for(Card card: player.getCards()) {
            Rank otherRank = card.getRank();
            Suit otherSuit = card.getSuit();
            if(rank == otherRank & suit == otherSuit) {
                return card;
            }
        }
        return null;
    }

    /**
     * Implement the logic required when a Player tries to place the chosenCard down.
     */
    private void playCardRequestLogic(Card chosenCard, Player currentPlayer) {
        boolean valid = access.isValidCard(chosenCard);
        if(valid) {
            // The card is valid, place it down, change turns
            access.playCard(currentPlayer, chosenCard);
            if(isWinner(currentPlayer)) {
                winLogic(currentPlayer);
            }
            access.changeCurrentTurn();
            access.notifyGameObservers();
        }  // The card is not valid, therefore it should not be played, nothing is to be done.
    }

    /**
     * Implement the logic required when a User requests to pick up a Card. It will check if the user
     * can pick up a Card, and if they can, then pick one up. Otherwise, the function won't do anything.
     */
    private void pickUpCardRequestLogic(Player currentPlayer) {
        if(!(access.getCurrentTurnHasPickedUp()) && !(anyValidCards(currentPlayer))) {
            access.pickUpCard(currentPlayer);
            notifier.update();
        }
    }

    /**
     * Implement the logic required when a User requests to skip a turn. This method will check if the user
     * has any valid cards or if the user has picked up. If the user has picked up OR has no valid cards,
     * the user CAN skip their turn. Otherwise, the user CANNOT skip their turn.
     */
    private void skipTurnLogic(Player currentPlayer) {
        assert currentPlayer.equals(access.getCurrentTurn());
        if ((access.getCurrentTurnHasPickedUp()) & !(anyValidCards(currentPlayer))) {
            // User can skip.
            access.changeCurrentTurn();
            notifier.update();
        }
            // User cannot skip their turn.
    }

    /**
     * Check all the Cards in this Player's Hand to see if any of them are valid to play in the Game.
     * @return True iff any Cards are valid to play, false otherwise.
     */
    private boolean anyValidCards(Player player) {
        for (Card card : player.getCards()) {
            if (access.isValidCard(card)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Perform the logic required when a Player wins the Game. If a Player has won, set the Player as the Game winner,
     * and increment wins and losses for all HumanPlayers in the current game.
     * @param player The player who has won the Game.
     */
    private void winLogic(Player player) {
        access.setWinner(player);
        if(player instanceof HumanPlayer) {
            ((HumanPlayer) player).incrementWins();
        }
        for(Player p: access.getPlayers()) {
            if(p instanceof HumanPlayer && !(p.equals(player))) {
                ((HumanPlayer) p).incrementLosses();
            }
        }
        notifier.update();
    }

    /**
     * Check if the given Player is the winner of the Game.
     * @return True iff the Player has no Cards left in their Hand (played their last Card).
     */
    private boolean isWinner(Player player) {
        return player.getCards().isEmpty();
    }

    /**
     * This method implements all the logic for ComputerPlayers, including the logic for putting down a Card,
     * picking a card up, and skipping their turn.
     * @param compPlayer The ComputerPlayer whose turn it is.
     */
    private void computerPlayerLogic(ComputerPlayer compPlayer) {
        Card compCard = access.selectRandomValidCard(compPlayer);
        // If compCard is null, compPlayer has no valid cards, thus it must pick up and then try again.
        if(compCard == null) {
            access.pickUpCard(compPlayer);
            boolean hasAnyValid = anyValidCards(compPlayer);
            if(hasAnyValid) {
                // Player has a valid card, thus play it
                Card toPlay = access.selectRandomValidCard(compPlayer);
                access.playCard(compPlayer, toPlay);
                if(isWinner(compPlayer)) {
                    // The ComputerPlayer is the winner, set them as the Winner and notify the Game
                    winLogic(compPlayer);
                    return;
                }
            }
        } else {
            // Play the valid Card
            access.playCard(compPlayer, compCard);
            if(isWinner(compPlayer)) {
                // The ComputerPlayer is the winner, set them as the Winner and notify the Game
                winLogic(compPlayer);
                return;
            }
        }
        // The ComputerPlayer has played a Card (may or may not have picked up) and there was no winner, so change the
        // current turn and update the Game.
        access.changeCurrentTurn();
        notifier.update();
    }
}