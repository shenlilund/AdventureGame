//Shenli Lund
//project 6
//CS 3250-001


public class GameChar implements java.io.Serializable
{
	private String[] inventory = new String[10];
	/* private String[] inventory = {"brass lantern", "rope", "rations", "staff"}; */
	private int ns;
	private int ew;
	private int vision = 9;

	//----------------Constructor---------------------
	public GameChar()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			inventory[i] = "empty";
		}
	}

	//--------------------------------Methods-------------------------------	

	public void addInv(String item)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i].equals("empty"))
			{
				inventory[i] = item;
				break;
			}
		}
	}

	public boolean compareItem(String item)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i].equals(item))
			{
				return true;
			}
		}
		return false;
	}

	public void dropFromInv(String item)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i].equals(item))
			{
				inventory[i] = "empty";
				break;
			}
		}

	}

	public void GoNorth()
	{
		ns = ns-1;
	}
	public void GoSouth()
	{
		ns = ns+1;
	}
	public void GoEast()
	{
		ew = ew+1;
	}
	public void GoWest()
	{
		ew = ew-1;
	}

	public int getNS()
	{
		return ns;
	}

	public int getEW()
	{
		return ew;
	}

	public void SeeInv()
	{
		for(String item : inventory)
		{
			System.out.println(item);
		}
	}
	
	public String[] GetInv()
	{
		return inventory;
	}
}
