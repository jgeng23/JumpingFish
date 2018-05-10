import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlatformTest extends JFrame
{
	Image fish;
	JLabel fishLabel;
	JButton test;

	public PlatformTest()
	{
		//Initialize character
		ClassLoader cldr = this.getClass().getClassLoader();
		ImageIcon fishIcon = new ImageIcon(cldr.getResource("fish.gif"));
		fishLabel = new JLabel(fishIcon);
		test = new JButton();

		//Create container
		//Container container = getContentPane();
		//container.setLayout(new BorderLayout());
		
		//add platform

		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//show window
		setSize(800,800);
		setVisible(true);

	}
	
	public void paintComponent(Graphics g)
	{
		g.fillRect(100, 100, 50, 50);
	}

	public static void main(String[] args)
	{
		PlatformTest test = new PlatformTest();
	}
}
