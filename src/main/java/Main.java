
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import entities.*;
import enums.Rank;
import enums.Suit;
import enums.WindowName;
import ui.components.NavigationButton;
import ui.factories.GameWindowFactory;
import ui.factories.MenuWindowFactory;
import ui.factories.RuleWindowFactory;
import ui.factories.StatsWindowFactory;
import ui.windows.CardLayoutManager;
import ui.windows.ICardLayoutManager;
import ui.windows.MenuWindow;
import ui.windows.PaneDelegator;
import ui.windows.Window;

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

        Window menuWindow = menuFactory.createWindow();
        Window ruleWindow = ruleFactory.createWindow();
        Window statsWindow = statsFactory.createWindow();
        Window gameWindow = factory.createWindow();

        layoutManager = new CardLayoutManager(frame);
        layoutManager.addPane(menuWindow);
        layoutManager.addPane(ruleWindow);
        layoutManager.addPane(statsWindow);
        layoutManager.addPane(gameWindow);

        PaneDelegator paneDelegator = new PaneDelegator(layoutManager);
        menuWindow.setNavigator(paneDelegator);
        statsWindow.setNavigator(paneDelegator);
        ruleWindow.setNavigator(paneDelegator);

        frame.setVisible(true);
    }
}
