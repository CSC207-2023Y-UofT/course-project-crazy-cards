package ui.components;

import enums.Rank;
import enums.Suit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.util.List;

public class DrawnHand extends JPanel {
    private JLayeredPane cardPane;
    private int visible;
    private final List<DrawnCard> drawnCards;

    /**
     * Constructor that sets the current hand to a given list of DrawnCards before initializing the GUI for display.
     * Adds a new ComponentListener that will (or won't) perform actions based on a given input it listens for.
     * @param drawnCards A List of DrawnCards to be set as the current player's hand.
     */
    public DrawnHand(List<DrawnCard> drawnCards) {
        this.drawnCards = drawnCards;

        initializeGUIComponents();

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateCards();
            }

            public void componentMoved(java.awt.event.ComponentEvent e) {
                // Do nothing
            }

            public void componentShown(java.awt.event.ComponentEvent e) {
                // Do nothing
            }

            public void componentHidden(java.awt.event.ComponentEvent e) {
                // Do nothing
            }
        });
    }

    /**
     * Adds a MouseListener to each DrawnCard in the user's hand.
     * @param listener The mouse listener added to each card.
     */
    @Override
    public void addMouseListener(MouseListener listener) {
        for (DrawnCard card : drawnCards) {
            card.addMouseListener(listener);
        }
    }

    /**
     * Add a given card to the DrawnHand, in order to be displayed.
     * @param card The given card to be added to the user's hand.
     */
    public void addCard(DrawnCard card) {
        drawnCards.add(card);
    }

    /**
     * Initializes the GUI components necessary for the DrawnHand to be displayed.
     */
    private void initializeGUIComponents() {
        setLayout(new BorderLayout());

        cardPane = new JLayeredPane();
        cardPane.setPreferredSize(new Dimension(300, 100));

        add(cardPane);
    }

    /**
     * Sets a new display for a given DrawnCard within the user's DrawnHand to be displayed.
     * @param index Index of the specific DrawnCard to be updated, in the List of DrawnCards within the user's hand.
     * @param suit The new suit of the card that is being updated.
     * @param rank The new rank/value of the card that is being updated.
     */
    public void setCard(int index, Suit suit, Rank rank) {
        DrawnCard card = drawnCards.get(index);
        card.setSuit(suit);
        card.setRank(rank);
        card.setVisible(true);
    }

    /**
     * Hide all cards after the given index, inclusive.
     * @param index The index of the card to start hiding from.
     */
    public void hideCards(int index) {
        visible = index;
        for (int i = index; i < drawnCards.size(); i++) {
            DrawnCard card = drawnCards.get(i);
            card.setVisible(false);
        }
    }

    /**
     * Update the display of the DrawnHand by setting new positions of each card and changing the display between users.
     */
    public void updateCards() {
        int width = cardPane.getWidth();
        int height = cardPane.getHeight();

        double cardOffset = (double) (width - 70) / (this.visible - 1);

        cardPane.removeAll();

        for (int i = 0; i < this.visible; i++) {
            DrawnCard card = drawnCards.get(i);
            cardPane.add(card, i);

            boolean wasHovered = card.isHovered();

            card.unhover();
            card.setBounds((int)(i * cardOffset), (height / 2) - 30, 70, 95);
            if (wasHovered) {
                card.hover();
            }
        }
    }
}
