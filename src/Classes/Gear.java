package Classes;

public class Gear extends Item
{
	protected int defense;
	protected int attackMin;
	protected int attackMax;
	protected int acc;
	protected char itemslot;
	
	public Gear() 
	{

		this.defense = 0;
		this.attackMin = 0;
		this.attackMin = 0;
		this.acc = 0;
		this.itemslot = 0;


	}
	public Gear(int itemId, String itemName, String itemDesc, int defense, int attackMin, int attackMax, int acc, char itemslot)
	{
		super(itemId, itemName, itemDesc);
		this.defense = defense;
		this.attackMin = attackMin;
		this.attackMax = attackMax;
		this.itemslot = itemslot;
		this.acc = acc;
		this.itemslot = itemslot;
	}
	public Gear(String loadedString)
	{
		//loaded from file
		//int itemId, String itemName, char slot, int defense, 
		//int attackMin, int attackMax,int acc,
		//String itemDescShort, String itemDescLong
		
		//protected int itemId;
		//protected String itemName;
		//protected String itemDesc;
		//protected int defense;
		//protected int attack;
		//protected char itemslot;
		try 
		{
			String[] splitString = loadedString.split("|");
			itemId = Integer.parseInt(splitString[0]);
			itemName = splitString[1];
			itemslot = splitString[2].charAt(0);
			defense = Integer.parseInt(splitString[3]);
			attackMin = Integer.parseInt(splitString[4]);
			attackMax = Integer.parseInt(splitString[5]);
			acc = Integer.parseInt(splitString[6]);
			itemDesc = splitString[7];
			
			
		}catch(Exception E)
		{
			System.out.println("Text Spencer Williams to fix this. I might have messed up a TextFile."
					+ "I can fix it in like 30 minutes. Include Gear in the text message");
		}
		
	}
	
	public int getDefense() 
	{
		return defense;
	}
	public int getAttack() 
	{
		return attackMin;
	}
	public char getItemslot()
	{
		return itemslot;
	}

	public void setDefense(int defense) 
	{
		this.defense = defense;
	}
	public void setAttack(int attack)
	{
		this.attackMin = attack;
	}
	public void setItemslot(char itemslot) {
		this.itemslot = itemslot;
	}
	public void equip()
	{
		
	}

	@Override
	public String toString()
	{
		return "Gear [defense=" + defense + "][attack=" + attackMin + "]";
	}
}