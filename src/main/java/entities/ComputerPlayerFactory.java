package entities;

/**
 * The class used to create the computer players whenever a new game of Crazy Cards is started.
 * It may or may not be used in the game, depending on whether the user wants to play with computer players.
 */
public class ComputerPlayerFactory implements PlayerFactory {
    /**
     * Create a ComputerPlayer with the given parameters.
     * @param name The name of the Player that will be created.
     * @param hand The hand dealt to the Player that will be created.
     * @return Return a new ComputerPlayer with the given name and hand.
     */
    @Override
    public Player create(String name, Hand hand) {
        return new ComputerPlayer(name, hand);
    }
}
