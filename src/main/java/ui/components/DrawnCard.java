package ui.components;

import enums.Rank;
import enums.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DrawnCard extends JPanel {
    private static final int HIGHLIGHT_OFFSET = 15;
    private Suit suit;
    private Rank rank;
    private int index;
    private boolean highlighted;
    private static HashMap<Rank, String> rankToString;
    private JLabel suitLabel;
    private JLabel rankLabel;

    /**
     * Constructor for the DrawnCard class, which initializes all the components necessary for the card.
     * @param suit The suit the DrawnCard will have.
     * @param rank The rank/value the DrawnCard will have.
     */
    public DrawnCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        highlighted = false;
        rankToString = new HashMap<>();
        generateRankToString();
        initializeGUIComponents();
    }

    /**
     * Getter method for the suit of the card.
     * @return The suit of the card, as a Suit enum.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Setter method for the suit of the card.
     * @param suit The suit of the card to be set, as a Suit enum.
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Getter method for the value of the card.
     * @return The value of the card, as a Rank enum.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Setter method for the value of the card.
     * @param rank The value of the card to be set, as a Rank enum.
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    /**
     * Generates the hashmap used to display the string representation of a Rank enum, for any given card.
     */
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

    /**
     * Create the JPanel for each card, and display the suit and value accordingly.
     */
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
        JPanel pane = new JPanel();
        pane.setPreferredSize(dimension);
        String rankString = rankToString.get(this.rank);
        rankLabel = new JLabel(rankString);
        rankLabel.setFont(font);
        rankLabel.setBounds(22, 8, 50, 65);
        suitLabel = new JLabel();
        suitLabel.setFont(font);
        suitLabel.setBounds(5, -18, 50, 65);
        switch (this.suit) {
            case CLUB: suitLabel.setText("<html>&#9827;</html>");
            suitLabel.setForeground(black);
            rankLabel.setForeground(red);
            break;
            case SPADE: suitLabel.setText("<html>&#9824;</html>");
            suitLabel.setForeground(black);
            rankLabel.setForeground(red);
            break;
            case HEART: suitLabel.setText("<html>&#9829;</html>");
            suitLabel.setForeground(red);
            rankLabel.setForeground(black);
            break;
            case DIAMOND: suitLabel.setText("<html>&#9830;</html>");
            suitLabel.setForeground(red);
            rankLabel.setForeground(black);
            break;
        }
        pane.add(suitLabel, back);
        pane.add(rankLabel, front);
        add(pane);
    }

    public void setSuitLabel(Suit suit) {
        Color red = new Color(180, 20, 20);
        Color black = Color.BLACK;
        switch (suit) {
            case CLUB: suitLabel.setText("<html>&#9827;</html>");
                suitLabel.setForeground(black);
                rankLabel.setForeground(red);
                break;
            case SPADE: suitLabel.setText("<html>&#9824;</html>");
                suitLabel.setForeground(black);
                rankLabel.setForeground(red);
                break;
            case HEART: suitLabel.setText("<html>&#9829;</html>");
                suitLabel.setForeground(red);
                rankLabel.setForeground(black);
                break;
            case DIAMOND: suitLabel.setText("<html>&#9830;</html>");
                suitLabel.setForeground(red);
                rankLabel.setForeground(black);
                break;
        }
    }

    public void setRankLabel(Rank rank) {
        this.rankLabel.setText(rankToString.get(rank));
    }

    /**
     * Highlights the card by moving the card up 15 pixels.
     */
    public void highlight() {
        if (!highlighted) {
            highlighted = true;
            setLocation(getX(), getY() - HIGHLIGHT_OFFSET);
        }
    }

    /**
     * Unhighlights the card by setting it back to the initial position.
     */
    public void unhighlight() {
        if (highlighted) {
            highlighted = false;
            setLocation(getX(), getY() + HIGHLIGHT_OFFSET);
        }
    }
}
