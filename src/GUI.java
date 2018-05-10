import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener, KeyListener
{
	private Image fish;
	private Image bg;
	private FishPlayer player;
	private Timer jumpTimer = new Timer(1000,this);
	private Timer timer = new Timer(5, this);
	private int jumpCounter;
	private ArrayList<Platform> platforms;
	private Rectangle rect;
	private Rectangle rect2;
	private Rectangle rect3;
	private int GuiWidth;
	private int GuiHeight;
	
	public GUI()
	{
		jumpCounter = 0;
		
		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//to load images
		ClassLoader cldr = getClass().getClassLoader();
		
		//player icon
		ImageIcon fishIcon = new ImageIcon(cldr.getResource("fish.gif"));
		fish = fishIcon.getImage();
		
		//background image
		ImageIcon bgIcon = new ImageIcon(cldr.getResource("blue_sky.png"));
		bg = bgIcon.getImage();
		
		//instantiate player character
		player = new FishPlayer(100, 185);
		
		addKeyListener(this);
		
		//set size
		GuiWidth = 800;
		GuiHeight = 800;
		
		//make visible
		setSize(GuiWidth,GuiHeight);
		setVisible(true);
		
		platforms = new ArrayList<Platform>();
		
		platforms.add(new Platform(100, 200, 100, 50));
		platforms.add(new Platform(300, 350, 100, 50));
		platforms.add(new Platform(200, 400, 100, 50));
		platforms.add(new Platform(400, 500, 100, 50));
		platforms.add(new Platform(500, 400, 100, 50));
		
		player.setPlatform(platforms.get(0));
		
		timer.start();

	}
	
	public void paint(Graphics g)
	{
		Image offImage = createImage(800, 800);
	// Creates an off-screen drawable image to be used for
	// double buffering; XSIZE, YSIZE are each of type ‘int’;
	// represents size of JFrame or JPanel, etc
		Graphics buffer = offImage.getGraphics();
	// Creates a graphics context for drawing to an 
	// off-screen image
		paintOffScreen(buffer);		// your own method
		g.drawImage(offImage, 0, 0, null);	
	// draws the image with upper left corner at 0,0
	}

	public void paintOffScreen(Graphics g)
	{
		// sometimes helpful to do this first to clear things:
		//g.clearRect(0, 0, 800, 800);
		
		//draw background
		g.drawImage(bg, 0, 0, null);
		
		//draw platform
		g.setColor(Color.WHITE);
		g.fillRect(100, 200, 100, 50);
		g.fillRect(200, 400, 100, 50);
		g.fillRect(300, 350, 100, 50);
		g.fillRect(400, 500, 100, 50);
		g.fillRect(500, 400, 100, 50);
		
		//draw player
		g.drawImage(fish, player.getMyX(), player.getMyY(), null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(player.moveState() == FishPlayer.D || player.moveState() == FishPlayer.DL)
			{
				player.setMoveState(FishPlayer.DR);
			}
				
			else if(player.moveState() == FishPlayer.U)
				player.setMoveState(FishPlayer.UR);
			else
			{
				player.setMoveState(FishPlayer.R);
			}
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(player.moveState() == FishPlayer.D || player.moveState() == FishPlayer.DR)
				player.setMoveState(FishPlayer.DL);
			else if(player.moveState() == FishPlayer.U)
				player.setMoveState(FishPlayer.UL);
			else
				player.setMoveState(FishPlayer.L);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_UP && player.getPlatform() != null)
		{
			if(player.moveState() == FishPlayer.R)
				player.setMoveState(FishPlayer.UR);
			else if(player.moveState() == FishPlayer.L)
				player.setMoveState(FishPlayer.UL);
			else
				player.setMoveState(FishPlayer.U);
			player.setPlatform(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

		if(e.getKeyCode() == KeyEvent.VK_RIGHT)	
		{
			if(player.moveState() == FishPlayer.UR)
				player.setMoveState(FishPlayer.U);
			else if(player.moveState() == FishPlayer.DR)
				player.setMoveState(FishPlayer.D);
			else
				player.setMoveState(FishPlayer.STILL);
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)	
		{
			if(player.moveState() == FishPlayer.UL)
				player.setMoveState(FishPlayer.U);
			else if(player.moveState() == FishPlayer.DL)
				player.setMoveState(FishPlayer.D);
			else
				player.setMoveState(FishPlayer.STILL);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void checkCollision(Platform p)
	{
		if(player.getPlatform() != null)
			player.checkPlatform();
		
		if(player.moveState() == FishPlayer.R)
		{
			if(player.getPlatform() == null)
			{
				player.setMoveState(FishPlayer.DR);
			}
			//if player hits right edge of another platform or edge of GUI
			else if(player.getMyX() + 30 >= p.getX() && player.getMyX() + 30 <= p.getX() + 1 && (player.getMyY() + 15 <= p.getY() + p.getHeight() + 2 && player.getMyY() >= p.getY())
					|| player.getMyX() >= GuiWidth)
				if(player.moveState() != FishPlayer.U && player.moveState() != FishPlayer.UR && player.moveState() != FishPlayer.UL)
					player.setMoveState(FishPlayer.STILL);
		}
		
		else if(player.moveState() == FishPlayer.L)
		{
			if(player.getPlatform() == null)
				player.setMoveState(FishPlayer.DL);
			else if(player.getMyX() + 30 <= p.getX() && player.getMyY() + 15 >= p.getY() - 1 && player.getMyY() <= p.getY() + p.getHeight()
					&& player.getMyX() <= 0)
				player.setMoveState(FishPlayer.STILL);
		} 
		
		else if(player.moveState() == FishPlayer.U || player.moveState() == FishPlayer.UR || player.moveState() == FishPlayer.UL)
		{
			//if player hits the bottom of another platform
			if(player.getMyY() <= p.getMaxY() && player.getMyY() >= p.getY() && player.getMyX() >= p.getX() && player.getMyX() + 30 <= p.getMaxX())
			{
				player.setMoveState(FishPlayer.D);
				jumpCounter = 0;
			}
			else if(player.getMyX() == p.getX() - 1 && player.getMyY() + 15 >= p.getY() && player.getMyY() <= p.getY() + p.getHeight())
			{
				player.setMoveState(FishPlayer.D);
				jumpCounter = 0;
			}	
			
			//if player hits the right edge of a platform
			else if(player.getMyX() + 30 == p.getX() && player.getMyY() + 15 >= p.getY() && player.getMyY() <= p.getY() + p.getHeight())
			{
				player.setMoveState(FishPlayer.D);
				jumpCounter = 0;
			}
		}
		
		else if(player.moveState() == FishPlayer.D || player.moveState() == FishPlayer.DR || player.moveState() == FishPlayer.DL)
		{
			//if player lands on a platform
			if(player.getMyY() + 15 >= p.getY() && player.getMyY() + 15 <= p.getY() + 1 && player.getMyX() + 30 > p.getX() && player.getMyX() < p.getMaxX())
			{
				player.setMoveState(FishPlayer.STILL);
				player.setPlatform(p);
			}
		}
	}
	
	public void jump()
	{
		if(jumpCounter <= 50)
		{
			player.setY(player.getMyY() - 2);
			
			if(player.moveState() == FishPlayer.UR)
				player.setX(player.getMyX() + 1);
			else if(player.moveState() == FishPlayer.UL)
				player.setX(player.getMyX() - 1);
			
			jumpCounter++;
		}
		
		else
		{
			if(player.moveState() == FishPlayer.UR)
				player.setMoveState(FishPlayer.DR);
			else if(player.moveState() == FishPlayer.UL)
				player.setMoveState(FishPlayer.DL);
			else	
				player.setMoveState(FishPlayer.D);
			jumpCounter = 0;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(Platform p: platforms)
			checkCollision(p);
		
		if(player.moveState() == FishPlayer.R)
		{
			player.setX(player.getMyX() + 1);
		}
		
		else if(player.moveState() == FishPlayer.L)
		{
			player.setX(player.getMyX() - 1);
		}
		
		else if(player.moveState() == FishPlayer.D || player.moveState() == FishPlayer.DR || player.moveState() == FishPlayer.DL)
		{
			player.setY(player.getMyY() + 2);
			
			if(player.moveState() == FishPlayer.DR)
				player.setX(player.getMyX() + 1);
			
			else if(player.moveState() == FishPlayer.DL)
				player.setX(player.getMyX() - 1);
		}
		
		else if(player.moveState() == FishPlayer.U || player.moveState() == FishPlayer.UR || player.moveState() == FishPlayer.UL)
		{		
			jump();
		}
			
							
		repaint();
	}
	
	public static void main(String[] args)
	{
		GUI gooey = new GUI();
	}

}
