package main;

import javax.swing.*;

public class Game {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setResizable(true);
		
		Menu menu = new Menu(frame);
		frame.add(menu);
		frame.setVisible(true);
		
	}
	
}
