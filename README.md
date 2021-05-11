# My Personal Project

## Portable Pokedex

This project is a **Portable Pokedex**. 

#### What is this application?:

- You can register yourselves as a Trainer, which includes your name, Trainer ID, number of Badges, and Pokemon caught.
Other features include searching and viewing the details of a Pokemon using it ID and a random Pokemon generator.

#### Who is it for>:

- This project is just for a user to have fun. A pokemon enthusiast like me would definitely enjoy this. I'm looking to expand this to more Pokemon, maybe even add Items in the future.      If the graphical interface (next phase of my project) is good enough, this could be sued as a tool with the various Pokemon games available.

#### Why is this project of interest to you?:

- This project is part of my Term project for ***CPSC 210*** at UBC. I pondered over many ideas like (Budget calculator, TO-DO list, etc.) but they all felt a bit too cliche and mainstream to me. I wanted to do something out of the way. Being a Pokemon enthusiast, I choose this topic.


## User Stories

- As a user, I want to be able to register as a Trainer and fetch my details.
- As a user, I should be able to look up all the Pokemon in the database at any time.
- As a user, I should be able to catch and release Pokemon.
- As a user, I should be able to add Pokemon (object of class X) to my Pokemon (ArrayList in every object of class Trainer).
- As a user, I want to view all my Pokemon
- As a user, I want to be able to collect up to 8 badges.
- As a user, my details are saved automatically whenever I update anything.
- As a user, my details are stored even when I quit the application.
- As a user, I can retrieve all my trainer details when I use the application in the future.

# DIRECTIONS FOR THE TA #
- The project represents a Pokedex. Once on the Welcome Screen, you can search pokemons with ID (1-151), or view a random Pokemon
- The user can load details of an Existing Trainer or register themselves as a new Trainer (by entering the required details).
- A registered trainer can view their details, their pokemon, catch and release pokemon, add a new badges (up to a total of 8).
- A picture is displayed when JUMP IN is pressed and a song plays in the background once the program is started (AUDIO VISUAL COMPONENT)
- View Pokemon option in trainer options uses the required list view

# Phase 4: Task 2 #
Made Trainer class robust using AllBadgesException (thrown when all badges are collected) and NotCaughtException (when the pokemon hasn't been caught yet).

# Phase 4: Task 3 #
- Made the goBack and exit method in each gui class as a separate class with the method that is called every time with a corresponding FXML file reducing duplication and cohesion.
- Made every method that does a button press to display a nes scene into a separate class where the method is called every time with a corresponding FXML file to reduce cohesion.
- New Pokemon Manager class that handles all catching and releasing pokemon to reduce cohesion.