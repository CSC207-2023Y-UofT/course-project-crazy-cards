# Crazy Cards

Crazy cards is a single/multiplayer card game adapted from Crazy 8s. Compete against your friends or against our CPU
bots to see who can get rid of their cards the fastest. You can view our rules page and how to play page on more details
on the way our card game is played. Or, you can check your gameplay statistics, and see how many wins or losses you've 
accumulated. The rules of the game are simple, simply play a card that is similar in suit or rank
to the card that was played last, but beware, some cards may have some 
unintended side effects!

Note: Throughout the code and documentation, we use player and user interchangeably.

## How To Run The Game

Run the file [src/main/Main.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/Main.java).

## GUI & Gameplay
We coded the GUI by hand, that includes the placement of all the GUI elements, and even
the cards themselves, we hope you like it.

include screenshots of the game and put them in images folder 

## Data Persistence
Players are able to save their progress and resume it. By entering their username, our program locates the player in our database.
If the player is found, the program restores their number of wins and losses; if not, it creates a new player with no wins and losses.
This was done by utilizing [opencsv](https://opencsv.sourceforge.net/), a CSV parser library for Java.


## Code Organization, and Style
The code is organized by packages adhering to clean architecture. Packages include entities, use_cases, controllers, database, and UI.
Entities and UI classes have been divided further into subpackages based on the part of business logic
they belong to.

We used `lowerCamelCase` for variable and method names, `CamelCase` for class names,
`CONSTANT_CASE` for constants, and `pothole_case` for package names.


## Clean Architecture and SOLID
We made the effort to follow clean architecture principles. We have an interface seperating
each layer such that higher level modules don't depend on lower level modules, both depend
on abstractions. This includes `GameAccess` and `CreationAccess` that seperate 
the use case layer and the entity layer, `DataAccess` between the use cases and database layer, the
input boundary classes between the controllers and use cases, and the bridge classes between
the controllers and the UI/windows. This would also be good adherence to DIP.

Additionally, we made sure to follow ISP and SRP, as can be seen with `GameManager`, which
implements `GameAccess`, `CreationAccess`, and `ObservableGame`. These interfaces
are all segregated and kept up small. Additionally we have an interface and class solely for updating
`GameObserver` classes, this responsibility is kept away from `GameManager`, adhering to SRP. While it seems that
GameManager violates SRP due to the implementation of 3 different interfaces, at any one time,
it is only one of these, it serves to encapsulate `Game`, and is acted upon only one actor
at a particular time during execution. The program also follows OCP, which the Extensibility section
goes into further detail about. We also don't see any LSP violations, subclasses serve to extend functionality of their parent classes,
instead of overriding the parent class behaviours.

## Design Patterns
Our overall program functionality primarily follows the MVC design pattern. Through user input received by the window and display, 
information is sent to a delegator, which is then sent to a controller, which interacts with use cases and manipulates the entities. 
A response is then received by the controller and the packaged response is processed by visual components to display the new information 
to the user.<br>
![](images/design_pattern.png)

### Observer
Due to the event-driven nature of this program, we decided to use an observer
design pattern to monitor a ```Game``` instance, whose state changes throughout 
the program. There is information from a ```Game``` object which we wanted
to display to the user, such as information on whose turn it is, a given user's cards, how many cards
the other ```Player```s have, and the most recently played ```Card```
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
their own custom effects. There is a ``SpecialEffect`` interface, in which 
a ```Card``` has a ```SpecialEffect```, and when that ```Card``` is played, the special
effect is done. We have already made preset special effects such as reversing the turn,
swapping a `Hand` with another `Player`, skipping a turn, forcing a `Player` to draw cards, and randomly setting the current suit and rank
of a `Game` to something else. `SpecialEffect` instances can also be made through
the `EffectGenerationParameters` class, which introduces randomness in choosing a
`SpecialEffect` out of a list of them. Of course a user can choose to define their
own, even crazier, special effects.



## Future Updates
Besides bug fixes and making the code cleaner, we believe there is still much to do in the 
project. We could work on special effects that are not random, i.e. they require
user input, such as the cards from UNO and the 8 from the game Crazy8s. This would
may require a new type of special effect class, and most definitely requires special
pop up screens to be made. It would also require small tweaks to our current logic,
or another implementation of the `PlayerGameInputBoundary` interface. We also plan on working on giving users more choice
in the game modes and rules they want to play with. They can customize the game much more to their liking, and make them
as "crazy" as they want to. <br>
Outside of the gameplay itself, we want to update our persistence mechanism as well. Because of issues we were running
into during development, we chose to save to a .csv file for the sake of demonstrating the persistence mechanism as required
by the project. However, we want to upgrade this to our initial idea of serialization. We would probably store more data
about the user as well, such as longest win streak, total games played, and etc. <br>
And lastly, as more general improvements, we want to address any of the remaining open issues on our GitHub page, and
improve the GUI in general. These are just some of the things we've though up of, but we do plan on continuing work on this
project in the future.

## Known Bugs
Sometimes when running the program, a card may look out of place or
glitched out on the screen. We believe this to be a Swing "bug", 
however it can be fixed in most cases by just resizing the screen
to something bigger. Alternatively, closing out of the GUI and restarting it should work too. Although these may have an
effect on the visuals of the game, there should not be any differences in how the game actually plays. All functions of
the game are not affected by this card display "bug".




