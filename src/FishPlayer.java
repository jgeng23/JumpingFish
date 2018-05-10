import java.awt.Rectangle;

public class FishPlayer extends Rectangle
{
	public static int STILL = 0;
	public static int R = 1;
	public static int D = 2;
	public static int L = 3;
	public static int U = 4;
	public static int UR = 5;
	public static int DR = 6;
	public static int UL = 7;
	public static int DL = 8;
	private int moveState;
	private Platform myPlat;
	private int myX;
	private int myY;
	
	public FishPlayer(int x, int y)
	{
		super(x, y, 30, 15);
		moveState = STILL;
		myPlat = null;
		myX = x;
		myY = y;
	}
	
	public int getMyX()
	{
		return myX;
	}
	
	public void setX(int x)
	{
		myX = x;
	}
	
	public int getMyY()
	{
		return myY;
	}
	
	public void setY(int y)
	{
		myY = y;
	}

	public int moveState()
	{
		return moveState;
	}
	
	public void setMoveState(int state)
	{
		moveState = state;
	}
	
	public void setPlatform(Platform p)
	{
		myPlat = p;
	}
	
	public void checkPlatform()
	{
		if(myX + 30 < myPlat.getX() || myX >= myPlat.getMaxX())
			myPlat = null;		
	}
	
	public Platform getPlatform()
	{
		return myPlat;
	}
	
}
