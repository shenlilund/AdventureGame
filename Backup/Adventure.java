//Shenli Lund
//project 6
//CS 3250-001


import java.util.Scanner;
import java.io.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.imageio.*;
import java.awt.Image;
import java.awt.image.*;
import javax.swing.text.DefaultCaret;
import static javax.swing.ScrollPaneConstants.*;

public class Adventure extends JFrame implements ActionListener
{
	//variables---------------------------
    private JButton openButton;
	private JButton quitButton;
	private JButton saveButton;
	private JTextField txtField;
	private JTextArea txtArea;
	private GameChar person;
	private Map m;
	private Bitmap ul;
	private Bitmap uc;
	private Bitmap ur;
	private Bitmap l;
	private Bitmap c;
	private Bitmap r;
	private Bitmap bl;
	private Bitmap bc;
	private Bitmap br;

	//constructor----------------------------------
    public Adventure() 
	{
		//Create objects
		person = new GameChar();
		m = new Map();

		//create the Bitmap objects (one for each space
		ul = new Bitmap();
		l = new Bitmap();
		bl = new Bitmap();
		uc = new Bitmap();
		c = new Bitmap();
		bc = new Bitmap();
		ur = new Bitmap();
		r = new Bitmap();
		br = new Bitmap();

		//North Panel ------------------------
		JPanel np = new JPanel();
		add(np, BorderLayout.NORTH);

		openButton = new JButton("Open");
		np.add(openButton);
		openButton.addActionListener(this);

		saveButton = new JButton("Save");
		np.add(saveButton);
		saveButton.addActionListener(this);

		quitButton = new JButton("Quit");
		np.add(quitButton);
		quitButton.addActionListener(this);

		//South Panel------------------------
		JPanel sp = new JPanel();
		add(sp, BorderLayout.SOUTH);

		txtField = new JTextField(20);
		txtField.addActionListener(this);
		sp.add(txtField);


		//West Panel--------------------------------
		JPanel wp = new JPanel();
		add(wp, BorderLayout.WEST);

		txtArea = new JTextArea(30, 20);
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		DefaultCaret caret = (DefaultCaret)txtArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane(txtArea); 
		scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		txtArea.setEditable(false);

		wp.add(scrollPane, BorderLayout.CENTER);

		//Center Panel----------------------------------
		JPanel cp = new JPanel(new GridLayout(3,3));

		cp.add(ul);
		cp.add(uc);
		cp.add(ur);
		cp.add(l);
		cp.add(c);
		cp.add(r);
		cp.add(bl);
		cp.add(bc);
		cp.add(br);

		RePaintAll();
		add(cp, BorderLayout.CENTER);

	}

	public void RePaintAll()
	{
		ul.repaint();
		uc.repaint();
		ur.repaint();
		l.repaint();
		c.repaint();
		r.repaint();
		bl.repaint();
		bc.repaint();
		br.repaint();
	}

    public void actionPerformed(ActionEvent e)
	{
		String text = txtField.getText();
		/* input = text; */
		txtArea.append(text + "\n");
		txtField.setText("");
		SendInput(text);

		if (e.getSource()==quitButton)
		{
			System.exit(0);
		}
	}


