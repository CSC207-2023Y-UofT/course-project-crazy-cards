package ui.components;

import enums.Rank;
import enums.Suit;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DrawnCard extends JPanel {
    private static final int HOVER_OFFSET = 30;
    private static final Color GOLD_SELECTED = new Color(255, 215, 0);
    private static final Color BLACK_UNSELECTED = new Color(0, 0, 0);
    private static final HashMap<Rank, String> rankToString = new HashMap<>() {{
        put(Rank.ACE, "A");
        put(Rank.TWO, "2");
        put(Rank.THREE, "3");
        put(Rank.FOUR, "4");
        put(Rank.FIVE, "5");
        put(Rank.SIX, "6");
        put(Rank.SEVEN, "7");
        put(Rank.EIGHT, "8");
        put(Rank.NINE, "9");
        put(Rank.TEN, "10");
        put(Rank.JACK, "J");
        put(Rank.QUEEN, "Q");
        put(Rank.KING, "K");
    }};

    private Suit suit;
    private Rank rank;
    private boolean hovered;
    private boolean selected;
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
        hovered = false;
        selected = false;

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
        setSuitLabel(suit);
        unhighlight();
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
        setRankLabel(rank);
        unhighlight();
    }

    /**
     * Create the JPanel for each card, and display the suit and value accordingly.
     */
    private void initializeGUIComponents() {
        Dimension dimension = new Dimension(70, 95);
        setPreferredSize(dimension);
        setBorder(BorderFactory.createLineBorder(BLACK_UNSELECTED, 2, true));
        // Define colours
        Color red = new Color(180, 20, 20);
        Color black = Color.BLACK;

        // Define font to be used
        Font font = new Font("monospaced", Font.BOLD, 50);

        // Creating objects
        JLayeredPane pane = new JLayeredPane();
        pane.setPreferredSize(dimension);
        String rankString = rankToString.get(this.rank);
        rankLabel = new JLabel(rankString);
        rankLabel.setFont(font);
        rankLabel.setBounds(-5, 10, 70, 95);
        rankLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        suitLabel = new JLabel();
        suitLabel.setFont(font);
        suitLabel.setBounds(5, -30, 50, 95);
        suitLabel.setHorizontalAlignment(SwingConstants.LEFT);
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
        pane.add(suitLabel);
        pane.add(rankLabel);
        add(pane);
    }

    /**
     * Helper method to update this card's rank.
     */
    private void setRankLabel(Rank rank) {
        String rankString = rankToString.get(rank);
        rankLabel.setText(rankString);
    }

    /**
     * Helper method to update this card's suit.
     */
    private void setSuitLabel(Suit suit) {
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

    /**
     * Moves the card up 15 pixels to signify hovering the mouse over it.
     */
    public void hover() {
        if (!hovered) {
            hovered = true;
            setLocation(getX(), getY() - HOVER_OFFSET);
        }
    }

    /**
     * Moves the card back down to signify moving the mouse off it.
     */
    public void unhover() {
        if (hovered) {
            hovered = false;
            setLocation(getX(), getY() + HOVER_OFFSET);
        }
    }

    /**
     * Highlights the card by changing the border to gold.
     */
    public void highlight() {
        if (!selected) {
            selected = true;
            setBorder(BorderFactory.createLineBorder(GOLD_SELECTED, 2, true));
        }
    }

    /**
     * Unhighlights the card by changing the border to black.
     */
    public void unhighlight() {
        if (selected) {
            selected = false;
            setBorder(BorderFactory.createLineBorder(BLACK_UNSELECTED, 2, true));
        }
    }

    /**
     * Returns whether the card is being hovered over or not.
     * @return whether the card is being hovered over.
     */
    public boolean isHovered() {
        return hovered;
    }
}
