# DungeonGame
Console simulation of the "Welcome to the dungeon" table game.
This project has been implemented in Java using Eclipse. 

The simplified version of the game will only support a single player, with the sequence of play being as follows:
1. A number of monsters are created, each of which has a name and a strength value
  1.1 Skeleton strenght value 2
  1.2 Goblin strength value 5
  1.3 Witch strength value 8
  1.4 Dragon strength value 10
3. A player is created, with a fixed starting health value equal to 40
4. A monster is chosen at random, and added to the dungeon
5. The player is shown how many monsters are in the dungeon, and asked if they think they’ll be able to handle another one
6. Step 4 and 5 are repeated until the player decides that’s enough
7. The player proceeds through the dungeon, with the player’s health reduced by the strength value of each monster encountered
8. If the player’s health drops to 0 or below, they die and lose the game
9. If the player makes it through all the monsters without dying, they win, and their score for the game is 100 per each monster defeated.

Some special features have been added:
1. The player has an item that can use to defeat any of the monster of their choosing. For each monster the player get asked if he wants to use the item. If the item is used it will stop to get asked.
2. The user has another item which defeat skeletons without them damaging the player. This item will be lost by the player whenever they first encounter a witch.
3. All the scores will be stored in a DB using DB40 Object Database and the console will print both your score and the best score (reading it from the database). 
   The "com.db4o-8.1.9" library to make the database work is included in the lib folder inside the DungeonDB folder. The name of the db file will be scores.db. 
