package ui.windows.game;

import enums.Rank;
import enums.Suit;
import enums.WindowName;
import ui.components.DrawnCard;
import ui.components.DrawnHand;
import ui.components.NavigationButton;
import ui.windows.layout_managers.PaneDelegator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Renderer for a game window.
 */
public class GameDisplay extends JPanel{
    private GamePlayDelegator playDelegator;
    private GameSkipDelegator skipDelegator;
    private GameDrawDelegator drawDelegator;
    private SwitchUpdater switchUpdater;
    private CardDelegator cardDelegator;
    private DrawnHand currentHand;
    private DrawnCard currentCard;
    private JPanel header;
    private JPanel center;
    private JPanel footer;
    private JLabel currentPlayerLabel;
    private JTable scores;
    public static final String[] COLUMNS = {"Player name", "# of cards"};
    private String[][] playerInfo;
    private JPanel gameBoard;
    private JPanel hand;
    private JPanel buttons;
    private JButton playButton;
    private JButton drawButton;
    private JButton skipButton;
    private String currentPlayer;
    private final NavigationButton goToMenu = new NavigationButton(WindowName.MENU, "Go to Menu");

    /**
     * Construct a GameDisplay with the given delegators.
     * @param gameDelegator the delegator for game actions
     * @param cardDelegator the delegator for card actions
     */
    public GameDisplay(GameDelegator gameDelegator, CardDelegator cardDelegator, SwitchUpdater switchUpdater) {
        this.playDelegator = gameDelegator.getPlayDelegator();
        this.skipDelegator = gameDelegator.getSkipDelegator();
        this.drawDelegator = gameDelegator.getDrawDelegator();
        this.cardDelegator = cardDelegator;
        this.switchUpdater = switchUpdater;

        this.currentCard = new DrawnCard(Suit.HEART, Rank.ACE);
        this.playerInfo = new String[][]{{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}};

        currentHand = new DrawnHand(new ArrayList<>());
        for(int i = 0; i < 10; i++){
            currentHand.addCard(new DrawnCard(Suit.HEART, Rank.ACE));
        }
        currentHand.addMouseListener(cardDelegator);
        initializeGUIComponents();
    }

    public void onSwitch() {
        switchUpdater.update();
    }

    /**
     * Updates the view the user(s) see. Includes whose turn it is, the current player's hand, the current card in play
     * and the rest of the players, excluding the current player, and their respective number of cards remaining.
     * @param data The GameDisplayData needed to know the current state of the game, in order to update.
     */
    public void updateView(GameDisplayData data) {
        boolean winner = data.getHasWinner();
        currentPlayer = data.getCurrentPlayer();
        currentPlayerLabel.setText(data.getCurrentPlayer() + "'s Turn!");
        currentPlayerLabel.setFont(new Font("serif", Font.BOLD, 30));
        if(winner) {
            winScreen();
        }
        updateHand(data.getCards());
        Suit newSuit = data.getCurrentCard().getSuit();
        Rank newRank = data.getCurrentCard().getRank();
        currentCard.setRank(newRank);
        currentCard.setRankLabel(newRank);
        currentCard.setSuitLabel(newSuit);
        currentCard.setSuit(newSuit);
        currentCard.setVisible(true);
        HashMap<String, Integer> map = data.getPlayersAndNumCards();
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            String[] row = {key, value};
            playerInfo[index] = row;
            index++;
        }
        scores.repaint();
    }

    /*
     * Draws the winscreen popup.
     */
    private void winScreen() {
        String winner = currentPlayer;
        goToMenu.setPreferredSize(new Dimension(200, 50));
        NavigationButton[] buttons = {goToMenu};
        int num = JOptionPane.showOptionDialog(this, winner + " has won!",
                "Game is over", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, buttons, 0);
        if(num == JOptionPane.YES_OPTION) {
            goToMenu.doClick();
        }

    }

    /*
     * Adds a PaneDelegator to this instance.
     */
    public void setNavigator(PaneDelegator navigator) {
        this.goToMenu.addActionListener(navigator);
    }

    /**
     * Updates the hand to match which user the current turn belongs to.
     * @param cards The cards belonging to the new current user's hand, as an ArrayList of CardDisplayData.
     */
    private void updateHand(ArrayList<CardDisplayData> cards) {
        int i = 0;
        for (i = 0; i < cards.size(); i++) {
            CardDisplayData card = cards.get(i);
            currentHand.setCard(i, card.getSuit(), card.getRank());
        }
        currentHand.hideCards(i);
        currentHand.updateCards();
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
        currentPlayerLabel = new JLabel();
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
        scores = new JTable(playerInfo, COLUMNS);
        scores.setPreferredSize(new Dimension(200, 180));
        scores.setRowHeight(30);
        scores.setFont(new Font("serif", Font.PLAIN, 25));
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
        JLabel currCardLabel = new JLabel("Current card:");
        currCardLabel.setFont(new Font("serif", Font.PLAIN, 20));
        gameBoard.add(currCardLabel);
        gameBoard.add(currentCard);
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
        bottom.add(currentHand, handConstraints);

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

        Dimension dim = new Dimension(80, 40);
        Font font = new Font("serif", Font.BOLD, 20);

        playButton.setPreferredSize(dim);
        playButton.setFont(font);
        drawButton.setPreferredSize(dim);
        drawButton.setFont(font);
        skipButton.setPreferredSize(dim);
        skipButton.setFont(font);

        playButton.addActionListener(playDelegator);
        drawButton.addActionListener(drawDelegator);
        skipButton.addActionListener(skipDelegator);

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
