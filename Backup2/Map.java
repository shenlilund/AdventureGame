//Shenli Lund
//project 3
//CS 3250-001

import java.util.Scanner;

public class Map implements java.io.Serializable
{
	private String[][] map = new String[100][100];
	private int width;
	private int height;
	private String[][] key = new String[100][3];
	private String itemFile;
	private String[][][] items;


	//---------------------------Methods-----------------------------

	public void CreateMap(int index, String[] parts)
	{
		for (int i = 0; i < width; i++)
		{
			map[index][i] = parts[i+1];
		}
	}

	public void setItemFile(String iFile)
	{
		itemFile = iFile;
	}

	public void setKeys(String l, int count)
	{
		String parts[] =  l.split(";");
		key[count][0] = parts[0];
		key[count][1] = parts[1];
		key[count][2] = parts[2];
	}

	public void SetDimensions(String[] parts)
	{
		height = Integer.parseInt(parts[0]);
		width = Integer.parseInt(parts[1]);
		items = new String[height][width][10];

		for (int i=0; i<height; i++)
		{
			for (int h=0; h<width; h++)
			{
				for (int n=0; n<10; n++)
				{
					items[i][h][n] = "empty";
				}
			}
		}
	}

	public void CreateItems(String l)
	{
		String parts[] = l.split(";");
		int lh = Integer.parseInt(parts[0]);
		int lw = Integer.parseInt(parts[1]);

		for (int i=0; i<10; i++)
		{
			if (items[lh][lw][i].equals("empty"))
			{
				items[lh][lw][i] = parts[2];
				break;
			}
		}

	}

	public String[] getItems(int ns, int ew)
	{
		int count = 0;
		for (int i = 0; i < 10; i++)
		{
			if (!items[ns][ew][i].equals("empty"))
			{
				count++;
			}
		}

		String[] returnItems = new String[count];
		int index = 0;
		for (int h = 0; h < 10; h++)
		{
			if (!items[ns][ew][h].equals("empty"))
			{
				returnItems[index] = items[ns][ew][h];
				index++;
			}
		}

		return returnItems;
	}

	public boolean compareItem(String itm, int ns, int ew)
	{
		for (int i = 0; i < 10; i++)
		{
			if (items[ns][ew][i].equals(itm))
			{
				return true;
			}
		}
		return false;
	}

	public void takeItem(String itm, int ns, int ew)
	{
		for (int i = 0; i < 10; i++)
		{
			if (items[ns][ew][i].equals(itm))
			{
				items[ns][ew][i] = "empty";
				break;
			}
		}
	}

	public void dropItem(String itm, int ns, int ew)
	{
		for (int h = 0; h < 10; h++)
		{
			if (items[ns][ew][h].equals("empty"))
			{
				items[ns][ew][h] = itm;
				break;
			}
		}

	}


	public int getWidth()
	{
		return width;	
	}

	public int getHeight()
	{
		return height;
	}

	public String getLocation(int ns, int ew)
	{
		return map[ns][ew];
	}

	public String getKey(String k)
	{
		for (String[] item : key)
		{
			if (k.equals(item[0]))
			{
				return item[2];
			}
		}
		return "MapPics/out.png";
	}

