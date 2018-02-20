package com.iready.restrictedAccess;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.iready.restrictedAccess.frames.AccessBar;
import com.iready.restrictedAccess.frames.Popups;
import com.iready.restrictedAccess.frames.Toolbar;

public class Main {

	static Toolbar toolFrame = new Toolbar();
	static AccessBar hoverBar = new AccessBar();
	
	static int FRAME_HEIGHT = 95;
	static double DISPLAY_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static double DISPLAY_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	
	public static void main(String[] args) throws IOException {
		
		Runtime.getRuntime().exec("taskkill /F /IM explorer.exe");
		
		String webTarget = "https://login.i-ready.com/?utm_source=WordofMouth&utm_medium=vanityURL&utm_content=iready_com&utm_campaign=Vanity";
		Runtime.getRuntime().exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe --chrome-frame --incognito --kiosk --force-device-scale-factor=0.88 " + webTarget);
		
		/**
		 * [!] Toolbar action listeners.
		 */
		toolFrame.powerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popups.powerOptions(toolFrame, hoverBar);	
			}
		});
		toolFrame.soundButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Popups.soundOptions();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
			
		/**
		 * [!] Hoverbar action listener.
		 */
		hoverBar.activateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hoverBar.activateBtn.getText().equals(new String("Options")))
				{
					toolFrame.setVisible(true);
					hoverBar.activateBtn.setText("Minimize");
					hoverBar.setLocation(0, (toolFrame.getLocation().y - 30));
				}
				else if(hoverBar.activateBtn.getText().equals(new String("Minimize")))
				{
					toolFrame.setVisible(false);
					hoverBar.setLocation(0, ((int)DISPLAY_HEIGHT - 30));
					hoverBar.activateBtn.setText("Options");
				}
			}
		});

	}
}
