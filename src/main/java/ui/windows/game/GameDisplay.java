package ui.windows.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

import controllers.data_objects.CardDisplayData;
import controllers.data_objects.GameDisplayData;
import controllers.interfaces.GameUI;
import enums.Rank;
import enums.Suit;
import enums.WindowName;
import ui.components.DrawnCard;
import ui.components.DrawnHand;
import ui.components.NavigationButton;
import ui.components.WrappingLabel;
import ui.windows.layout_managers.PaneDelegator;

/**
 * Renderer for a game window.
 */
public class GameDisplay extends JPanel implements GameUI {
    private static final String[] COLUMNS = {"Player name", "# of cards"};
    private static final String EMPTY_LABEL = "";

    private GamePlayDelegator playDelegator;
    private GameSkipDelegator skipDelegator;
    private GameDrawDelegator drawDelegator;
    private CardDelegator cardDelegator;
    private SwitchUpdater switchUpdater;
    private DrawnHand currentHand;
    private DrawnCard currentCard;

    private JPanel header;
    private JLabel currentPlayerLabel;

    private JPanel center;
    private JTable scores;
    private String[][] playerInfo;
    private JPanel gameBoard;
    private JLabel feedback;
    private JButton playButton;
    private JButton drawButton;
    private JButton skipButton;
    private JPanel buttons;

    private JPanel footer;

    private NavigationButton winReturnButton;
    private String currentPlayer;

    /**
     * Construct a GameDisplay with the given delegators.
     * @param gameDelegator the delegator for game actions
     * @param cardDelegator the delegator for card actions
     * @param switchUpdater the switch updater
     */
    public GameDisplay(GameDelegator gameDelegator, CardDelegator cardDelegator, SwitchUpdater switchUpdater) {
        this.playDelegator = gameDelegator.getPlayDelegator();
        this.skipDelegator = gameDelegator.getSkipDelegator();
        this.drawDelegator = gameDelegator.getDrawDelegator();
        this.cardDelegator = cardDelegator;
        this.switchUpdater = switchUpdater;

        initializeGUIComponents();
    }

    /**
     * Updates the view the user(s) see. Includes whose turn it is, the current player's hand, the current card in play
     * and the rest of the players, excluding the current player, and their respective number of cards remaining.
     * @param data The GameDisplayData needed to know the current state of the game, in order to update.
     */
    @Override
    public void updateView(GameDisplayData data) {
        updateCurrentPlayer(data.getCurrentPlayer());
        boolean winner = data.getHasWinner();
        if(winner) {
            winScreen();
        }
        updateHand(data.getCards());
        updateFeedback(data.getFeedbackMessage());
        updateCurrentCard(data.getCurrentCard());
        updateScoreBoard(data.getPlayersAndNumCards());
    }

    /*
     * Adds a PaneDelegator to this instance.
     */
    public void setNavigator(PaneDelegator navigator) {
        this.winReturnButton.addActionListener(navigator);
    }

    /**
     * Gets the game state after the game is started.
     */
    public void onSwitch() {
        switchUpdater.update();
    }

    /*
     * Draws the win screen popup.
     */
    private void winScreen() {
        String winner = currentPlayer;
        winReturnButton.setPreferredSize(new Dimension(200, 50));
        NavigationButton[] buttons = {winReturnButton};
        JLabel message = new JLabel(winner + " has won!");
        message.setFont(new Font("serif", Font.BOLD, 20));
        int num = JOptionPane.showOptionDialog(this, message,
                "Game is over", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, buttons, 0);
        if(num == JOptionPane.YES_OPTION) {
            winReturnButton.doClick();
        }
    }

