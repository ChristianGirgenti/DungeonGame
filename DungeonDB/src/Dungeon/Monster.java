package Dungeon;

public class Monster {

	String name;
	int strongValue;
	
	public Monster(String name, int strongValue) {
		this.name = name;
		this.strongValue=strongValue;	
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getStrongValue()
	{
		return strongValue;
	}
	
	public void setName (String newName)
	{
		this.name=newName;
	}
	
	public void setStrongValue (int newStrongValue)
	{
		this.strongValue=newStrongValue;
	}

}
