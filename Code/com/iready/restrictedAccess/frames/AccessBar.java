package com.iready.restrictedAccess.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AccessBar extends JFrame{

	/**
	 * [!] Generated serial version UID.
	 */
	private static final long serialVersionUID = -2832117238378872420L;

	static double DISPLAY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static double DISPLAY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	/* Window Components */
	public JButton activateBtn = new JButton("Options");
	
	public AccessBar()
	{
		super("Access Bar");
		super.setLocation(0, ((int)DISPLAY_HEIGHT - 30));
		super.setSize((int)DISPLAY_WIDTH, 30);
		super.setUndecorated(true);
		super.getContentPane().setBackground(Color.BLACK);
		super.setVisible(true);
		super.setAlwaysOnTop(true);
		
		activateBtn.setBackground(Color.BLACK);
		activateBtn.setForeground(Color.WHITE);
		activateBtn.setFont(new Font("Arial", Font.BOLD, 12));
		super.add(activateBtn);
		
		super.setVisible(true);
	}
	
}
