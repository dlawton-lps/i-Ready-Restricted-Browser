package com.iready.restrictedAccess.frames;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.iready.restrictedAccess.Main;

public class Toolbar extends JFrame {

	/**
	 * [!] Generated serial version UID.
	 */
	private static final long serialVersionUID = 5634761084243330228L;
	
	/**
	 * [!] Image URL settings.
	 */
	static final URL pURL = Main.class.getResource("/powerSymbol.jpg");
	static final URL sURL = Main.class.getResource("/soundSymbol.jpg");
	
	/**
	 * [!] Toolbar button settings.
	 */
	public JButton powerButton = new JButton(new ImageIcon(pURL));
	public JButton soundButton = new JButton(new ImageIcon(sURL));
	
	/**
	 * [!] Screen size data.
	 */
	static int FRAME_HEIGHT = 95;
	static double DISPLAY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static double DISPLAY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	public Toolbar()
	{
		super("Restricted Access Toolbar");
		super.setLayout(new FlowLayout());
		super.setSize((int)DISPLAY_WIDTH, FRAME_HEIGHT);
		super.setResizable(false);
		super.setLocation(0, ((int)DISPLAY_HEIGHT - FRAME_HEIGHT));
		super.setUndecorated(true);
		super.getContentPane().setBackground(Color.BLACK);
		super.setAlwaysOnTop(true);
		super.toFront();
		
		/**
		 * [!] Component settings.
		 */
		powerButton.setSize(100, 50);
		powerButton.setBorder(BorderFactory.createEmptyBorder());
		powerButton.setContentAreaFilled(false);
		powerButton.setVisible(true);
		
		soundButton.setSize(100, 50);
		soundButton.setBorder(BorderFactory.createEmptyBorder());
		soundButton.setContentAreaFilled(false);
		soundButton.setVisible(true);
		
		super.add(powerButton);
		super.add(soundButton);
		
		super.setVisible(false);
	}

}
