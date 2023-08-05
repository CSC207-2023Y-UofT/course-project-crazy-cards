# Crazy Cards

Crazy cards is a single/multiplayer card game (crazy 8s) that can be played between users and/or computers. Throughout
the code and documentation, we use player and user interchangeably.

## How To Run The Game

Run the file src/main/Main.java (ADD LINK HERE AFTER CREATED).

## Functionality & Features
### Feature 1: [Game Start](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/1)
Significant files in this feature include [Game.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/Game.java) (class),
[Deck.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/Deck.java) (interface),
[StandardDeck.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/StandardDeck.java) (class),
[Card.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/Card.java) (class),
[Hand.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/Hand.java) (class),
[Player.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/Player.java) (abstract class),
[ComputerPlayer.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/ComputerPlayer.java) (class),
[HumanPlayer.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/HumanPlayer.java) (class),
[ConcretePlayer.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/ConcretePlayer.java) (class),
[ObservableGame.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/ObservableGame.java) (interface),
and [GameObserver.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/GameObserver.java) (interface).
This feature creates all the entities required for the game (besides the player factories).

### Feature 2: [Statistics Page](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/2)
Significant files in this feature include [Window.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/Window.java) (interface),
[StatsWindow.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/StatsWindow.java) (class),
[StatsDelegator.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/StatsDelegator.java) (class),
[StatsController.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/StatsController.java) (class),
[StatsDisplay.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/StatsDisplay.java) (class),
and [StatsDisplayData.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/StatsDisplayData.java) (class).
This feature displays a statistics page to the user with the wins and losses associated with each username in players.csv (data storing file).
The statistics page is accessible through the home page.

### Feature 3: [Rules Page](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/3)
Significant files in this feature include [RulesWindow.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/RulesWindow.java) (class)
and [RulesDisplay.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/ui/windows/RulesDisplay.java) (class).
This feature displays the rules of the game. The rules page is accessible through the home page.

### Feature 4: [Play Card Feature](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/4)
Significant files in this feature include [PlayerGameController.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/controllers/PlayerGameController.java) (class),
[PlayerGameInputBoundary.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/use_cases/PlayerGameInputBoundary.java) (interface),
[PlayerGameInteractor.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/use_cases/PlayerGameInteractor.java) (class),
[PlayerGameRequestModel.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/use_cases/PlayerGameRequestModel.java) (class),
and [PlayerGameResponseModel.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/use_cases/PlayerGameResponseModel.java) (class).
This feature implements the functionality of a player (ComputerPlayer and HumanPlayer) playing a card.

### Feature 5: [Add Couch Co-op Players](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/5)
Significant files in this feature include [PlayerFactory.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/PlayerFactory.java) (interface),
[ComputerPlayerFactory.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/ComputerPlayerFactory.java) (class),
and [HumanPlayerFactory.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/entities/HumanPlayerFactory.java) (class).
This feature adds the multiplayer functionality to the game.

### Feature 6: [Game Creator](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/6)
TO BE COMPLETED

### Feature 7: [GetGameState](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/7)
Significant files in this feature include [GameState.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/use_cases/GameState.java) (class).
This feature implements the GameState class to assist with transitioning between player turns.

### Feature 8: [Save Player Stats](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/8)

Significant files in this feature
include [DataAccess.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/database/DataAccess.java) (interface), 
[CSVDatabase.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/database/CSVDatabase.java) (class), 
and [PlayerInformation.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/use_cases/PlayerInformation.java) (class). 
This feature saves the wins and losses of different players and retrieves their statistics by username. 
This allows players to continuously build and monitor their gaming progress.

### Feature 9: [Start Window](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/issues/17)
TO BE COMPLETED

###

## Code Organization
The code is organized by packages adhering to clean architecture. Packages include entities, use_cases, controllers, database, and UI (LIST OTHERS HERE).

## Testing
Under [src/test/java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/tree/main/src/test/java) include comprehensive tests for the various functionalities of the game. Javadocs are included to further explain each test.

## Design Patterns


