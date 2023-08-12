package enums;

/**
 * Enums used to identify each type of window.
 */
public enum WindowName {
    MENU("Menu"),
    GAME("Game"),
    CREATOR("Creator"),
    STATS("Statistics"),
    RULES("Rules");

    private String name;

    /**
     * Constructor to give a given window the correct enum associated with it.
     * @param name The enum associated with a given window.
     */
    WindowName(String name) {
        this.name = name;
    }

    /**
     * Getter method that returns the enum associated with a given window.
     * @return The enum associated with any given window.
     */
    public String getName() {
        return name;
    }
}
