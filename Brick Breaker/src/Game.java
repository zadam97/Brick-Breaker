import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Scanner;

public class Game extends JPanel implements ActionListener 
{

	private Timer timer;
	private int counttime = 0;
	private int layout = 1;
	private int level = 1;
	private boolean firstpass = true;
	private static int ogbricks = 0;
	private Bouncer bouncer;
	private static ArrayList<Brick> bricks;
	private boolean ingame;

	private final int iBOUNCER_X = 370;
	private final int iBOUNCER_Y = 695;

	private final int G_WIDTH = 775;
	private final int G_HEIGHT = 750;

	private final int DELAY = 15;



	public Game() 
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("  Welcome To Brick Breaker");
		System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		menu();
	}
	
	public void variablereset()
	{
		counttime = 0;
		layout = 1;
		level = 1;
		firstpass = true;
		ogbricks = 0;	
	}

	public void menu()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("  1 ------------------ Easy");
		System.out.println("  2 ---------------- Medium");
		System.out.println("  3 ------------------ Hard");
		System.out.print("\n  Please Select a Level:  ");
		level = scan.nextInt();
		System.out.print("\n");
		//////////////////////////////////////////////////
		System.out.println("  1 ------------ Brick Wall");
		System.out.println("  2 --------------- Lattice");
		System.out.println("  3 -------------- Fortress");
		System.out.println("  4 --------------- Diamond");
		System.out.println("  5 -------- Hollow Diamond");
		System.out.print("\n  Please Select a Layout: ");
		layout = scan.nextInt();
		System.out.print("\n\n");

		setlayout(layout);

		initGame(level);
		
		
	}

	private void easy()
	{
		Ball.easy();
		Bouncer.easy();
	}

	private void medium()
	{
		Ball.medium();
		Bouncer.medium();
	}

	private void hard()
	{
		Ball.hard();
		Bouncer.hard();
	}

	private void initGame(int level) 
	{
		ogbricks = 0;
		counttime = 0;

		if(level == 1)
			this.easy();
		else if(level == 2)
			this.medium();
		else
			this.hard();


		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		ingame = true;

		setPreferredSize(new Dimension(G_WIDTH, G_HEIGHT));

		bouncer = new Bouncer(iBOUNCER_X, iBOUNCER_Y);

		initBricks();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void setlayout(int x)
	{
		layout = x;
	}

	public void initBricks()
	{
		bricks = new ArrayList<>();

		switch(layout)
		{
		case 1: layout1();
		break;
		case 2: layout2();
		break;
		case 3: layout3();
		break;
		case 4: layout4();
		break;
		case 5: layout5();
		break;
		default:
			layout1();
			break;
		}
	}

	public void layout1()
	{
		int x, y;


		for(x = 0; x < (G_WIDTH); x += 25)
		{
			for(y = 0; y < (G_HEIGHT / 2); y += 12)
			{
				bricks.add(new Brick(x,y));

			}	
		}
	}

	public void layout2()
	{
		int x, y;


		for(x = 0; x < (G_WIDTH); x += 25)
		{
			for(y = 0; y < ((G_HEIGHT / 2) - 100); y += 12)
			{
				if(x%2 == 1 || y%8 == 0)
				{
					bricks.add(new Brick(x,y));
				}
			}
		}
	}

	public void layout3()
	{
		int x, y;


		for(x = 0; x < (G_WIDTH / 2); x += 25)
		{
			for(y = 0; y < (G_HEIGHT / 4); y += 12)
			{
				if((x * 2+ y >= 750) || (x > 200 && x < 250) || (y > 155 && y < 165))
				{
					bricks.add(new Brick(x,y));                 //1st Quadrant
					bricks.add(new Brick((750 - x), y));        //2nd Quadrant
					bricks.add(new Brick(x,(372 - y)));         //3rd Quadrant
					bricks.add(new Brick((750 - x),(372 - y))); //4th Quadrant

				}

			}
		}
	}

	public void layout4()
	{
		int x, y;


		for(x = 0; x < (G_WIDTH / 2); x += 25)
		{
			for(y = 0; y < (G_HEIGHT / 4); y += 12)
			{
				if(x + y * 2 >= 550)
				{
					bricks.add(new Brick(x,y));                 //1st Quadrant
					bricks.add(new Brick((750 - x), y));        //2nd Quadrant
					bricks.add(new Brick(x,(372 - y)));         //3rd Quadrant
					bricks.add(new Brick((750 - x),(372 - y))); //4th Quadrant

				}

			}
		}
	}

	public void layout5()
	{
		int x, y;


		for(x = 0; x < (G_WIDTH / 2); x += 25)
		{
			for(y = 0; y < (G_HEIGHT / 4); y += 12)
			{
				if((x + y * 2 >= 450) && (x + y * 2 <= 575))
				{
					bricks.add(new Brick(x,y));                 //1st Quadrant
					bricks.add(new Brick((750 - x), y));        //2nd Quadrant
					bricks.add(new Brick(x,(372 - y)));         //3rd Quadrant
					bricks.add(new Brick((750 - x),(372 - y))); //4th Quadrant

				}

			}
		}

	}	

	public static void brickmade()
	{
		ogbricks++;
	}

	public static int bricksleft()
	{
		return bricks.size();
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		if (ingame) 
		{
			drawObjects(g);

		}
		else 
		{
			drawGameOver(g);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	private void drawObjects(Graphics g) 
	{   	
		if (bouncer.isVisible()) 
		{
			g.drawImage(bouncer.getImage(), bouncer.getX(), bouncer.getY(),this);
		}

		ArrayList<Ball> balls = bouncer.getBalls();

		for (Ball b : balls) 
		{
			if (b.isVisible()) 
			{
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}

		for (Brick br : bricks) 
		{
			if (br.isVisible()) 
			{
				g.drawImage(br.getImage(), br.getX(), br.getY(), this);
			}
		}

		g.setColor(Color.WHITE);
		g.drawString("Balls left: " + Ball.count() + "   Bricks Left: " + Game.bricksleft() + "   Time: " + (counttime/100), 5, 15);
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, G_WIDTH, G_HEIGHT);
	}

	private void drawGameOver(Graphics g)
	{	
		String won = "You Won";
		String lost = "You Lost";
		String score = "Score: ";
		Font small = new Font("Helvetica", Font.BOLD, 16);
		FontMetrics fm = getFontMetrics(small);

		if(bricks.size() == 0)
		{
			setBackground(Color.MAGENTA);
			g.setColor(Color.BLUE);
			g.setFont(small);
			g.drawString(won, (G_WIDTH - fm.stringWidth(won)) / 2, G_HEIGHT / 2);
			g.drawString(score + scorecalc(), (345), (G_HEIGHT / 2) + 25);
		}
		else
		{
			g.setColor(Color.green);
			g.setFont(small);
			g.drawString(lost, (G_WIDTH - fm.stringWidth(lost)) / 2, (G_HEIGHT / 2) - 25);
			if(bricks.size() == 1)
			{
				g.drawString("1 Brick Left", (343), G_HEIGHT / 2);
				g.drawString(score + scorecalc(), (345), (G_HEIGHT / 2) + 25);
			}
			else
			{
				g.drawString(bricks.size() + " Bricks Left", (340), G_HEIGHT / 2);
				g.drawString(score + scorecalc(), (345), (G_HEIGHT / 2) + 25);
			}
		}		

			if(!firstpass)
			{
				pause();
				
			System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("  | Thank You For Playing |");
			System.out.println("  |      Brick Breaker    |");
			System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			System.exit(0);
			}
			
			else
				firstpass = false;
	}
	
	
	public void pause()
	{
		try {
			Thread.sleep(7500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int scorecalc()
	{
		return (((ogbricks - bricks.size()) * 10) + (Ball.count() * 5) + (100 - counttime));
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		inGame();
		updateBalls(); 
		updateBouncer();
		updateBricks();
		checkCollisions();
		repaint();

		if(!Ball.gameplay())
		{
			ingame = false;
		}

	}

	private void inGame() 
	{
		if (!ingame) 
		{
			timer.stop();
		}
	}

	private void updateBouncer() 
	{
		bouncer.move();
	}

	private void updateBalls() 
	{
		
		if(Ball.BIP())
			counttime = counttime + 1;

		ArrayList<Ball> balls = bouncer.getBalls();    
		int counter = 0;
		for (int i = 0; i < balls.size(); i++) 
		{

			Ball b = balls.get(i);

			if (b.isVisible()) 
			{
				Ball.setBIP(true);
				b.move();
				counter ++;
			}
			else 
			{
				balls.remove(i);
			}
			if(counter == 0)
			{
				Ball.setBIP(false);
			}
		}
	}

	private void updateBricks() 
	{
		if (bricks.isEmpty()) 
		{
			ingame = false;
			return;
		}

		for (int i = 0; i < bricks.size(); i++) 
		{
			Brick b = bricks.get(i);
			if (!b.isVisible()) 
			{
				bricks.remove(i);
			}
		}
	}

	public void checkCollisions() 
	{
		Rectangle r3 = bouncer.getBounds();

		ArrayList<Ball> balls = bouncer.getBalls();

		for (Ball b : balls) 
		{
			Rectangle r1 = b.getBounds();

			for (Brick brick : bricks) 
			{
				Rectangle r2 = brick.getBounds();

				if (r1.intersects(r2)) 
				{
					brick.setVisible(false);
					b.bouncebrick();
				}
				if(r3.intersects(r1))
				{
					b.bounced(bouncer.getdx());  
				}
			}
		}
	}

	private class TAdapter extends KeyAdapter 
	{

		@Override
		public void keyReleased(KeyEvent e) 
		{
			bouncer.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			bouncer.keyPressed(e);
		}
	}
}