package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Menu extends JPanel implements ActionListener {
	private JFrame frame;
	private JButton game;
	private JButton settings;
	private JButton exit;
	
	public Menu(JFrame frame) {
		this.frame = frame;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		game = new JButton("Game");
		settings = new JButton("Settings");
		exit = new JButton("Exit");
		
		game.setEnabled(true);
		settings.setEnabled(true);
		exit.setEnabled(true);
		
		game.addActionListener(this);
		settings.addActionListener(this);
		exit.addActionListener(this);
		
		game.setAlignmentX(Component.CENTER_ALIGNMENT);
		settings.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createVerticalStrut(30));
		add(game);
		add(Box.createVerticalStrut(50));
		add(settings);
		add(Box.createVerticalStrut(50));
		add(exit);
		add(Box.createVerticalStrut(30));
	}
	
	private void onGameClicked(JFrame frame) {
		// Removing menu from frame
		frame.remove(this);
		
		// Initializing
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		PongKeyListener listener1 = new PongKeyListener(frame, KeyEvent.VK_W, KeyEvent.VK_S);
		PongKeyListener listener2 = new PongKeyListener(frame, KeyEvent.VK_UP, KeyEvent.VK_DOWN) {
			@Override
			public void onEscPressed(JFrame frame) {
				getTimer().stop();
				frame.remove(getTable());
				Menu newMenu = new Menu(frame);
				frame.setSize(500, 300);
				frame.add(newMenu);
			}
		};
		
		main.Canvas table = new Canvas(frame, listener1, listener2);
		listener2.setTable(table);
		table.addKeyListener(listener1);
		table.addKeyListener(listener2);
		frame.add(table);
		
		TimerListener pongTimerListener = new TimerListener(table);
		Timer timer = new Timer(10, pongTimerListener);
		listener2.setTimer(timer);
		pongTimerListener.setTimer(timer);
		
		table.setFocusable(true);
		table.grabFocus();
		timer.start();
		
	}
	private void onSettingsClicked(JFrame frame) {
		frame.remove(this);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setResizable(true);
		Settings settings = new Settings(frame);
		frame.add(settings);
		frame.setVisible(true);
		frame.revalidate();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(game.equals(e.getSource()))
			onGameClicked(frame);
		else if(settings.equals(e.getSource()))
			onSettingsClicked(frame);
		else if(exit.equals(e.getSource()))
			frame.dispose();
	}
	
}
