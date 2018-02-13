package com.iready.restrictedAccess.frames;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Popups {
	
	/**
	 * [!] Display data.
	 */
	static double DISPLAY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static double DISPLAY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	/**
	 * [!] Datetime data.
	 */
	static DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
	
	/**
	 * [!] Method for creating the popup window for controlling power options.
	 * @param toolFrame
	 * @param hoverBar
	 */
	public static void powerOptions(Toolbar toolFrame, AccessBar hoverBar)
	{
		JFrame powerWindow = new JFrame("Power Options");
		powerWindow.setSize(500, 500);
		powerWindow.setLocation(((int)DISPLAY_WIDTH / 3), ((int)DISPLAY_HEIGHT / 4) - 35);
		powerWindow.setUndecorated(true);
		
		GridLayout listLayout = new GridLayout(4, 1);
		powerWindow.setLayout(listLayout);
		
		JButton backButton = new JButton("Close");
		JButton closeButton = new JButton("Exit i-Ready");
		JButton logoutButton = new JButton("Log Off");
		JButton shutdownButton = new JButton("Shutdown");
		
		Font menuFont = new Font("Arial", Font.BOLD, 15);
		backButton.setFont(menuFont);
		closeButton.setFont(menuFont);
		logoutButton.setFont(menuFont);
		shutdownButton.setFont(menuFont);
		
		backButton.setBorder(new LineBorder(Color.WHITE));
		closeButton.setBorder(new LineBorder(Color.WHITE));
		logoutButton.setBorder(new LineBorder(Color.WHITE));
		shutdownButton.setBorder(new LineBorder(Color.WHITE));
		
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		closeButton.setBackground(Color.BLACK);
		closeButton.setForeground(Color.WHITE);
		logoutButton.setBackground(Color.BLACK);
		logoutButton.setForeground(Color.WHITE);
		shutdownButton.setBackground(Color.BLACK);
		shutdownButton.setForeground(Color.WHITE);
		
		powerWindow.add(shutdownButton);
		powerWindow.add(logoutButton);
		powerWindow.add(closeButton);
		powerWindow.add(backButton);
		
		powerWindow.setVisible(true);
		
		//Back button listener
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				powerWindow.dispose();
			}
		});
		
		//Log out button listener
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(confirmDialog("log off"))
				{	
					powerWindow.dispose();
					toolFrame.dispose();
					hoverBar.dispose();
					
					BufferedImage image = null;
					try {
						image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					} catch (HeadlessException | AWTException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					try {
						ImageIO.write(image, "png", new File("C:/tech/i-Ready Restricted Browser/Security/screenshot_logoff_USER-" + System.getProperty("user.name") + "_TIME-" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".png"));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					String shutdownCmd = "shutdown -l";
					try {
						Runtime.getRuntime().exec(shutdownCmd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		//Close button listener
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(confirmDialog("close i-Ready"))
				{	
					powerWindow.dispose();
					toolFrame.dispose();
					hoverBar.dispose();
					
					BufferedImage image = null;
					try {
						image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					} catch (HeadlessException | AWTException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					try {
						ImageIO.write(image, "png", new File("C:/tech/i-Ready Restricted Browser/Security/screenshot_exit_USER-" + System.getProperty("user.name") + "_TIME-" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".png"));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					String killCmd = "taskkill /F /IM chrome.exe";
					try {
						Runtime.getRuntime().exec(killCmd);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						revive();
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
				}
			}
		});
	
		/**
		 * [!] Shutdown button listener.
		 */
		shutdownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(confirmDialog("shut down"))
				{
					
					powerWindow.dispose();
					toolFrame.dispose();
					hoverBar.dispose();
					
					BufferedImage image = null;
					try {
						image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					} catch (HeadlessException | AWTException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					try {
						ImageIO.write(image, "png", new File("C:/tech/i-Ready Restricted Browser/Security/screenshot_shutdown_USER-" + System.getProperty("user.name") + "_TIME-" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".png"));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					String shutdownCmd = "shutdown -s -t 0";
					try {
						Runtime.getRuntime().exec(shutdownCmd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * [!] Method for reviving explorer.exe when iRRB is exited.
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public static void revive() throws IOException
	{
		Process p = new ProcessBuilder("explorer.exe").start();
	}
	
	/**
	 * [!] Method to open volume mixer window.
	 * @throws IOException
	 */
	public static void soundOptions() throws IOException
	{
		String volumeControlCommand = "sndvol.exe -f";
		Runtime.getRuntime().exec(volumeControlCommand);
	}
	
	/**
	 * [!] Method to double check with the user before doing 'func'.
	 * @param func
	 * @return boolean (continue?)
	 */
	public static boolean confirmDialog(String func)
	{
		int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to " + func + "?");
		if(confirmed == 0)
			return true;
		else
			return false;
	}

}