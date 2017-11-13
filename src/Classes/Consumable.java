package Classes;

public class Consumable extends Item
{	
	protected int healthEffect;	
	protected int maxHealthEffect;
	
	public Consumable() 
	{	
		healthEffect = 0;
		maxHealthEffect = 0;
	}
	public Consumable(String loadedString) {
		//loaded from file
		//int itemID, String itemName, int healthEffect, int maxHealthEffect
		//String itemDescShort, String itemDescLong
		try {
			String[] splitString = loadedString.split("|");
			itemId = Integer.parseInt(splitString[0]);
			itemName = splitString[1];
			healthEffect = Integer.parseInt(splitString[2]);
			maxHealthEffect = Integer.parseInt(splitString[3]);
			itemDescShort = splitString[4];
			itemDescLong = splitString[5];
		}catch(Exception E)
		{
			System.out.println("Text Spencer Williams to fix this. I might have messed up a TextFile."
					+ "I can fix it in like 30 minutes. Include Consumable in the text message");
		}
	}
	public Consumable(int itemID, String itemName, String itemDescShort, String itemDescLong, int healthEffect, int maxHealthEffect) 
	{
		super(itemID, itemName, itemDescShort, itemDescLong);
		this.healthEffect = healthEffect;
		this.maxHealthEffect = maxHealthEffect;
	}

	public int getHealthEffect() 
	{
		return healthEffect;
	}

	public void setHealthEffect(int healthEffect) 
	{
		this.healthEffect = healthEffect;
	}
	
	public void use()
	{
		
	}

	@Override
	public String toString() 
	{
		return "Consumable [healthEffect=" + healthEffect + "]";
	}
}