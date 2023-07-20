package entities;

import java.io.*;
import java.util.*;

public class HumanPlayer extends Player {

    String name;

    private int losses;

    private int wins;


    public HumanPlayer(String name) {
        this.name = name;
    }

    public String getStats() {
        return "";
    }

    public void incrementWins() {

    }

    public void incrementLosses() {

    }


    public String getName() {
        return this.name;
    }

}
