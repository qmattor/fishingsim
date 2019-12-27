import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class QMpanel extends JPanel implements ActionListener, KeyListener
{
	private Timer t;
	private BufferedImage bigFish, smallFish, cast, start, brewskis;
	private int fishies;
	private boolean help, spcae, exit, exit2;
	private int state, count = 0;
	private Random rand;
	QMpanel ()
	{
		super();
		t = new Timer(500, this); // holds state for 5 seconds
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		System.out.println("test 1");
		rand = new Random();
		try
		{
			//for unix
			bigFish = ImageIO.read(new File("elements/bigfish.png"));
			smallFish = ImageIO.read(new File("elements/smallfish.png"));
			cast = ImageIO.read(new File("elements/cast.png"));
			start = ImageIO.read(new File("elements/start.png"));
			brewskis = ImageIO.read(new File("elements/brewskis.png"));
		}
		catch (IOException e)
		{
			try
			{
				//for windows
				bigFish = ImageIO.read(new File("elements\\bigfish.png"));
				smallFish = ImageIO.read(new File("elements\\smallfish.png"));
				cast = ImageIO.read(new File("elements\\cast.png"));
				start = ImageIO.read(new File("elements\\start.png"));
				brewskis = ImageIO.read(new File("elements\\brewskis.png"));
			}
			catch (IOException d)
			{
				JOptionPane.showMessageDialog(null, "ERROR 1, FILES NOT FOUND", "404", JOptionPane.ERROR_MESSAGE);
			}

		}
		System.out.println("test 2\n");
		help = false;
		spcae = false;
		exit = false;
		exit2 = false;
		state = 0;
		t.start();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (spcae && state == 0)
			state++;
		int temp;
		if (state == 0)
			g.drawImage(start, 0, 0, null);
		else if (state == 1)
		{
			g.drawImage(cast, 0, 0, null);
			temp = rand.nextInt() % 200;
			if (temp > 150)			//big catch;
			{
				state = 3;
				fishies++;
			}
			else if (temp > 100)	//little catch
			{
				state = 2;
				fishies++;
			}
			else
				state = 0;
		}
		else if (state == 2)
		{
			g.drawImage(smallFish, 0,0, null);
			state = 0;
		}
		else if (state == 3)
		{
			g.drawImage(bigFish, 0,0, null);
			state = 0;
		}
		if (exit)
		{
			exit2 = true;
			exit = false;
			g.drawImage(this.brewskis, 0, 0, null);

		}
		if (exit2)
		{
			System.out.println("what");
			g.drawImage(this.brewskis, 0,0, null);
			count++;
			if (count == 7)
			{
				JOptionPane.showMessageDialog(null, "you caught " + getFishies() + " fish", "GG m8", 0);
				System.exit(0);
			}
		}

	}
	public int getFishies()
	{
		return fishies;
	}
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	public void keyPressed(KeyEvent arg0)
	{
		System.out.println("key pressed");
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
			{
				spcae = true;
				break ;
			}
			case KeyEvent.VK_ESCAPE:
			{
				exit = true;
				break ;
			}
			case KeyEvent.VK_H:
			{
				help = true;
				break ;
			}
		}
	}
	public void keyReleased(KeyEvent arg0)
	{
		switch (arg0.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
			{
				spcae = false;
				break ;
			}
			case KeyEvent.VK_ESCAPE:
			{
				exit = false;
				break ;
			}
			case KeyEvent.VK_H:
			{
				help = false;
				break ;
			}
		}
	}
	public void keyTyped(KeyEvent arg0)
	{}
}
