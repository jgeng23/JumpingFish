
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class test extends JFrame
{
	JButton b1;
	JLabel l1;
	public test()
	{
		//setTitle("Background Color for JFrame");
		
		//setLocationRelativeTo(null); Makes window appear in center
		
		//setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("blue_sky.png")));
		//setLayout(new FlowLayout());
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		
		setSize(800,800);
		setVisible(true);
		/*
		One way
		-----------------
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
		add(background);
		background.setLayout(new FlowLayout());
		l1=new JLabel("Here is a button");
		b1=new JButton("I am a button");
		background.add(l1);
		background.add(b1);
		 */
		// Another way
		
		//l1=new JLabel("Here is a button");
		//b1=new JButton("I am a button");
		//add(l1);
		//add(b1);
		// Just for refresh :) Not optional!
		//setSize(799,799);
	}
	
	public static void main(String args[])
	{
		new test();
	}
}

