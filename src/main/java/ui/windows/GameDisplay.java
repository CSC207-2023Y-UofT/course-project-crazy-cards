package ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import ui.components.DrawnCard;
import ui.components.DrawnHand;
import ui.enums.Rank;
import ui.enums.Suit;
import ui.factories.GameWindowFactory;

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

    public GameDisplay(GameDelegator gameDelegator, CardDelegator cardDelegator) {
        this.playDelegator = gameDelegator.getPlayDelegator();
        this.skipDelegator = gameDelegator.getSkipDelegator();
        this.drawDelegator = gameDelegator.getDrawDelegator();
        this.cardDelegator = cardDelegator;

        initializeGUIComponents();
    }

    public void setCurrentPlayer(String player) {
        this.currentPlayer = player;
    }

    public void setHandsByPlayer(HashMap<String, DrawnHand> handsByPlayer) {
        for (String player : handsByPlayer.keySet()) {
            DrawnHand hand = handsByPlayer.get(player);
            hand.addMouseListener(cardDelegator);
        }

        this.handsByPlayer = handsByPlayer;
    }

    private void initializeGUIComponents() {
        setLayout(new BorderLayout());

        initializeHeader();
        initializeCenter();
        initializeFooter();

        // header.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        // center.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
        // footer.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        add(header, BorderLayout.PAGE_START);
        add(center, BorderLayout.CENTER);
        add(footer, BorderLayout.PAGE_END);
    }

    private void initializeHeader() {
        header = new JPanel();
        currentPlayerLabel = new JLabel(currentPlayer);
        header.add(currentPlayerLabel);
    }

    private void initializeCenter() {
        center = new JPanel();
        center.setLayout(new GridBagLayout());

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());

        GridBagConstraints scoresConstraints = new GridBagConstraints();
        scoresConstraints.gridx = 0;
        scoresConstraints.gridy = 0;
        scoresConstraints.gridwidth = 1;
        scoresConstraints.gridheight = 1;
        scoresConstraints.fill = GridBagConstraints.BOTH;
        scoresConstraints.weightx = 0.33;
        scoresConstraints.weighty = 1;
        JPanel scores = new JPanel();
        // JButton scores = new JButton("Scores");

        scores.setBorder(BorderFactory.createLineBorder(Color.RED, 4)); //TODO

        top.add(scores, scoresConstraints);

        GridBagConstraints gameBoardConstraints = new GridBagConstraints();
        gameBoardConstraints.gridx = 1;
        gameBoardConstraints.gridy = 0;
        gameBoardConstraints.gridwidth = 3;
        gameBoardConstraints.gridheight = 1;
        gameBoardConstraints.fill = GridBagConstraints.BOTH;
        gameBoardConstraints.weightx = 0.67;
        gameBoardConstraints.weighty = 1;
        gameBoard = new JPanel();
        // JButton gameBoard = new JButton("Game Board");

        gameBoard.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4)); //TODO

        top.add(gameBoard, gameBoardConstraints);


        // _____________________________________________________________

        JPanel bottom = new JPanel(new GridBagLayout());

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

        hand.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 4)); //TODO

        bottom.add(hand, handConstraints);

        GridBagConstraints buttonsConstraints = new GridBagConstraints();
        buttonsConstraints.gridx = 7;
        buttonsConstraints.gridy = 0;
        buttonsConstraints.gridwidth = 1;
        buttonsConstraints.gridheight = 1;
        buttonsConstraints.fill = GridBagConstraints.BOTH;
        buttonsConstraints.weightx = 0.2;
        buttonsConstraints.weighty = 1;
        buttons = new JPanel();

        buttons.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4)); //TODO

        initializeButtons();
        bottom.add(buttons, buttonsConstraints);

        GridBagConstraints test = new GridBagConstraints();
        test.gridx = 0;
        test.gridy = 0;
        test.fill = GridBagConstraints.BOTH;
        test.weightx = 1;
        test.weighty = 0.67;
        top.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4)); //TODO
        center.add(top, test);

        test.gridy=1;
        test.weighty = 0.33;
        bottom.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4)); //TODO
        center.add(bottom, test);
    }

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

    private void initializeFooter() {
        footer = new JPanel();
    }

    public void switchHand(String player) {
        currentPlayer = player;
        currentPlayerLabel.setText(currentPlayer);

        hand.removeAll();
        DrawnHand currentHand = handsByPlayer.get(currentPlayer);
        currentHand.updateCards();
        hand.add(currentHand);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));

        GameWindowFactory factory = new GameWindowFactory();
        Window gameWindow = factory.createWindow();

        GameDisplay gameDisplay = (GameDisplay)gameWindow.getPanel();
        DrawnHand hand = new DrawnHand(new ArrayList<>());

        ArrayList<DrawnCard> cards = new ArrayList<DrawnCard>();
        cards.add(new DrawnCard(Suit.CLUB, Rank.ACE));
        cards.add(new DrawnCard(Suit.CLUB, Rank.TWO));
        cards.add(new DrawnCard(Suit.CLUB, Rank.THREE));
        cards.add(new DrawnCard(Suit.HEART, Rank.ACE));
        cards.add(new DrawnCard(Suit.HEART, Rank.TWO));
        cards.add(new DrawnCard(Suit.HEART, Rank.THREE));

        hand.addCards(cards);
        gameDisplay.setHandsByPlayer(new HashMap<String, DrawnHand>() {{
            put("Player 1", hand);
        }});
        gameDisplay.switchHand("Player 1");

        frame.add(gameWindow.getPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
