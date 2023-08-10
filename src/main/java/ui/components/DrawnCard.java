package ui.components;

import java.awt.Color;
import java.awt.Dimension;

import ui.enums.Rank;
import ui.enums.Suit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class DrawnCard extends JPanel {
    private static final int HIGHLIGHT_OFFSET = 15;

    private Suit suit;
    private Rank rank;
    private int index;

    private boolean highlighted;

    public DrawnCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        highlighted = false;

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

    private void initializeGUIComponents() {
        setPreferredSize(new Dimension(30, 50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
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
