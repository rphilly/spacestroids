# Project title
**Spacestroids!** - Java asteroids implementation using default libraries for CE301 Capstone Project Challenge

# Requirements
[Java runtime] (https://www.oracle.com/uk/java/technologies/javase-jre8-downloads.html)

# How to use
Simply launch the Spacestroids.jar, or if you are using an IDE run Launcher.java

# Design
Keeping the application organised the classes are split into packages respective of their purpose. Spacestroids is
built using a state-system where the default abstract class is State. Each individual application state inherits from the
base State class. 

All of the game entities (player, asteroids, bullets, explosions) inherit from the base Entity class. We use an ArrayList
of type Entity to update and render so we can have a singular code path for all entities currently in use. We store each
object properties such as positions, velocity and size using the Vector data structure to keep collections of objects
in an organised structure.

# Packages
* engine - Stores launcher to run application
 * view - Base gui classes for building application
* entity - Includes all objects used within the game state
* state - Provides the states switched between in application
 * ui - Provides user interface management for the states
* util - Stores utility classes used throughout application

# Classes
* Frame - builds application frame and initialises panel
* Panel - builds application panel and also runs game-loop
* Launcher - runs application

* Entity - base class for game entities which are updated and drawn on panel. Following classes inherit from this class
* Player - controllable player spaceship
* Asteroid - the asteroids drawn in game
* Bullet - a projectile shot by the player spacship
* Explosion - a graphical explosion in new thread produced on asteroids crashing

* State - base class for application states which are switched between upon usage
* Menu - initial state class for navigation of application
* Game - state class for rendering and updating game objects
* Name - class for inputing desired player name
* Options - state class for toggleable options
* Hiscores - class for drawing high-scores in menu
 * ClickListener - interface for clicking
 * UiObject - abstract class for defining bounds and functions for clickable objects
 * UiButton - inherits from UiObject and enables hovering in the menu buttons
 * UiManager - class to store list of clickable objects to update and render

* FontLoader - class to load the Spacestroids font
* ImageLoader - utility class to load all application sprites
* MouseHandler - utility class to manage mouse input throughout application
* SpriteLoader - class to load all the sprites used within the game state
* Vector2f - provides simple 2d vectors used for position, velocity and size of entities
