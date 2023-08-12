import controllers.GameCreationController;
import controllers.PlayerGameController;
import database.CSVDatabase;
import entities.GameManager;
import entities.IObserverNotifier;
import entities.ObserverNotifier;
import ui.factories.*;
import ui.windows.Window;
import ui.windows.layout_managers.CardLayoutManager;
import ui.windows.layout_managers.ICardLayoutManager;
import ui.windows.layout_managers.PaneDelegator;
import use_cases.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        GameManager manager = new GameManager();
        GameState state = new GameState(manager);
        manager.addObserver(state);

        DataAccess dataAccess = new CSVDatabase();
        manager.addObserver(dataAccess);

        IObserverNotifier notifier = new ObserverNotifier(manager);

        PlayerGameInputBoundary gameBoundary = new PlayerGameInteractor(manager, notifier, state);
        GameCreationInputBoundary creationBoundary = new GameCreationInteractor(dataAccess, manager);

        PlayerGameController gameController = new PlayerGameController(gameBoundary);
        GameCreationController creationController = new GameCreationController(creationBoundary);

        ICardLayoutManager layoutManager = new CardLayoutManager(frame);

        MenuWindowFactory menuFactory = new MenuWindowFactory();
        RuleWindowFactory ruleFactory = new RuleWindowFactory();
        StatsWindowFactory statsFactory = new StatsWindowFactory(new CSVDatabase());
        GameWindowFactory gameFactory = new GameWindowFactory(gameController);
        CreatorWindowFactory creatorFactory = new CreatorWindowFactory(creationController);

        Window menuWindow = menuFactory.createWindow();
        Window ruleWindow = ruleFactory.createWindow();
        Window statsWindow = statsFactory.createWindow();
        Window gameWindow = gameFactory.createWindow();
        Window creatorWindow = creatorFactory.createWindow();

        layoutManager.addPane(menuWindow);
        layoutManager.addPane(ruleWindow);
        layoutManager.addPane(statsWindow);
        layoutManager.addPane(gameWindow);
        layoutManager.addPane(creatorWindow);

        PaneDelegator paneDelegator = new PaneDelegator(layoutManager);
        menuWindow.setNavigator(paneDelegator);
        statsWindow.setNavigator(paneDelegator);
        ruleWindow.setNavigator(paneDelegator);
        creatorWindow.setNavigator(paneDelegator);

        frame.setVisible(true);
    }
}
