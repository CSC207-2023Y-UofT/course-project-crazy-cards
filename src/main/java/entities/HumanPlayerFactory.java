package entities;

public class HumanPlayerFactory implements PlayerFactory {
    /**
     * Create a HumanPlayer with the given parameters.
     * @param name The name of the Player that will be created.
     * @param hand The hand dealt to the Player that will be created.
     * @return Return a new HumanPlayer with the given name and hand, and default wins and losses.
     */
    @Override
    public Player create(String name, Hand hand) {
        return new HumanPlayer(name, hand, 0, 0) {
        };
    }
    // TODO: implement persistence for wins and losses of players who have played before
}