	//Main------------------------------------------------------
	public static void main(String[] args)
	{
		Adventure frame = new Adventure();
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Scanner fin = null;

		try
		{
			fin = new Scanner(new File(args[0]));
		}
		catch (FileNotFoundException x)
		{
			System.out.println("File open failed");
			x.printStackTrace();
			System.exit(0);
		}

		String line;
		int count = -1;
		int indexH = -1;
		
		//This loop gets the board
		while(fin.hasNextLine())
		{

			line = fin.nextLine();

			if (line.isEmpty())
			{
				break;
			}
			if (count == -1)
			{
				String[] parts = line.split(" ");
				frame.m.SetDimensions(parts);
				indexH = Integer.parseInt(parts[0]);
			}
			else
			{
				String[] parts = line.split("");
				frame.m.CreateMap(count, parts);
				if (indexH == 0)
				{
					break;
				}
			}
			indexH--;
			count++;
		}


		/* frame.ul.loadFile("MapPics/plain.png"); */
		/* frame.uc.loadFile("MapPics/forest.png"); */
		/* frame.ur.loadFile("MapPics/treasure.png"); */
		/* frame.l.loadFile("MapPics/plain.png"); */
		/* frame.c.loadFile("MapPics/forest.png"); */
		/* frame.r.loadFile("MapPics/treasure.png"); */
		/* frame.bl.loadFile("MapPics/plain.png"); */
		/* frame.bc.loadFile("MapPics/forest.png"); */
		/* frame.br.loadFile("MapPics/treasure.png"); */
		/* frame.RePaintAll(); */

		int indexN = 0;
		String itemFile = "";
		int indexK = 0;

		while (fin.hasNextLine())
		{
			if (indexN == 0)
			{
				line = fin.nextLine();
				String[] lineSplit = line.split(" ");
				int imageW = Integer.parseInt(lineSplit[0]);
				int imageH = Integer.parseInt(lineSplit[1]);

				//set the height for each bitmap image
				frame.ul.setW(imageW); frame.ul.setH(imageH);
				frame.uc.setW(imageW); frame.uc.setH(imageH);
				frame.ur.setW(imageW); frame.ur.setH(imageH);
				frame.l.setW(imageW); frame.l.setH(imageH);
				frame.c.setW(imageW); frame.c.setH(imageH);
				frame.r.setW(imageW); frame.r.setH(imageH);
				frame.bl.setW(imageW); frame.bl.setH(imageH);
				frame.bc.setW(imageW); frame.bc.setH(imageH);
				frame.br.setW(imageW); frame.br.setH(imageH);
				indexN++;
			}
			if (indexN == 1)
			{
				itemFile = fin.nextLine();
				frame.m.setItemFile(itemFile);
				indexN++;
			}
			else
			{
				line = fin.nextLine();
				/* System.out.println(line); */
				frame.m.setKeys(line, indexK);
				indexK++;
			}
		}

		Scanner itemIn = null;

		try
		{
			itemIn = new Scanner(new File(itemFile));
		}
		catch (FileNotFoundException xc)
		{
			System.out.println("File open failed");
			xc.printStackTrace();
			System.exit(0);
		}

		while (itemIn.hasNextLine())
		{
			line = itemIn.nextLine();
			frame.m.CreateItems(line);
		}



		//display the initial map
		frame.ul.loadFile(frame.m.getUL(frame.person.getNS(), frame.person.getEW()));
		frame.uc.loadFile(frame.m.getUC(frame.person.getNS(), frame.person.getEW()));
		frame.ur.loadFile(frame.m.getUR(frame.person.getNS(), frame.person.getEW()));
		frame.l.loadFile(frame.m.getL(frame.person.getNS(), frame.person.getEW()));
		frame.c.loadFile(frame.m.getC(frame.person.getNS(), frame.person.getEW()));
		frame.r.loadFile(frame.m.getR(frame.person.getNS(), frame.person.getEW()));
		frame.bl.loadFile(frame.m.getBL(frame.person.getNS(), frame.person.getEW()));
		frame.bc.loadFile(frame.m.getBC(frame.person.getNS(), frame.person.getEW()));
		frame.br.loadFile(frame.m.getBR(frame.person.getNS(), frame.person.getEW()));
		frame.RePaintAll();


		frame.txtArea.append("You are currently at (" +frame.person.getNS()+ ", " +frame.person.getEW()+ ") in terrain " +frame.m.getLocation(frame.person.getNS(), frame.person.getEW())+"\n");
		/* frame.m.Print(frame.person.getNS(), frame.person.getEW()); */
		frame.txtArea.append("Please enter a command: \n");
	}


