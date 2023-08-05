package usecases;

import java.io.*;
import java.util.*;

public class PlayerInformation {
    private String name;
    private int wins;
    private int losses;

    /**
     * Construct an instance of PlayerInformation.
     */
    public PlayerInformation(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }

    /**
     * Get the name from this players PlayerInformation.
     * @return A String containing this players name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the number of wins from this players PlayerInformation.
     * @return An int containing this players number of wins.
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * Get the number of losses from this players PlayerInformation.
     * @return An int containing this players number of losses.
     */
    public int getLosses() {
        return this.losses;
    }
}
