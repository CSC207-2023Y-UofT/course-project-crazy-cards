package entities;

/**
 * The class used to create the human players whenever a new game of Crazy Cards is started.
 * It will be used at least once per game to create the user who starts the game.
 */
public class HumanPlayerFactory implements PlayerFactory {
    /**
     * Create a HumanPlayer with the given parameters.
     * This HumanPlayer initially has no wins or losses.
     * @param name The name of the Player that will be created.
     * @param hand The hand dealt to the Player that will be created.
     * @return Return a new HumanPlayer with the given name and hand, and default wins and losses.
     */
    @Override
    public Player create(String name, Hand hand) {
        return new HumanPlayer(name, hand, 0, 0);
    }

}
