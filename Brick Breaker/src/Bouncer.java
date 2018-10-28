import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Bouncer extends Sprite 
{

	private int dx;
	private int dy;
	private static int BOUNCER_SPEED = 5;
	private static int BOUNCER_SIZE = 1;
	private ArrayList<Ball> balls;

	//////////////////////////////////////////////////////////////////////
	//////////////////////////////LEVELS//////////////////////////////////
	//////////////////////////////////////////////////////////////////////4

	public static void easy()
	{
		BOUNCER_SPEED = 3;
		BOUNCER_SIZE = 1;
	}
	public static void medium()
	{
		BOUNCER_SPEED = 6;
		BOUNCER_SIZE = 0;
	}
	public static void hard()
	{
		BOUNCER_SPEED = 8;
		BOUNCER_SIZE = 0;
	}

	public static int getbouncersize()
	{
		return BOUNCER_SIZE;
	}

	public Bouncer(int x, int y) 
	{
		super(x, y);

		initBouncer();
	}

	private void initBouncer() 
	{
		balls = new ArrayList<>();

		if(BOUNCER_SIZE == 1)
		{
			loadImage("Big.png");
		}
		else if (BOUNCER_SIZE == 0)
		{
			loadImage("Bouncer.png");
		}
		getImageDimensions();
	}

	public void move() 
	{

		x += (dx * BOUNCER_SPEED);
		y += (dy * BOUNCER_SPEED);

		if (x < 1) 
		{
			x = 1;
		}

		if (y < 1) 
		{
			y = 1;
		}
		if (x > 725) //X Bound
		{
			x = 725;
		}
	}

	public ArrayList getBalls() 
	{
		return balls;
	}

	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) 
		{
			if(!Ball.BIP() || Ball.BIPtrigger())
				fire();
		}

		if (key == KeyEvent.VK_LEFT) 
		{
			dx = -1;
		}

		if (key == KeyEvent.VK_RIGHT) 
		{
			dx = 1;
		}

		//////////////////////Speed changers//////////////////////////

		if (key == KeyEvent.VK_1) 
		{
			Ball.setspeed(1);
			BOUNCER_SPEED = 3;
		}

		if (key == KeyEvent.VK_2) 
		{
			Ball.setspeed(3);
			BOUNCER_SPEED = 6;
		}

		if (key == KeyEvent.VK_3) 
		{
			Ball.setspeed(5);
			BOUNCER_SPEED = 8;
		}

		if (key == KeyEvent.VK_4)
		{
			Ball.setspeed(7);
			BOUNCER_SPEED = 10;
		}

		if (key == KeyEvent.VK_5)
		{
			Ball.setspeed(9);
			BOUNCER_SPEED = 12;
		}
	}

	public void fire() 
	{
		if(getbouncersize() == 1)
		{
			balls.add(new Ball((x + width - 30), y + height / 2));
		}
		else
		{
			balls.add(new Ball((x + width - 17), y + height / 2));	
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public int getdx()
	{
		if(dx == -1)
			return -1;
		else if (dx == 1)
			return 1;
		else
			return 0;
	}
}