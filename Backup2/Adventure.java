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
	private Bitmap top1;
	private Bitmap top2;
	private Bitmap top3;
	private Bitmap top4;
	private Bitmap top5;

	private Bitmap sec1;
	private Bitmap sec2;
	private Bitmap sec3;
	private Bitmap sec4;
	private Bitmap sec5;

	private Bitmap mid1;
	private Bitmap mid2;
	private Bitmap mid3;
	private Bitmap mid4;
	private Bitmap mid5;

	private Bitmap four1;
	private Bitmap four2;
	private Bitmap four3;
	private Bitmap four4;
	private Bitmap four5;

	private Bitmap bot1;
	private Bitmap bot2;
	private Bitmap bot3;
	private Bitmap bot4;
	private Bitmap bot5;

	private SaveOut o;

	/* private Bitmap ul; */
	/* private Bitmap uc; */
	/* private Bitmap ur; */
	/* private Bitmap l; */
	/* private Bitmap c; */
	/* private Bitmap r; */
	/* private Bitmap bl; */
	/* private Bitmap bc; */
	/* private Bitmap br; */

	//constructor----------------------------------
    public Adventure() 
	{
		//Create objects
		o = new SaveOut();
		o.person = new GameChar();
		o.m = new Map();

		//create the Bitmap objects (one for each space
		top1 = new Bitmap();
		top2 = new Bitmap();
		top3 = new Bitmap();
		top4 = new Bitmap();
		top5 = new Bitmap();

		sec1 = new Bitmap();
		sec2 = new Bitmap();
		sec3 = new Bitmap();
		sec4 = new Bitmap();
		sec5 = new Bitmap();

		mid1 = new Bitmap();
		mid2 = new Bitmap();
		mid3 = new Bitmap();
		mid4 = new Bitmap();
		mid5 = new Bitmap();

		four1 = new Bitmap();
		four2 = new Bitmap();
		four3 = new Bitmap();
		four4 = new Bitmap();
		four5 = new Bitmap();

		bot1 = new Bitmap();
		bot2 = new Bitmap();
		bot3 = new Bitmap();
		bot4 = new Bitmap();
		bot5 = new Bitmap();

		/* ul = new Bitmap(); */
		/* l = new Bitmap(); */
		/* bl = new Bitmap(); */
		/* uc = new Bitmap(); */
		/* c = new Bitmap(); */
		/* bc = new Bitmap(); */
		/* ur = new Bitmap(); */
		/* r = new Bitmap(); */
		/* br = new Bitmap(); */

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
		JPanel cp = new JPanel(new GridLayout(5,5));

		cp.add(top1);
		cp.add(top2);
		cp.add(top3);
		cp.add(top4);
		cp.add(top5);

		cp.add(sec1);
		cp.add(sec2);
		cp.add(sec3);
		cp.add(sec4);
		cp.add(sec5);

		cp.add(mid1);
		cp.add(mid2);
		cp.add(mid3);
		cp.add(mid4);
		cp.add(mid5);

		cp.add(four1);
		cp.add(four2);
		cp.add(four3);
		cp.add(four4);
		cp.add(four5);

		cp.add(bot1);
		cp.add(bot2);
		cp.add(bot3);
		cp.add(bot4);
		cp.add(bot5);

/* 		cp.add(ul); */
/* 		cp.add(uc); */
/* 		cp.add(ur); */
/* 		cp.add(l); */
/* 		cp.add(c); */
/* 		cp.add(r); */
/* 		cp.add(bl); */
/* 		cp.add(bc); */
/* 		cp.add(br); */

		RePaintAll();
		add(cp, BorderLayout.CENTER);

	}

	public void RePaintAll()
	{
		top1.repaint();
		top2.repaint();
		top3.repaint();
		top4.repaint();
		top5.repaint();

		sec1.repaint();
		sec2.repaint();
		sec3.repaint();
		sec4.repaint();
		sec5.repaint();

		mid1.repaint();
		mid2.repaint();
		mid3.repaint();
		mid4.repaint();
		mid5.repaint(); 

		four1.repaint();
		four2.repaint();
		four3.repaint();
		four4.repaint();
		four5.repaint();

		bot1.repaint();
		bot2.repaint();
		bot3.repaint();
		bot4.repaint();
		bot5.repaint();

		/* ul.repaint(); */
		/* uc.repaint(); */
		/* ur.repaint(); */
		/* l.repaint(); */
		/* c.repaint(); */
		/* r.repaint(); */
		/* bl.repaint(); */
		/* bc.repaint(); */
		/* br.repaint(); */
	}

    public void actionPerformed(ActionEvent e)
	{
		String text = txtField.getText();
		/* input = text; */
		txtArea.append(text + "\n");
		txtField.setText("");
		SendInput(text);

		if (e.getSource()==saveButton)
		{
			try
			{
				FileOutputStream fileOut = new FileOutputStream("test.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(person);
				out.close();
				fileOut.close();
				System.out.printf("Serialized data is saved in test.ser");
			}
			catch(IOException i)
			{
				i.printStackTrace();
			}
			/* final JFileChooser fc = new JFileChooser(); */
			/* int returnVal = fc.showOpenDialog(this); */

			/* if (returnVal == JFileChooser.APPROVE_OPTION) */
			/* { */
			/* 	File file = fc.getSelectedFile(); */
			/* }  */
			/* else */
			/* { */
			/* 	System.out.println("Save cancelled"); */
			/* } */
		}

		if (e.getSource()==quitButton)
		{
			System.exit(0);
		}
	}



	public void setImageHeight(int imgW, int imgH)
	{
		top1.setW(imgW); top1.setH(imgH);
		top2.setW(imgW); top2.setH(imgH);
		top3.setW(imgW); top3.setH(imgH);
		top4.setW(imgW); top4.setH(imgH);
		top5.setW(imgW); top5.setH(imgH);

		sec1.setW(imgW); sec1.setH(imgH);
		sec2.setW(imgW); sec2.setH(imgH);
		sec3.setW(imgW); sec3.setH(imgH);
		sec4.setW(imgW); sec4.setH(imgH);
		sec5.setW(imgW); sec5.setH(imgH);

		mid1.setW(imgW); mid1.setH(imgH);
		mid2.setW(imgW); mid2.setH(imgH);
		mid3.setW(imgW); mid3.setH(imgH);
		mid4.setW(imgW); mid4.setH(imgH);
		mid5.setW(imgW); mid5.setH(imgH);

		four1.setW(imgW); four1.setH(imgH);
		four2.setW(imgW); four2.setH(imgH);
		four3.setW(imgW); four3.setH(imgH);
		four4.setW(imgW); four4.setH(imgH);
		four5.setW(imgW); four5.setH(imgH);

		bot1.setW(imgW); bot1.setH(imgH);
		bot2.setW(imgW); bot2.setH(imgH);
		bot3.setW(imgW); bot3.setH(imgH);
		bot4.setW(imgW); bot4.setH(imgH);
		bot5.setW(imgW); bot5.setH(imgH);
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
				frame.setImageHeight(imageW, imageH);
				/* frame.ul.setW(imageW); frame.ul.setH(imageH); */
				/* frame.uc.setW(imageW); frame.uc.setH(imageH); */
				/* frame.ur.setW(imageW); frame.ur.setH(imageH); */
				/* frame.l.setW(imageW); frame.l.setH(imageH); */
				/* frame.c.setW(imageW); frame.c.setH(imageH); */
				/* frame.r.setW(imageW); frame.r.setH(imageH); */
				/* frame.bl.setW(imageW); frame.bl.setH(imageH); */
				/* frame.bc.setW(imageW); frame.bc.setH(imageH); */
				/* frame.br.setW(imageW); frame.br.setH(imageH); */
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
		/* frame.ul.loadFile(frame.m.getUL(frame.person.getNS(), frame.person.getEW())); */
		/* frame.uc.loadFile(frame.m.getUC(frame.person.getNS(), frame.person.getEW())); */
		/* frame.ur.loadFile(frame.m.getUR(frame.person.getNS(), frame.person.getEW())); */
		/* frame.l.loadFile(frame.m.getL(frame.person.getNS(), frame.person.getEW())); */
		/* frame.c.loadFile(frame.m.getC(frame.person.getNS(), frame.person.getEW())); */
		/* frame.r.loadFile(frame.m.getR(frame.person.getNS(), frame.person.getEW())); */
		/* frame.bl.loadFile(frame.m.getBL(frame.person.getNS(), frame.person.getEW())); */
		/* frame.bc.loadFile(frame.m.getBC(frame.person.getNS(), frame.person.getEW())); */
		/* frame.br.loadFile(frame.m.getBR(frame.person.getNS(), frame.person.getEW())); */

		frame.top1.loadFile(frame.m.getTop1(frame.person.getNS(), frame.person.getEW()));
		frame.top2.loadFile(frame.m.getTop2(frame.person.getNS(), frame.person.getEW()));
		frame.top3.loadFile(frame.m.getTop3(frame.person.getNS(), frame.person.getEW()));
		frame.top4.loadFile(frame.m.getTop4(frame.person.getNS(), frame.person.getEW()));
		frame.top5.loadFile(frame.m.getTop5(frame.person.getNS(), frame.person.getEW()));
		
		frame.sec1.loadFile(frame.m.getSec1(frame.person.getNS(), frame.person.getEW()));
		frame.sec2.loadFile(frame.m.getSec2(frame.person.getNS(), frame.person.getEW()));
		frame.sec3.loadFile(frame.m.getSec3(frame.person.getNS(), frame.person.getEW()));
		frame.sec4.loadFile(frame.m.getSec4(frame.person.getNS(), frame.person.getEW()));
		frame.sec5.loadFile(frame.m.getSec5(frame.person.getNS(), frame.person.getEW()));

		frame.mid1.loadFile(frame.m.getMid1(frame.person.getNS(), frame.person.getEW()));
		frame.mid2.loadFile(frame.m.getMid2(frame.person.getNS(), frame.person.getEW()));
		frame.mid3.loadFile(frame.m.getMid3(frame.person.getNS(), frame.person.getEW()));
		frame.mid4.loadFile(frame.m.getMid4(frame.person.getNS(), frame.person.getEW()));
		frame.mid5.loadFile(frame.m.getMid5(frame.person.getNS(), frame.person.getEW()));

		frame.four1.loadFile(frame.m.getFour1(frame.person.getNS(), frame.person.getEW()));
		frame.four2.loadFile(frame.m.getFour2(frame.person.getNS(), frame.person.getEW()));
		frame.four3.loadFile(frame.m.getFour3(frame.person.getNS(), frame.person.getEW()));
		frame.four4.loadFile(frame.m.getFour4(frame.person.getNS(), frame.person.getEW()));
		frame.four5.loadFile(frame.m.getFour5(frame.person.getNS(), frame.person.getEW()));

		frame.bot1.loadFile(frame.m.getBot1(frame.person.getNS(), frame.person.getEW()));
		frame.bot2.loadFile(frame.m.getBot2(frame.person.getNS(), frame.person.getEW()));
		frame.bot3.loadFile(frame.m.getBot3(frame.person.getNS(), frame.person.getEW()));
		frame.bot4.loadFile(frame.m.getBot4(frame.person.getNS(), frame.person.getEW()));
		frame.bot5.loadFile(frame.m.getBot5(frame.person.getNS(), frame.person.getEW()));

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

				
				/* ul.loadFile(m.getUL(person.getNS(), person.getEW())); */
				/* uc.loadFile(m.getUC(person.getNS(), person.getEW())); */
				/* ur.loadFile(m.getUR(person.getNS(), person.getEW())); */
				/* l.loadFile(m.getL(person.getNS(), person.getEW())); */
				/* c.loadFile(m.getC(person.getNS(), person.getEW())); */
				/* r.loadFile(m.getR(person.getNS(), person.getEW())); */
				/* bl.loadFile(m.getBL(person.getNS(), person.getEW())); */
				/* bc.loadFile(m.getBC(person.getNS(), person.getEW())); */
				/* br.loadFile(m.getBR(person.getNS(), person.getEW())); */

				top1.loadFile(m.getTop1(person.getNS(), person.getEW()));
				top2.loadFile(m.getTop2(person.getNS(), person.getEW()));
				top3.loadFile(m.getTop3(person.getNS(), person.getEW()));
				top4.loadFile(m.getTop4(person.getNS(), person.getEW()));
				top5.loadFile(m.getTop5(person.getNS(), person.getEW()));
				
				sec1.loadFile(m.getSec1(person.getNS(), person.getEW()));
				sec2.loadFile(m.getSec2(person.getNS(), person.getEW()));
				sec3.loadFile(m.getSec3(person.getNS(), person.getEW()));
				sec4.loadFile(m.getSec4(person.getNS(), person.getEW()));
				sec5.loadFile(m.getSec5(person.getNS(), person.getEW()));

				mid1.loadFile(m.getMid1(person.getNS(), person.getEW()));
				mid2.loadFile(m.getMid2(person.getNS(), person.getEW()));
				mid3.loadFile(m.getMid3(person.getNS(), person.getEW()));
				mid4.loadFile(m.getMid4(person.getNS(), person.getEW()));
				mid5.loadFile(m.getMid5(person.getNS(), person.getEW()));

				four1.loadFile(m.getFour1(person.getNS(), person.getEW()));
				four2.loadFile(m.getFour2(person.getNS(), person.getEW()));
				four3.loadFile(m.getFour3(person.getNS(), person.getEW()));
				four4.loadFile(m.getFour4(person.getNS(), person.getEW()));
				four5.loadFile(m.getFour5(person.getNS(), person.getEW()));

				bot1.loadFile(m.getBot1(person.getNS(), person.getEW()));
				bot2.loadFile(m.getBot2(person.getNS(), person.getEW()));
				bot3.loadFile(m.getBot3(person.getNS(), person.getEW()));
				bot4.loadFile(m.getBot4(person.getNS(), person.getEW()));
				bot5.loadFile(m.getBot5(person.getNS(), person.getEW()));

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

	public class SaveOut
	{
		private GameChar person;
		private Map m;
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
