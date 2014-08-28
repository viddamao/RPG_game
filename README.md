Project 1 Game Design Plan
====

Wenjun Mao

Net ID: wm56

Genre: 

The game I am planning to design is a turn-based RPG game, which means you and the computer controlled monsters take turns walk around the map. The game mode is kind of like Wind Fantasy but kind of different.

Name: 

Legend of the paladin

The evil daemons concurred the world and as a paladin it’s your responsibility to defeat the daemons and save the world. 

Goal: 

Your goal is to get to a certain position on the map and destroy the power core of the daemons. (Winning condition)
Based on the hero type you choose, paladin or mage, you have certain amount of health points, if it goes below zero you would be dead.
To get into the hard level, you need to complete at least once in the normal mode. (Or by entering one cheat code you can unlock the hard mode)


How to play: 

The map is recorded by a 20*10 table (approximate value) with different types of landscapes.
The main controller is use up, down, left, and right key to control the movement of a cursor that moves block by block in the game window and “z” is confirm, “x” is return to select different types of actions.

X X X X X X X X X X X X X X X X X X 

X P P P P P P P P P P P P H H H H X

X H P F F P P P W W W W W W H H X

X ....etc.

In which X shows the border, 

P is plain where your hero and monsters can move and stay on,

H, W are hills and lakes where you can’t go across or stay on at the end of turn.

F is forest which gives the character +20% probability of evading attacks.

T are towns, if you stay in town at the end of turn you can recover lost HP, and search in town gives you chance to find gears that enhance the ability of your hero.

 Each turn you can move your hero once and choose one action, either attack, drink potions, explore the town (if stay in town), or end turn.

Paladin can move 5 blocks, and mage can move only 3 blocks.
And the attack range of Paladin is 1 and mage is 2.

For example:

X X X X X X X

X X X A X X X

X X A B A X X

X A B H B A X

X X A B A X X

X X X A X X X

X X X X X X X

If your hero is in position H, paladin can attack enemies in position B only and mages can use magic to attack enemies in A or B.


And different enemies got corresponding ranges too.

The moving mechanism of the monsters are as follows:

If distance(x)+distance(y) is less than 10, the monster moves towards the hero with its maximum movement distance after checking the validity of staying in certain position.
If after moving hero is in the attack range, it attacks the hero.
If distance larger than 10, the monster stays and do nothing.

How different level differ.

The hard mode has a boss like enemy with special attacks (to be decided).
 
