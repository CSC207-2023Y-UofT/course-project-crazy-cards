# Crazy Cards

Crazy cards is a single/multiplayer card game adapted from Crazy 8s. Compete against your friends or against our CPU
bots to see who can get rid of their cards the fastest. You can view our rules page and how to play page on more details
on the way our card game is played. Or, you can check your gameplay statistics, and see how many wins or losses you've 
accumulated.

Comments:<br>
Throughout the code and documentation, we use player and user interchangeably. Once you've entered the gameplay itself, 
feel free to maximize the program window. In the event that the cards you draw are extremely unlucky, this prevents the
cards from becoming hidden behind the action buttons beside your hand. Other screens/windows will be compatible with the
original screen size when the program is run.

## How To Run The Game

Run the file [src/main/Main.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/Main.java).

## GUI & Gameplay

include screenshots of the game and put them in images folder 

## Data Persistence
Players are able to save their progress and resume it. By entering their username, our program locates the player in our database.
If the player is found, the program restores their number of wins and losses; if not, it creates a new player with no wins and losses.
This was done by utilizing [opencsv](https://opencsv.sourceforge.net/), a CSV parser library for Java.


## Clean Architecture, Code Organization, and Style
The code is organized by packages adhering to clean architecture. Packages include entities, use_cases, controllers, database, and UI.

## Design Patterns
Our overall program functionality primarily follows the MVC design pattern. Through user input received by the window and display, 
information is sent to a delegator, which is then sent to a controller, which interacts with use cases and manipulates the entities. 
A response is then received by the controller and the packaged response is processed by visual components to display the new information 
to the user.
![](images/design_pattern.png)

### Observer
We used an observer design pattern where ```Game``` is an ```ObservableGame```
and ```GameStat```

Due to the event-driven nature of this program, we decided to use an observer
design pattern to monitor a ```Game``` instance, whose state changes throughout 
the program. There is information from a ```Game``` object which we would wanted
to display to the user, such as information on whose turn it is, their cards, 
the number of cards of other ```Player```s, and the last played ```Card```
in the ```Game```. Naturally this led to a ```GameState``` class which observes 
the ```Game``` and is updated with this information whenever there is a change. Later
on we decided to have a manager class for a game, and so the role of the 
```ObservableGame``` is given to ```GameManager```. Instead of a
```GameManager``` directly notifying observers, there is a ```ObserverNotifier```
class whose sole responsibility is to update observers. Later when attempting
to find a way update player's wins and losses to the database, we had the
idea of making the ```DataAccess``` interface extend ```GameObserver```, and
so everytime there is an update to a ```Game```, the ```DataAccess``` objects 
are updated. We use this as a way to write statistics to the database after
a ```Game``` has been won.

### Factories
We used simple factories on two occasions, we had factories for creating windows and
factories for players. Our ``Player`` factories helped to encapsulate the logic for creating
a ```Player```, such as loading the statistics for a ```HumanPlayer```. Our ```Window```
factories helped to encapsulate the creation logic required to create displays and delegators
used for the ```Window``` classes.

## Design Decisions

use of manager, access..... making a Game accessible only via interfaces


## Testing and Coverage
Under [src/test/java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/tree/main/src/test/java) include comprehensive
tests for the various functionalities of the game. Javadocs are included to further explain each test.

### What we haven't tested

## Extensibility
Firstly, we have made the type of database used to be easily extended. We 
defined a ```DataAccess``` interface in the use case layer, which is what the rest
of the program uses in loading data from the database. This interface is implemented
by ```CSVDatabase```, which writes and loads from a CSV file. In the future though,
since the process of this is slightly inconvenient, a separate class implementing
```DataAccess``` can be used in the case of another database.

We have also introduced extensibility and flexibility in users defining
their own custom effects.

## Future Updates

## Known Bugs
Sometimes when running the program, a card may look out of place or
glitched out on the screen. We believe this to be a Swing "bug", 
however it can be fixed in most cases by just resizing the screen
to something bigger.