	public void SendInput(String text)
	{
		String input = text;

		//change input to lowercase
		input = input.toLowerCase();


		//split input by spaces
		String[] parts = input.split(" ");

		if (parts[0].length() < 1)
		{
				txtArea.append("That is not an acceptable command.\n");
		}
		else
		{
			//get the first letter of the first word
			char firstLetter = parts[0].charAt(0);

			if (firstLetter == 'g')
			{
				//if there is nothing after go (ie: user writes "go" with no direction)
				if (parts.length < 2)
				{
					txtArea.append("Please input a direction.\n");
				}
				else
				{
					//get the first letter of the second word
					char secondLetter = parts[1].charAt(0);
					
					if (secondLetter == 'n')
					{
						if ((person.getNS()-1) >= 0)
						{
							person.GoNorth();
						}
						else 
						{
							txtArea.append("You can't go that direction.\n");
						}
					}
					else if (secondLetter == 's')
					{
						if ((person.getNS()+1) < m.getHeight())
						{
							person.GoSouth();
						}
						else 
						{
							txtArea.append("You can't go that direction.\n");
						}
					}
					else if (secondLetter == 'e')
					{
						if ((person.getEW()+1) < m.getWidth())
						{
							person.GoEast();
						}
						else 
						{
							txtArea.append("You can't go that direction.\n");
						}
					}
					else if (secondLetter == 'w')
					{
						if ((person.getEW()-1) >= 0)
						{
							person.GoWest();
						}
						else 
						{
							txtArea.append("You can't go that direction.\n");
						}
					}
					else
					{
							txtArea.append("Please enter a cardinal direction.\n");
					}
				}

				txtArea.append("You are currently at (" +person.getNS()+ ", " +person.getEW()+ ") in terrain " +m.getLocation(person.getNS(), person.getEW())+"\n");
				/* m.Print(person.getNS(), person.getEW()); */
				txtArea.append("Please enter a command: \n");


				String[] itms = m.getItems(person.getNS(), person.getEW());
				for (int i = 0; i < itms.length; i++)
				{
					if (!itms[i].equals("empty"))
					{
						txtArea.append("There is " + itms[i] + " here.\n");
					}
				}

				
				ul.loadFile(m.getUL(person.getNS(), person.getEW()));
				uc.loadFile(m.getUC(person.getNS(), person.getEW()));
				ur.loadFile(m.getUR(person.getNS(), person.getEW()));
				l.loadFile(m.getL(person.getNS(), person.getEW()));
				c.loadFile(m.getC(person.getNS(), person.getEW()));
				r.loadFile(m.getR(person.getNS(), person.getEW()));
				bl.loadFile(m.getBL(person.getNS(), person.getEW()));
				bc.loadFile(m.getBC(person.getNS(), person.getEW()));
				br.loadFile(m.getBR(person.getNS(), person.getEW()));
				RePaintAll();

			}
			else if (firstLetter == 't')
			{
				if (parts.length < 2)
				{
					txtArea.append("What would you like to take?\n");
				}
				else
				{
					String theItem = "";
					for (int i = 1; i < parts.length; i++)
					{
						if (i == 1)
						{
							theItem = parts[i];
						}
						else
						{
							/* String theItem = parts[1]+ " " +parts[2]; */
							theItem = theItem + " " + parts[i];
						}
					}

					if (m.compareItem(theItem, person.getNS(), person.getEW()))
					{
						m.takeItem(theItem, person.getNS(), person.getEW());
						person.addInv(theItem);
					}
				}

			}
			else if (firstLetter == 'd')
			{
				if (parts.length < 2)
				{
					txtArea.append("What would you like to drop?\n");
				}
				else
				{
					String theItem = "";
					for (int i = 1; i < parts.length; i++)
					{
						if (i == 1)
						{
							theItem = parts[i];
						}
						else
						{
							/* String theItem = parts[1]+ " " +parts[2]; */
							theItem = theItem + " " + parts[i];
						}
					}

					if (person.compareItem(theItem))
					{
						m.dropItem(theItem, person.getNS(), person.getEW());
						person.dropFromInv(theItem);
					}
				}
			}
			else if (firstLetter == 'i')
			{
				String[] inv = person.GetInv();
				for(String item : inv)
				{
					if (!item.equals("empty"))
					{
						txtArea.append(item+"\n");
					}
				}
			}
			else if (firstLetter == 'q')
			{
				/* break; */
			}
			else
			{
				txtArea.append("That is not an acceptable command.\n");
			}

		}
			

	}


	//Bitmap Class-----------
	public class Bitmap extends JPanel 
	{
		private BufferedImage image;
		private int w;
		private int h;

		public void setW(int width)
		{
			w = width;
		}

		public void setH(int height)
		{
			h = height;
		}

		public int getW()
		{
			return image.getWidth(this);
		}
		public int getH()
		{
			return image.getHeight(this);
		}

		public void loadFile(String fileName)
		{
			try 
			{
				image = ImageIO.read(new File(fileName));
			}
			catch (IOException e) 
			{
				System.out.println("File didn't load");
			}
		}

		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			if (image != null) 
			{
				g.drawImage(image, 0, 0, w, h, this);
				/* g.drawImage(image, 0, 0, null); */
			}
		}

	}//End Bitmap class

}//End Adventure class
