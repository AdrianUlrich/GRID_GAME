# GRID_GAME
Mini-Projet 2 du premier semestre 2018 de PRG_EPFL_IN_SC

=========================================================

# CONCEPTION

For the first 4 steps of the project we mostly followed the instruction PDF.

Implemented extension for step 5 include the following :

-A Foreground class based on the Background one, depth vector being modified for it to be drawn on top of all AreaEntities.

-An Animation class using an Array of Sprite and iterating over it to draw the correct current frame.

-A Talkable class, representing any Actor you can talk to and taking a String as an argument to specify the Sprite to use. 
The Talkable posseses a Dialog attribute to be drawn when the player is interacting with it.
The player has to interact with the Talkable again in order to close the Dialog and be able to move again.

-A RunningShoes class, being a collectable. When the player has collected one of them it allows him to run by holding the space bar pressed.
A running player moves at twice its normal speed.

-An ExplodingRock class working together with a Bomb class which is a collectable. 
Once the player has collected a Bomb, he's able to blow up ExplodingRocks by interacting with them. 
An Exploding ExplodingRock will blow up any adjecent or diagonal ExplosingRock if it was blown up by the player.

-A Teleporter Class, which is basically a Door with a specific Animation. We mostly use it to teleport the player to the same Area. 
We had to add a few paramaters to the Player update method in ordre for linked Portals not to constantly teleport back to each other. 
Therefor the player can only interact with a Teleporter or Door after using another one if he has successfully moved to another cell inbetween.

-

