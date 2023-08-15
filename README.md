# Crazy Cards

Crazy cards is a single/multiplayer card game (crazy 8s) that can be played between users and/or computers. Throughout
the code and documentation, we use player and user interchangeably.

## How To Run The Game

Run the file [src/main/Main.java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/blob/main/src/main/java/Main.java).

## GUI

## Gameplay

## Data Persistence
Players are able to save their progress and resume it. By entering their username, our program locates the player in our database.
If the player is found, the program restores their number of wins and losses; if not, it creates a new player with no wins and losses. 
This is done by reading and writing to a csv file.



## Code Organization and Style
The code is organized by packages adhering to clean architecture. Packages include entities, use_cases, controllers, database, and UI (LIST OTHERS HERE). 

## Testing and Coverage
Under [src/test/java](https://github.com/CSC207-2023Y-UofT/course-project-crazy-cards/tree/main/src/test/java) include comprehensive tests for the various functionalities of the game. Javadocs are included to further explain each test.

#### What we haven't tested:


## Clean Architecture and Design Decisions/Patterns

### Clean Architecture

### Design Patterns

#### Observer

#### Factories

### Design Decisions

use of manager, access..... making a Game accessible only via interfaces

## Extensibility

## Future Updates

## Known Bugs




