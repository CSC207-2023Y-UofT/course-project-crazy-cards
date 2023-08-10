package ui.components;

import java.awt.*;
import java.util.HashMap;

import ui.enums.Rank;
import ui.enums.Suit;

import javax.swing.*;

import static ui.enums.Rank.*;

public class DrawnCard extends JPanel {
    private static final int HIGHLIGHT_OFFSET = 15;
    private Suit suit;
    private Rank rank;
    private int index;
    private boolean highlighted;
    private HashMap<Rank, String> rankToString;

    public DrawnCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        highlighted = false;
        rankToString = new HashMap<>();
        generateRankToString();
        initializeGUIComponents();
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void generateRankToString() {
        rankToString.put(Rank.ACE, "A");
        rankToString.put(Rank.TWO, "2");
        rankToString.put(Rank.THREE, "3");
        rankToString.put(Rank.FOUR, "4");
        rankToString.put(Rank.FIVE, "5");
        rankToString.put(Rank.SIX, "6");
        rankToString.put(Rank.SEVEN, "7");
        rankToString.put(Rank.EIGHT, "8");
        rankToString.put(Rank.NINE, "9");
        rankToString.put(Rank.TEN, "10");
        rankToString.put(Rank.JACK, "J");
        rankToString.put(Rank.QUEEN, "Q");
        rankToString.put(Rank.KING, "K");
    }

    private void initializeGUIComponents() {
        Dimension dimension = new Dimension(50, 65);
        setPreferredSize(dimension);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        // Define colours
        Color red = new Color(180, 20, 20);
        Color black = Color.BLACK;

        // Define Integers (for layering's sake in the JLayeredPane)
        Integer back = 1;
        Integer front = 2;

        // Define font to be used
        Font font = new Font("monospaced", Font.BOLD, 37);

        // Creating objects
        JLayeredPane pane = new JLayeredPane();
        pane.setPreferredSize(dimension);
        String valueString = rankToString.get(this.rank);
        JLabel value = new JLabel(valueString);
        value.setFont(font);
        value.setBounds(22, 8, 50, 65);
        JLabel suits = new JLabel();
        suits.setFont(font);
        suits.setBounds(5, -18, 50, 65);
        switch (this.suit) {
            case CLUB: suits.setText("<html>&#9827;</html>");
            suits.setForeground(black);
            value.setForeground(red);
            break;
            case SPADE: suits.setText("<html>&#9824;</html>");
            suits.setForeground(black);
            value.setForeground(red);
            break;
            case HEART: suits.setText("<html>&#9829;</html>");
            suits.setForeground(red);
            value.setForeground(black);
            break;
            case DIAMOND: suits.setText("<html>&#9830;</html>");
            suits.setForeground(red);
            value.setForeground(black);
            break;
        }
        pane.add(suits, back);
        pane.add(value, front);
        add(pane);
    }

    public void highlight() {
        if (!highlighted) {
            highlighted = true;
            setLocation(getX(), getY() - HIGHLIGHT_OFFSET);
        }
    }

    public void unhighlight() {
        if (highlighted) {
            highlighted = false;
            setLocation(getX(), getY() + HIGHLIGHT_OFFSET);
        }
    }
}