    /**
     * Updates the current player.
     * @param currentPlayer The name of the new current player.
     */
    private void updateCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.currentPlayerLabel.setText(currentPlayer + "'s Turn!");
    }

    /**
     * Update the feedback label.
     * @param feedback The new feedback to display.
     */
    private void updateFeedback(String feedback) {
        this.feedback.setText(feedback);
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
     * Updates the current card in play.
     * @param data The CardDisplayData of the current card in play.
     */
    private void updateCurrentCard(CardDisplayData data) {
        Suit newSuit = data.getSuit();
        Rank newRank = data.getRank();
        currentCard.setRank(newRank);
        currentCard.setSuit(newSuit);
        currentCard.setVisible(true);
    }

    /**
     * Updates the scoreboard to match the number of cards each player has.
     * @param map A HashMap of player names to the number of cards they have.
     */
    private void updateScoreBoard(HashMap<String, Integer> map) {
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

    /**
     * Initialize the GUI components of this window.
     */
    private void initializeGUIComponents() {
        winReturnButton = new NavigationButton(WindowName.MENU, "Go to Menu");
        // Placeholder values before initial game data is requested
        this.currentCard = new DrawnCard(Suit.HEART, Rank.ACE);
        this.playerInfo = new String[][]{{"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}, {"", ""}};
        currentHand = new DrawnHand(new ArrayList<>());
        // Limit hand size to 15 cards
        for(int i = 0; i < 15; i++){
            currentHand.addCard(new DrawnCard(Suit.HEART, Rank.ACE));
        }
        currentHand.addMouseListener(cardDelegator);

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
        currentPlayerLabel.setFont(new Font("serif", Font.BOLD, 30));
        header.add(currentPlayerLabel);
    }

    /**
     * Initialize the center of this window.
     */
    private void initializeCenter() {
        EmptyBorder emptyBorder = new EmptyBorder(new Insets(10, 10, 10, 10));
        LineBorder outline = new LineBorder(Color.BLACK, 2);
        CompoundBorder border = new CompoundBorder(emptyBorder, outline);

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
        scorePanel.setLayout(new BorderLayout());
        EmptyBorder cornerPadding = new EmptyBorder(new Insets(10, 20, 0, 0));
        scorePanel.setBorder(cornerPadding);
        
        scores = new JTable(playerInfo, COLUMNS);
        scores.setPreferredSize(new Dimension(200, 180));
        scores.setRowHeight(30);
        scores.setFont(new Font("serif", Font.PLAIN, 25));
        JTableHeader header = scores.getTableHeader();
        header.setFont(new Font("serif", Font.PLAIN, 25));
        scorePanel.add(header, BorderLayout.NORTH);
        scorePanel.add(scores, BorderLayout.CENTER);
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
        currentHand.setBorder(border);
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
        buttons.setBorder(border);
        bottom.add(buttons, buttonsConstraints);

        GridBagConstraints topConstraints = new GridBagConstraints();
        topConstraints.gridx = 0;
        topConstraints.gridy = 0;
        topConstraints.fill = GridBagConstraints.BOTH;
        topConstraints.weightx = 1;
        topConstraints.weighty = 0.5;
        center.add(top, topConstraints);

        GridBagConstraints bottomConstraints = new GridBagConstraints();
        bottomConstraints.gridx = 0;
        bottomConstraints.gridy = 1;
        bottomConstraints.fill = GridBagConstraints.BOTH;
        bottomConstraints.weightx = 1;
        bottomConstraints.weighty = 0.5;
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

        playButton.setAlignmentX(CENTER_ALIGNMENT);
        drawButton.setAlignmentX(CENTER_ALIGNMENT);
        skipButton.setAlignmentX(CENTER_ALIGNMENT);

        buttons.add(playButton);
        buttons.add(drawButton);
        buttons.add(skipButton);
    }

    /**
     * Initialize the footer of this window.
     */
    private void initializeFooter() {
        footer = new JPanel();

        feedback = new JLabel(EMPTY_LABEL);
        feedback.setFont(new Font("serif", Font.PLAIN, 20));
        footer.add(feedback);
    }
}
