package enums;

public enum WindowName {
    MENU("Menu"),
    GAME("Game"),
    CREATOR("Creator"),
    STATS("Statistics"),
    RULES("Rules"),
    HOWTO("Howto");

    private String name;

    WindowName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
