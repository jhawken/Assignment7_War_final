Jon H
assignment 7

At this point I am very tired. Many hours working on this war card game. I spent my birthday writing this assignment. I have one bug that when a certain sequence of cards are put into the winners pile that an endless loop will occur as the sequence gets traded back and forth. I tried shuffling the discard pile but could not get that to work. In the end, I swapped the first two sequences to reduce the chances and added a break for the theoritical max battle.

I know cheesy but it is very close. I learned more Java than I expected to this semester. Thank you. Oh...it is written with notepad++ because it was easy and fast setup. IntelliJ project setup is still a pain for me.

War.java
Card.Java

Assignment 7 instructions:
This assignment is to create a java program that can simulate the card game "War"

See https://en.wikipedia.org/wiki/War_(card_game) for instructions on game play.

In the event that a player runs out of cards during a war/double war/triple war/etc, use their last remaining card to resolve the war, while their opponent continues to deal cards normally

This game should not be interactive, but you could add a separate interactive mode if you choose

Instead, your program should run 1000 independent games, and monitor the outcome to produce statistics.

Your program must write these statistics to a file called stats.txt


This project can be completed either individually, or in pairs.

If working with a partner, both students must submit identical code to blackboard, and specify their partner in a readme file


Finally, during development, you must use Github to track your progress

If you have never used Github, it is a version control system, and there are plenty of tutorials and guides - for example https://guides.github.com/activities/hello-world/

Please add the instructor - https://github.com/dtyler7 - to your github repository


Your output should include all of the following statistics in the following format:

For 1000 games ...

Average number of battles per game: 321.2

Average number of wars per game: 24.3

Average number of double wars per game: 0.5

Max number of battles in a game: 1234

Min number of battles in a game: 12

Max number of wars in a game: 123

Min number of wars in a game: 1