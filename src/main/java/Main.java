import controllers.GameBridge;
import controllers.GameCreationController;
import database.CSVDatabase;
import entities.*;
import enums.Rank;
import enums.Suit;
import enums.WindowName;
import ui.factories.*;
import ui.windows.*;
import use_cases.DataAccess;
import use_cases.GameCreationInteractor;
import use_cases.PlayerGameInteractor;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Deck deck = new StandardDeck();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Card> cards1 = new ArrayList<>();
        cards1.add(new Card(Suit.CLUB, Rank.ACE));
        cards1.add(new Card(Suit.CLUB, Rank.TWO));
        players.add(new HumanPlayer("Player 1", new Hand(cards1), 0, 0));

        ArrayList<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(Suit.HEART, Rank.THREE));
        cards2.add(new Card(Suit.HEART, Rank.FOUR));
        players.add(new HumanPlayer("Player 2", new Hand(cards2), 0, 0));

        Game game = new Game(deck, players);
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        ICardLayoutManager layoutManager = new CardLayoutManager(frame);

        MenuWindowFactory menuFactory = new MenuWindowFactory();
        RuleWindowFactory ruleFactory = new RuleWindowFactory();
        StatsWindowFactory statsFactory = new StatsWindowFactory();
        GameWindowFactory factory = new GameWindowFactory();
        CreationWindowFactory creationWindowFactory = new CreationWindowFactory();

        Window menuWindow = menuFactory.createWindow();
        Window ruleWindow = ruleFactory.createWindow();
        Window statsWindow = statsFactory.createWindow();
        Window creationWindow = creationWindowFactory.createWindow();
        DataAccess database = new CSVDatabase();
        CreationDisplay creationDisplay = (CreationDisplay) creationWindow.getPanel();
        GameCreationController creationController =  creationDisplay.getController();
        GameCreationInteractor creationInteractor = creationController.getInteractor();
        creationInteractor.setDataAccess(database);
        StatsDisplay statsDisplay = (StatsDisplay) statsWindow.getPanel();
        StatsController controller = statsDisplay.getDelegator().getController();
        controller.setDatabase(database);
        Window gameWindow = factory.createWindow();


        layoutManager = new CardLayoutManager(frame);
        layoutManager.addPane(menuWindow);
        layoutManager.addPane(ruleWindow);
        layoutManager.addPane(statsWindow);
        layoutManager.addPane(gameWindow);
        layoutManager.addPane(creationWindow);

        PaneDelegator paneDelegator = new PaneDelegator(layoutManager);
        menuWindow.setNavigator(paneDelegator);
        statsWindow.setNavigator(paneDelegator);
        ruleWindow.setNavigator(paneDelegator);

        GameDisplay gameDisplay = (GameDisplay) gameWindow.getPanel();
        GameController gameController = gameDisplay.getController();
        creationDisplay.setGameController(gameController);
        creationDisplay.setLayoutManager((CardLayoutManager) layoutManager);


        frame.setVisible(true);

        PlayerGameInteractor playerGameInteractor = null;
        while (playerGameInteractor == null) {
            playerGameInteractor = creationController.getPlayerGameInteractor();
        }
        gameController.setBridge((GameBridge) playerGameInteractor);


        layoutManager.setPane(WindowName.GAME);
    }
}
