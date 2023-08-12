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
    private final Game currentGame;
    private final GameState gameState;

    /**
     * Create a new PlayerGameInteractor.
     * @param game The Game instance which the User interacts with.
     * @param gameState The GameState which this Interactor uses to later construct a response model.
     */
    public PlayerGameInteractor(Game game, GameState gameState) {
        this.currentGame = game;
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
            } else if (pgrm.getPickUpCardRequest()) {
            pickUpCardRequestLogic(currPlayer);
            } else if (pgrm.getSkipTurnRequest()) {
                // Requested to skip turn, check if the Player can play a Card first
                skipTurnLogic(currPlayer);
            }
            // Now we assume that the Game has been successfully updated, but first check that the Player whose
            // turn it IS NOT a ComputerPlayer, as a User should not play as a ComputerPlayer
            Player newCurrPlayer = currentGame.getCurrentTurn();
            while ((newCurrPlayer instanceof ComputerPlayer) & !(currentGame.hasWinner())) {
            computerPlayerLogic((ComputerPlayer) newCurrPlayer);
            newCurrPlayer = currentGame.getCurrentTurn();
            }
            // Return the current state of the game with a response model.
            return new PlayerGameResponseModel(gameState);
        } 
        else {
            return new PlayerGameResponseModel(gameState);
        }
    }

    /**
     * Find the Player in the Game with the given name.
     * This method assumes no Player in the current Game share the same name.
     * @param name A name of a Player in the Game.
     * @return The Player whose name matches the 'name' parameter.
     */
    private Player findPlayerFromString(String name) {
        for(Player p: currentGame.getPlayers()) {
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
        boolean valid = currentGame.isValidCard(chosenCard);
        if(valid) {
            // The card is valid, place it down, change turns
            currentPlayer.playCard(currentGame, chosenCard);
            if(isWinner(currentPlayer)) {
                winLogic(currentPlayer);
            }
            currentGame.changeCurrentTurn();
            currentGame.notifyGameObservers();
        }  // The card is not valid, therefore it should not be played, nothing is to be done.
    }

    /**
     * Implement the logic required when a User requests to pick up a Card. It will check if the user
     * can pick up a Card, and if they can, then pick one up. Otherwise, the function won't do anything.
     */
    private void pickUpCardRequestLogic(Player currentPlayer) {
        if(!(currentGame.getCurrentTurnHasPickedUp()) && !(anyValidCards(currentPlayer))) {
            currentPlayer.pickUpCard(currentGame);
            currentGame.notifyGameObservers();
        }
    }

    /**
     * Implement the logic required when a User requests to skip a turn. This method will check if the user
     * has any valid cards or if the user has picked up. If the user has picked up OR has no valid cards,
     * the user CAN skip their turn. Otherwise, the user CANNOT skip their turn.
     */
    private void skipTurnLogic(Player currentPlayer) {
        assert currentPlayer.equals(currentGame.getCurrentTurn());
        if ((currentGame.getCurrentTurnHasPickedUp()) & !(anyValidCards(currentPlayer))) {
            // User can skip.
            currentGame.changeCurrentTurn();
            currentGame.notifyGameObservers();
        }
            // User cannot skip their turn.
    }

    /**
     * Check all the Cards in this Player's Hand to see if any of them are valid to play in the Game.
     * @return True iff any Cards are valid to play, false otherwise.
     */
    private boolean anyValidCards(Player player) {
        for (Card card : player.getCards()) {
            if (currentGame.isValidCard(card)) {
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
        currentGame.setWinner(player);
        if(player instanceof HumanPlayer) {
            ((HumanPlayer) player).incrementWins();
        }
        for(Player p: currentGame.getPlayers()) {
            if(p instanceof HumanPlayer && !(p.equals(player))) {
                ((HumanPlayer) p).incrementLosses();
            }
        }
        currentGame.notifyGameObservers();
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
        Card compCard = compPlayer.selectRandomValidCard(this.currentGame);
        // If compCard is null, compPlayer has no valid cards, thus it must pick up and then try again.
        if(compCard == null) {
            compPlayer.pickUpCard(this.currentGame);
            boolean hasAnyValid = anyValidCards(compPlayer);
            if(hasAnyValid) {
                // Player has a valid card, thus play it
                Card toPlay = compPlayer.selectRandomValidCard(this.currentGame);
                compPlayer.playCard(this.currentGame, toPlay);
                if(isWinner(compPlayer)) {
                    // The ComputerPlayer is the winner, set them as the Winner and notify the Game
                    winLogic(compPlayer);
                    return;
                }
            }
        } else {
            // Play the valid Card
            compPlayer.playCard(this.currentGame, compCard);
            if(isWinner(compPlayer)) {
                // The ComputerPlayer is the winner, set them as the Winner and notify the Game
                winLogic(compPlayer);
                return;
            }
        }
        // The ComputerPlayer has played a Card (may or may not have picked up) and there was no winner, so change the
        // current turn and update the Game.
        currentGame.changeCurrentTurn();
        currentGame.notifyGameObservers();
    }
}