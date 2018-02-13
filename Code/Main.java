package com.iready.restrictedAccess;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
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
import java.net.URL;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Main {

	static JFrame frame = new JFrame();
	static JFrame hoverBar = new JFrame();
	
	static int FRAME_HEIGHT = 95;
	static double DISPLAY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static double DISPLAY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	public static void main(String[] args) throws IOException {
		
		frame.setVisible(false);
		
		hoverBar.setLocation(0, ((int)DISPLAY_HEIGHT - 30));
		hoverBar.setSize((int)DISPLAY_WIDTH, 30);
		hoverBar.setUndecorated(true);
		hoverBar.getContentPane().setBackground(Color.BLACK);
		hoverBar.setVisible(true);
		hoverBar.setAlwaysOnTop(true);
		
		JButton activateBtn = new JButton("Options");
		activateBtn.setBackground(Color.BLACK);
		activateBtn.setForeground(Color.WHITE);
		activateBtn.setFont(new Font("Arial", Font.BOLD, 12));
		
		Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");
		
		/* Image Settings */
		URL pURL = Main.class.getResource("/powerSymbol.jpg");
		URL sURL = Main.class.getResource("/soundSymbol.jpg");
		
		/* Control Settings */
		JButton powerButton = new JButton(new ImageIcon(pURL));
		powerButton.setSize(100, 50);
		powerButton.setBorder(BorderFactory.createEmptyBorder());
		powerButton.setContentAreaFilled(false);
		powerButton.setVisible(true);
		
		JButton soundButton = new JButton(new ImageIcon(sURL));
		soundButton.setSize(100, 50);
		soundButton.setBorder(BorderFactory.createEmptyBorder());
		soundButton.setContentAreaFilled(false);
		soundButton.setVisible(true);
		
		/* Event Listeners */
		powerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				powerOptions();	
			}
		});
		soundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					soundOptions();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		/* Layout Settings */
		FlowLayout layout = new FlowLayout();
		frame.setLayout(layout);
		
		/* JFrame Settings */
		frame.setTitle("Restricted Access Bar");
		frame.setSize((int)DISPLAY_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setLocation(0, ((int)DISPLAY_HEIGHT - FRAME_HEIGHT));
		frame.setUndecorated(true);
		frame.add(powerButton);
		frame.add(soundButton);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setAlwaysOnTop(true);
		
		activateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(activateBtn.getText().equals(new String("Options")))
				{
					frame.setVisible(true);
					activateBtn.setText("Minimize");
					hoverBar.setLocation(0, (frame.getLocation().y - 30));
				}
				else if(activateBtn.getText().equals(new String("Minimize")))
				{
					frame.setVisible(false);
					hoverBar.setLocation(0, ((int)DISPLAY_HEIGHT - 30));
					activateBtn.setText("Options");
				}
			}
		});
		
		hoverBar.add(activateBtn);
		hoverBar.setVisible(true);
	}
	
	public static void powerOptions()
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
		
		frame.toFront();
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
					DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
					
					powerWindow.dispose();
					frame.dispose();
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
					DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
					
					powerWindow.dispose();
					frame.dispose();
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
	
		//Power button listener
		shutdownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(confirmDialog("shut down"))
				{
					
					try {
						Runtime.getRuntime().exec("cmd.exe /c start C:/tech/i-Ready Restricted Browser/Revive.bat");
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
					
					powerWindow.dispose();
					frame.dispose();
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
	
	public static void soundOptions() throws IOException
	{
		String volumeControlCommand = "sndvol.exe -f";
		Runtime.getRuntime().exec(volumeControlCommand);
	}
	
	public static boolean confirmDialog(String func)
	{
		int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to " + func + "?");
		if(confirmed == 0)
			return true;
		else
			return false;
	}
	
	public static void revive() throws IOException
	{
		Process p = new ProcessBuilder("explorer.exe").start();
		/*for(int i = 0; i < 3; i++)
			Runtime.getRuntime().exec("explorer.exe");*/
	}

}
