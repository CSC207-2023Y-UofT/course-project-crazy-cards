package entities;

/**
 * The interface used to implement the different types of player factories that will be used
 * to create the players whenever a new game of Crazy Cards is started.
 */
public interface PlayerFactory {
    /**
     * Create a Player upon calling this method.
     * @param name The name of the Player that will be created.
     * @param hand The hand dealt to the Player that will be created.
     */
    Player create(String name, Hand hand);
}
