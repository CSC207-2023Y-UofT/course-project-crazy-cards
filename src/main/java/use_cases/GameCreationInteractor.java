package use_cases;

import entities.deck_logic.Deck;
import entities.deck_logic.StandardDeck;
import entities.game_logic.CreationAccess;
import entities.player_logic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is initialized once, given a request model, it constructs a Game if the request model has valid information
 * to create a Game, and sends a response model to the controller on whether a Game was created or not.
 */
public class GameCreationInteractor implements GameCreationInputBoundary {
    static final int NUMBER_OF_CARDS = 5;
    private final DataAccess dataAccess;
    private CreationAccess access;

    /**
     * Initialize a GameCreationInteractor given a particular data access object.
     * @param dataAccess a database interactor.
     * @param access exposed methods to build a Game.
     */
    public GameCreationInteractor(DataAccess dataAccess, CreationAccess access) {
        this.dataAccess = dataAccess;
        this.access = access;
    }

    /**
     * Created a response model, and Game if the request is valid.
     * @param request a GameCreationRequestModel containing details from the User.
     * @return a GameCreationResponseModel containing whether a Game was created (if User input was valid or not).
     */
    @Override
    public GameCreationResponseModel createGameResponse(GameCreationRequestModel request) {
        if(!(validAmountPlayers(request))) {
            return new GameCreationResponseModel(false);
        }
        Deck deck = buildDeck();
        ArrayList<Player> players = buildPlayers(request, deck);
        access.buildGame(players, deck);
        return new GameCreationResponseModel(true);
    }

    /**
     * Check if the requested Players to create has enough players and enough HumanPlayers to create a Game.
     * @param request The request containing details from the User regarding types of Players to create.
     * @return True iff more than 2 Players total and at least 1 HumanPlayer requested.
     */
    private boolean validAmountPlayers(GameCreationRequestModel request) {
        // check there are at least 2 players in the Game
        if(request.getPlayersInfo().keySet().size() < 2) {
            return false;
        }
        int numHumanPlayer = 0;
        for (String name: request.getPlayersInfo().keySet()) {
            if(!(request.isComputerPlayer(name))) {
                numHumanPlayer ++;
            }
        }
        // The request contains at least 1 human player
        return numHumanPlayer >= 1;
    }

    /**
     * Build the Deck instance that will be used by the Game that is to be built.
     * @return a Deck instance.
     */
    private Deck buildDeck() {
        return new StandardDeck();
    }

    /**
     * Build a Hand instance for a Player in this Game.
     * @param deck The Deck which this Hand's Cards will be from.
     * @return a Hand instance representing the Hand of a Player in the Game to be built.
     */
    private Hand buildHand(Deck deck) {
        Hand hand = new Hand(new ArrayList<>());
        for(int i = 0; i < NUMBER_OF_CARDS; i++) {
            hand.addCard(deck.removeCardFromDeck());
        }
        return hand;
    }

    /**
     * Build the list of Players that will be used in the Game to be built, given the details in the request.
     * @param playersInfo The request containing the User's requested details for Players in this Game.
     * @param deck The deck that the Player's Hands will use.
     * @return A shuffled ArrayList of Players in this Game where the first Player in the list is a HumanPlayer.
     */
    private ArrayList<Player> buildPlayers(GameCreationRequestModel playersInfo, Deck deck) {
        ArrayList<Player> players = new ArrayList<>();
        ComputerPlayerFactory cpFactory = new ComputerPlayerFactory();
        HumanPlayerFactory hpFactory = new HumanPlayerFactory();
        for(String name: playersInfo.getPlayersInfo().keySet()) {
            Hand playerHand = buildHand(deck);
            if(playersInfo.isComputerPlayer(name)) {
                Player cp = cpFactory.create(name, playerHand);
                players.add(cp);
            } else {
                Player hp = HumanPlayerBuilder(name, hpFactory, playerHand);
                players.add(hp);
                }
            }
        // Shuffle the order of the Players, but make sure the first turn belongs to a HumanPlayer.
        Collections.shuffle(players);
        while(players.get(0) instanceof ComputerPlayer) {
            Collections.shuffle(players);
        }
        return players;
    }

    /**
     * Create a HumanPlayer with the given name, factory, and Hand. If a Player has wins and losses in the database,
     * the returned Player will have those stats.
     * @param name The name of the Player to be created.
     * @param factory The factory used to create to the Player.
     * @param hand The Hand that will belong. to the Player
     * @return a Player with the amount of wins and losses in the database.
     */
    private Player HumanPlayerBuilder(String name, HumanPlayerFactory factory, Hand hand) {
        PlayerInformation stats = getPlayerStats(name);
        Player hp = factory.create(name, hand);
        if (stats != null) {
            // Increase hp's wins to what they are in stats.
            for(int i = 0; i < stats.getWins(); i++) {
                ((HumanPlayer) hp).incrementWins();
            }
            for(int i = 0; i < stats.getLosses(); i++) {
                ((HumanPlayer) hp).incrementLosses();
            }
        }
        return hp;
    }

    /**
     * Given a Player's name, access their statistics if they exist in the database.
     * @param name A potential Player's name to be checked in the database.
     * @return a PlayerInformation object if the name is in the database, and null otherwise.
     */
    private PlayerInformation getPlayerStats(String name) {
        try {
            return dataAccess.loadPlayer(name);
        } catch (IOException e) {
            return null;
        }
    }
}
