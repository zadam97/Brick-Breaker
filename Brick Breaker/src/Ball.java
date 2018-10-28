public class Ball extends Sprite 
{

	private static int BALL_SPEED = 1;
	private static int BALL_COUNT = 50;
	private final int XBOUND = 770;
	private final int YBOUND = 746;
	private boolean LR = true;
	private boolean UD = true;
	public int trajectory;
	private static boolean bip = false; 
	private static boolean biptrigger = false;
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////LEVELS//////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public static void easy()
	{
		BALL_SPEED = 1;
		BALL_COUNT = 50;
		biptrigger = true;
	}
	public static void medium()
	{
		BALL_SPEED = 3;
		BALL_COUNT = 15;
		biptrigger = false;

	}
	public static void hard()
	{
		BALL_SPEED = 5;
		BALL_COUNT = 5;
		biptrigger = false;

	}    
	public static void setspeed(int speed)
	{
		BALL_SPEED = speed;
	}

	public Ball(int x, int y) 
	{
		super(x, y);

		initBall();
	}

	private void initBall() 
	{
		if(BALL_COUNT > 0)
		{
			if(biptrigger == true || bip == false)
			{
				loadImage("Ball.png");
				getImageDimensions();

				BALL_COUNT--;
			}
		}
	}
	public static boolean BIPtrigger()//returns BIP trigger
	{
		return biptrigger;
	}
	public static boolean BIP()//Returns BIP
	{
		return bip;
	}
	public static void setBIP(boolean input)
	{
		bip = input;
	}
	public static int count()
	{
		return BALL_COUNT;
	}

	public static boolean gameplay()//Returns false if user can't play anymore
	{
		if((bip == false && BALL_COUNT <= 0) || Game.bricksleft() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	//////////////////////////////////////////////////////////////////////
	/////////////////////Collision Protocalls/////////////////////////////
	//////////////////////////////////////////////////////////////////////   

	public void move() 
	{
		if(trajectory == 1)
			one();
		else if(trajectory == 3)
			three();
		else     //When Trajectory is 2
			two();

	}

	public void bouncebrick()
	{
		if(UD == true)
			UD = false;
		else
			UD = true;
	}

	public void bounced(int bouncerdx)
	{
		UD = true;

		if((bouncerdx == -1 && LR == true) || (bouncerdx == 1 && LR == false))
		{
			if(LR == true)
				LR = false;
			else if(LR == false)
				LR = true;
		}

		else if((bouncerdx == -1 && LR == false) || (bouncerdx == 1 && LR == true))
		{
			changetrajectory(false);
		}

	}


	//////////////////////////////////////////////////////////////////////
	//////////////////////Trajectory Courses//////////////////////////////
	//////////////////////////////////////////////////////////////////////

	public void changetrajectory(boolean level)//if level is true(go up in angle) if false(go down in angle)
	{
		if(trajectory > 1 && level == false)
			trajectory -= 1;
		if(trajectory < 3 && level == true)
			trajectory += 1;
	}

	public void one()//30degrees
	{
		if(y <= 0)
			UD = false;

		if(y >= YBOUND)
		{
			vis = false;
		}

		if(UD == true)
			y -= BALL_SPEED;

		if(UD == false)
			y += BALL_SPEED;

		if(x <= 0)
			LR = false;

		if (x >= XBOUND)
			LR = true;

		if(LR == true)
			x -= (BALL_SPEED * 2);

		if(LR == false)
			x += (BALL_SPEED * 2);
	}

	public void two()//45degrees
	{
		if(y <= 0)
			UD = false;

		if(y >= YBOUND)
		{
			vis = false;
		}

		if(UD == true)
			y -= BALL_SPEED;

		if(UD == false)
			y += BALL_SPEED;

		if(x <= 0)
			LR = false;

		if (x >= XBOUND)
			LR = true;

		if(LR == true)
			x -= BALL_SPEED;

		if(LR == false)
			x += BALL_SPEED;
	}

	public void three()//60degrees
	{
		if(y <= 0)
			UD = false;

		if(y >= YBOUND)
		{
			vis = false;
		}

		if(UD == true)
			y -= (BALL_SPEED * 2);

		if(UD == false)
			y += (BALL_SPEED * 2);

		if(x <= 0)
			LR = false;

		if (x >= XBOUND)
			LR = true;

		if(LR == true)
			x -= BALL_SPEED;

		if(LR == false)
			x += BALL_SPEED;
	}

}