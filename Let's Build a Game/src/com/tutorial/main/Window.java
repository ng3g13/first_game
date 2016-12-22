package com.tutorial.main;

//this class creates the window for us
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{


	private static final long serialVersionUID = -240840600533728354L;
	public Window(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //start in middle of screen
		frame.add(game); //adding game class to the frame
		frame.setVisible(true);
		game.start(); //running the game
	}
	
}
