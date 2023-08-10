package ui.windows;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import ui.components.DrawnHand;

/**
 * Renderer for a game window.
 */
public class GameDisplay extends JPanel {
    private GamePlayDelegator playDelegator;
    private GameSkipDelegator skipDelegator;
    private GameDrawDelegator drawDelegator;
    private CardDelegator cardDelegator;

    private String currentPlayer;
    private HashMap<String, DrawnHand> handsByPlayer;

    private JPanel header;
    private JPanel center;
    private JPanel footer;

    private JLabel currentPlayerLabel;

    private JTable scores;
    private JPanel gameBoard;
    private JPanel hand;   
    private JPanel buttons;

    private JButton playButton;
    private JButton drawButton;
    private JButton skipButton;

    /**
     * Construct a GameDisplay with the given delegators.
     * @param gameDelegator the delegator for game actions
     * @param cardDelegator the delegator for card actions
     */
    public GameDisplay(GameDelegator gameDelegator, CardDelegator cardDelegator) {
        this.playDelegator = gameDelegator.getPlayDelegator();
        this.skipDelegator = gameDelegator.getSkipDelegator();
        this.drawDelegator = gameDelegator.getDrawDelegator();
        this.cardDelegator = cardDelegator;

        initializeGUIComponents();
    }

    /**
     * Switch the current player. Should be called on new turns.
     * @param player the name of the current player
     */
    public void switchHand(String player) {
        currentPlayer = player;
        currentPlayerLabel.setText(currentPlayer);

        hand.removeAll();
        DrawnHand currentHand = handsByPlayer.get(currentPlayer);
        currentHand.updateCards();
        hand.add(currentHand);
    }

    /**
     * Set the hands of each player. Should be called when this object is first created.
     * @param handsByPlayer the hands of each player
     */
    public void setHandsByPlayer(HashMap<String, DrawnHand> handsByPlayer) {
        for (String player : handsByPlayer.keySet()) {
            DrawnHand hand = handsByPlayer.get(player);
            hand.addMouseListener(cardDelegator);
        }

        this.handsByPlayer = handsByPlayer;
    }

    /**
     * Initialize the GUI components of this window.
     */
    private void initializeGUIComponents() {
        setLayout(new BorderLayout());

        initializeHeader();
        initializeCenter();
        initializeFooter();

        add(header, BorderLayout.PAGE_START);
        add(center, BorderLayout.CENTER);
        add(footer, BorderLayout.PAGE_END);
    }

    /**
     * Initialize the header of this window.
     */
    private void initializeHeader() {
        header = new JPanel();
        currentPlayerLabel = new JLabel(currentPlayer);
        header.add(currentPlayerLabel);
    }

    /**
     * Initialize the center of this window.
     */
    private void initializeCenter() {
        // The center JPanel is split into two JPanels for formatting purposes
        center = new JPanel();
        center.setLayout(new GridBagLayout());

        // The top JPanel stores the scoreboard and the gameboard
        // Screen space is divided between the two at a ratio of 1:2 respectively
        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());

        // A table storing the number of cards each player has
        GridBagConstraints scoresConstraints = new GridBagConstraints();
        scoresConstraints.gridx = 0;
        scoresConstraints.gridy = 0;
        scoresConstraints.gridwidth = 1;
        scoresConstraints.gridheight = 1;
        scoresConstraints.fill = GridBagConstraints.BOTH;
        scoresConstraints.weightx = 0.33;
        scoresConstraints.weighty = 1;
        JPanel scorePanel = new JPanel();
        scores = new JTable();  // TODO: handle table data
        scorePanel.add(scores);

        top.add(scorePanel, scoresConstraints);

        // The game board
        GridBagConstraints gameBoardConstraints = new GridBagConstraints();
        gameBoardConstraints.gridx = 1;
        gameBoardConstraints.gridy = 0;
        gameBoardConstraints.gridwidth = 3;
        gameBoardConstraints.gridheight = 1;
        gameBoardConstraints.fill = GridBagConstraints.BOTH;
        gameBoardConstraints.weightx = 0.67;
        gameBoardConstraints.weighty = 1;
        gameBoard = new JPanel();
        top.add(gameBoard, gameBoardConstraints);

        // The bottom JPanel stores the hand and the buttons
        // Screen space is divided between the two at a ratio of 7:1 respectively
        JPanel bottom = new JPanel(new GridBagLayout());

        // The current player's hand
        GridBagConstraints handConstraints = new GridBagConstraints();
        handConstraints.gridx = 0;
        handConstraints.gridy = 0;
        handConstraints.gridwidth = 7;
        handConstraints.gridheight = 1;
        handConstraints.fill = GridBagConstraints.BOTH;
        handConstraints.weightx = 0.8;
        handConstraints.weighty = 1;
        hand = new JPanel();
        hand.setLayout(new BorderLayout());
        bottom.add(hand, handConstraints);

        // The buttons for playing, drawing, and skipping
        GridBagConstraints buttonsConstraints = new GridBagConstraints();
        buttonsConstraints.gridx = 7;
        buttonsConstraints.gridy = 0;
        buttonsConstraints.gridwidth = 1;
        buttonsConstraints.gridheight = 1;
        buttonsConstraints.fill = GridBagConstraints.BOTH;
        buttonsConstraints.weightx = 0.2;
        buttonsConstraints.weighty = 1;
        buttons = new JPanel();
        initializeButtons();
        bottom.add(buttons, buttonsConstraints);

        GridBagConstraints topConstraints = new GridBagConstraints();
        topConstraints.gridx = 0;
        topConstraints.gridy = 0;
        topConstraints.fill = GridBagConstraints.BOTH;
        topConstraints.weightx = 1;
        topConstraints.weighty = 0.67;
        center.add(top, topConstraints);

        GridBagConstraints bottomConstraints = new GridBagConstraints();
        bottomConstraints.gridx=0;
        bottomConstraints.gridy=1;
        bottomConstraints.fill = GridBagConstraints.BOTH;
        bottomConstraints.weightx = 1;
        bottomConstraints.weighty = 0.33;
        center.add(bottom, bottomConstraints);
    }

    /**
     * Initialize the buttons of this window.
     */
    private void initializeButtons() {
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        playButton = new JButton("Play");
        drawButton = new JButton("Draw");
        skipButton = new JButton("Skip");

        playButton.addActionListener(playDelegator);
        drawButton.addActionListener(playDelegator);
        skipButton.addActionListener(playDelegator);

        buttons.add(playButton);
        buttons.add(drawButton);
        buttons.add(skipButton);
    }

    /**
     * Initialize the footer of this window.
     */
    private void initializeFooter() {
        footer = new JPanel();
    }
}