	//Get Top Row -------------------------------------------------
	public String getTop1(int ns, int ew)
	{
		if (ns-2 < 0 || ew-2 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-2][ew-2]);
		}
	}
	public String getTop2(int ns, int ew)
	{
		if (ns-2 < 0 || ew-1 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-2][ew-1]);
		}
	}
	public String getTop3(int ns, int ew)
	{
		if (ns-2 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-2][ew]);
		}
	}
	public String getTop4(int ns, int ew)
	{
		if (ns-2 < 0 || ew+1 >=width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-2][ew+1]);
		}
	}
	public String getTop5(int ns, int ew)
	{
		if (ns-2 < 0 || ew+2 >=width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-2][ew+2]);
		}
	}


	//Get Sec Row -------------------------------------------------
	public String getSec1(int ns, int ew)
	{
		if (ns-1 < 0 || ew-2 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-1][ew-2]);
		}
	}
	public String getSec2(int ns, int ew)
	{
		if (ns-1 < 0 || ew-1 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-1][ew-1]);
		}
	}
	public String getSec3(int ns, int ew)
	{
		//upper mid
		if (ns-1 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-1][ew]);
		}
	}
	public String getSec4(int ns, int ew)
	{
		if (ns-1 < 0 || ew+1 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-1][ew+1]);
		}
	}
	public String getSec5(int ns, int ew)
	{
		if (ns-1 < 0 || ew+2 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns-1][ew+2]);
		}
	}


	//Get Mid Row -------------------------------------------------
	public String getMid1(int ns, int ew)
	{
		if (ew-2 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns][ew-2]);
		}
	}
	public String getMid2(int ns, int ew)
	{
		//middle left
		if (ew-1 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns][ew-1]);
		}
	}

	public String getMid3(int ns, int ew)
	{
		return getKey(map[ns][ew]);
	}

	public String getMid4(int ns, int ew)
	{
		//middle right
		if (ew+1 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns][ew+1]);
		}
	}
	public String getMid5(int ns, int ew)
	{
		//middle right
		if (ew+2 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns][ew+2]);
		}
	}

	//Get Four Row -------------------------------------------------
	public String getFour1(int ns, int ew)
	{
		if (ew-2 < 0 || ns+1 >= height)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+1][ew-2]);
		}
	}
	public String getFour2(int ns, int ew)
	{
		//bottom left
		if (ew-1 < 0 || ns+1 >= height)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+1][ew-1]);
		}
	}

	public String getFour3(int ns, int ew)
	{
		//bottom mid
		if (ns+1 >= height)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+1][ew]);
		}
	}

	public String getFour4(int ns, int ew)
	{
		//bottom right
		if (ns+1 >= height || ew+1 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+1][ew+1]);
		}
	}
	public String getFour5(int ns, int ew)
	{
		if (ns+1 >= height || ew+2 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+1][ew+2]);
		}
	}

	//Get Bot Row -------------------------------------------------
	public String getBot1(int ns, int ew)
	{
		if (ns+2 >= height || ew-2 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+2][ew-2]);
		}
	}
	public String getBot2(int ns, int ew)
	{
		if (ns+2 >= height || ew-1 < 0)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+2][ew-1]);
		}
	}
	public String getBot3(int ns, int ew)
	{
		if (ns+2 >= height)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+2][ew]);
		}
	}
	public String getBot4(int ns, int ew)
	{
		if (ns+2 >= height || ew+1 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+2][ew+1]);
		}
	}
	public String getBot5(int ns, int ew)
	{
		if (ns+2 >= height || ew+2 >= width)
		{
			return getKey("-");
		}
		else
		{
			return getKey(map[ns+2][ew+2]);
		}
	}



	public void Print(int ns, int ew)
	{
		//upper left
		if (ns-1 < 0 || ew-1 < 0)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns-1][ew-1]);
		}
		//upper mid
		if (ns-1 < 0)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns-1][ew]);
		}
		//upper right
		if (ns-1 < 0 || ew+1 >= width)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns-1][ew+1]);
		}
		System.out.println();
		//middle left
		if (ew-1 < 0)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns][ew-1]);
		}
		//middle 
		System.out.print(map[ns][ew]);
		//middle right
		if (ew+1 >= width)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns][ew+1]);
		}
		System.out.println();
		//bottom left
		if (ew-1 < 0 || ns+1 >= height)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns+1][ew-1]);
		}
		//bottom mid
		if (ns+1 >= height)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns+1][ew]);
		}
		//bottom right
		if (ns+1 >= height || ew+1 >= width)
		{
			System.out.print("X");
		}
		else
		{
			System.out.print(map[ns+1][ew+1]);
		}
		System.out.println();
	}

}
