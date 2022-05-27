Chess
Authors: Nathaniel Welch, Nathan Jenkins, Christen Kangas, Melanney Orta
Date: 05/27/2022
Version: 0.4

Description:
This program simulates a game of Chess using a graphical user interface. Players
are given the option of choosing the colors of each tile on the board.
Currently, this program is played with two players at the same computer,
therefore user input is taken for two players for their names. When playing on
the board, the chess board displays white pieces as white pictures at the top
and black pieces as black pictures at the bottom. Users move their chess pieces
by clicking the button the chess piece they want to move is on, and then
clicking where they want the piece to move to. The possible moves for any
given piece are displayed in yellow. Players are also free to capture pieces
when it is possible for their piece to do so. This version of ChessMeister
supports check and partially supports checkmate.

How to run:

First make sure you have the Java 11 JDK installed and that you have JavaFX 11
installed and imported in as a library for the project. Then on the command
line type

javac *.java

This will compile all the files to allow you to play. After this type

java Main

To begin running the program