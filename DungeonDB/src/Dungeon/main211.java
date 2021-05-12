package Dungeon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main211 {

	public static void main(String[] args) {
			
			int numberOfMonsters = 10;
			int score=0;
		
			Player myPlayer = new Player();
			
			Monster skeleton = new Monster ("Skeleton", 2);
			Monster goblin = new Monster ("Goblin", 5);
			Monster witch = new Monster ("Witch", 8);
			Monster dragon = new Monster ("Dragon", 10);
			
			ArrayList<Monster> monstersList = new ArrayList<Monster>();
			ArrayList<Monster> dungeonMonsterList = new ArrayList<Monster>();
			
			Random rnd = new Random();
			Scanner in = new Scanner(System.in);
			
			for (int i=0;i<numberOfMonsters;i++)
			{
				if (i==0)
					monstersList.add(dragon);
				else if (i<3)
					monstersList.add(witch);
				else if (i<6)
					monstersList.add(goblin);
				else 
					monstersList.add(skeleton);				
			}
		//ADD FIRST ELEMENT IN DUNGEON
			dungeonMonsterList.add(monstersList.get(rnd.nextInt(10)));
			
			
			System.out.println("In the dungeon there are "+dungeonMonsterList.size()+" monsters.");
			String choice = "";
		//ASK IF PLAYER WANTS MORE MONSTERS
			while (!choice.equals("no"))
			{
				System.out.println("Do you want to add another monster ? (yes/no)");
				choice = in.next();
				if (choice.equals("yes"))
				{
					dungeonMonsterList.add(monstersList.get(rnd.nextInt(10)));
					System.out.println("In the dungeon there are "+dungeonMonsterList.size()+" monsters.");
				}
				else if (choice.equals("no"))
					System.out.println("The adventure starts!");
			}	
			
		//FLAG THAT RECORDS IF LIFE GOES DOWN TO 0
			boolean youLost=false;
		//FLAG THAT RECORDS THE USE OF THE ITEM
			boolean flagItem=false;
		//FLAG THAT RECORDS IF YOU STILL HAVE THE ITEM TO BEAT SKELETONS	
			boolean antiSkeleton=true;
			
		//SCROLL THE LIST OF MONSTER TILL THE END OR TILL LIFE GOES NEGATIVE
			
			for (int i = 0;i<dungeonMonsterList.size() && youLost == false;i++)
			{
				String itemChoice="";
		//SHOW FIRST MONSTER
				System.out.print("Monster "+(i+1)+" is a "+dungeonMonsterList.get(i).getName()+".");
				
		//IF THE MONSTER IS A SKELETON AND THE ANTISKELETON IS ON, BEAT MONSERT WITH NO DMG
				if(dungeonMonsterList.get(i).getName().equals("Skeleton") && antiSkeleton==true)
					System.out.println("You have beaten "+dungeonMonsterList.get(i).name+" with no damage thanks to the anti skeleton!");
		//ELSE IF IT IS A WITCH, REMOVE ANTISKELETON!
				else if (dungeonMonsterList.get(i).getName().equals("Witch") && antiSkeleton==true)
				{
					antiSkeleton=false;
					System.out.println("You lost the antiskeleton");
				}
				
		//CHECK IF ITEM IS NOT USED. ASK IF YOU WANT TO USE EXCEPT IF THE MONSTER IS A SKELETON AND ANTISKELETON IS ACTIVE
				if (flagItem==false && !(dungeonMonsterList.get(i).getName().equals("Skeleton") && antiSkeleton==true))
				{
					
					while(!itemChoice.equals("yes")&&!itemChoice.equals("no"))
					{
						System.out.println("Do you want to use your sword? You will lose it once it is used. (yes/no)");
						itemChoice = in.next();
					}
		//IF PLAYER WANT TO USE ITEM, WIN THE BATTLE WITH NO DAMAGE AND ASSIGN TRUE TO FLAG ITEM SO THAT THE ITEM CANT BE USED AGAIN
					if (itemChoice.equals("yes"))
					{
						System.out.println("You have beaten "+dungeonMonsterList.get(i).name+" with no damage!");
						flagItem=true;
					}	
				}
		//IF PLAYER ANSWERED NO TO THE PREVIOUS QUESTION, CALCULATE THE DAMAGE NORMALLY
		//IF THE ITEM HAS BEEN USED (FLAGITEM==TRUE) AND THE ITEMCHOICE WAS NOT YES, CALCULATE THE DAMAGE NORMALLY
		//CONDITION && NEEDED BECAUSE WHEN WE USE THE ITEM, THE FLAG ITEM GET THE VALUE TRUE BUT WHEN WE GET IN THIS CONDITION
		//THE VALUE OF ITEMCHOICE WILL STILL BE YES. IN THAT CASE, THE DAMAGE WOULD BE CALCULATED EVEN IF WE HAVE USED THE ITEM.
		//EVEN IN THIS CASE WE NEED TO CHECK IF THE MONSTER IS A SKELETON AND IF THE ANTISKELETON IS STILL AVAILABLE
		//CALCULATE DAMAGE ONLY IF THE MONSTER IS NOT A SKELETON OR IF IT IS A SKELETON BUT ANTISKELETON IS FALSE
				if ((itemChoice.equals("no")||(flagItem==true && !itemChoice.equals("yes"))))
				{
					if(!dungeonMonsterList.get(i).getName().equals("Skeleton")||!antiSkeleton==true)
						calculateDamage(dungeonMonsterList, myPlayer, i);
				}	
				
				
		//BEFORE TO PASS TO THE NEXT MONSTER, CHECK THE HEALTH OF THE PLAYER, IF HE IS DEAD SHOW LOST MESSAGE AND SCORE OTHERWISE INCREASE THE SCORE BY 100
				if (myPlayer.health<0)
				{
					System.out.println("You have lost! :(");
					youLost=true;
				}
				else 
					score+=100;
			}
			
		//IF FLAG IS EQUAL FALSE, IT MEANS THE PLAYER IS STILL ALIVE SO THE DUNGEON HAS BEEN COMPLETED.
		//IF FLAG IS EQUAL TRUE, THE PLAYER DIED BEFORE THE DUNGEON WAS COMPLETE. 
		//SHOW SCORE IN ANY CASE.
			if (youLost==false)
				System.out.println("Congratulations!! You have beaten the dungeon with score "+score);
			else
				System.out.println("Unfortunally you have not complete the dungeon. Your score was "+score);
			
			Score myScore = new Score(score);
			printBestScore(myScore);
			
	
	}
	
	
	public static void calculateDamage(ArrayList<Monster> dungeonMonsterList, Player myPlayer, int i)
	{
		int monsterDmg = dungeonMonsterList.get(i).getStrongValue();
		int newHealth = myPlayer.health-monsterDmg;
		myPlayer.setHealth(newHealth);		
		System.out.println("It deals "+monsterDmg+" to you, leaving you with "+newHealth+" health");	
	}
	
	public static void printBestScore(Score score)
	{
		ScoreTracker tracker = new ScoreTracker("scores.db");
		tracker.scoreUpdate(score);
		
		Score naiveScore=new Score(0);
		Score[] scores = tracker.getScores();
		
		for (int i = 0;i<scores.length;i++)
		{
			if (scores[i].CompareTo(naiveScore))
			{
				naiveScore=scores[i];
			}
		}
		
		System.out.println("The best score is "+naiveScore.ToString());
		tracker.close();
	}

}
