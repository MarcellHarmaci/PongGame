package main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongKeyListener implements KeyListener {
	private JFrame frame;
	private Canvas table;
	private Timer timer;
	private int up;
	private int down;
	private boolean upPressed;
	private boolean downPressed;
	
	public PongKeyListener(JFrame frame, int upEvent, int downEvent) {
		this.frame = frame;
		up = upEvent;
		down = downEvent;
		upPressed = false;
		downPressed = false;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTable(Canvas myTable) {
		table = myTable;
	}
	public Canvas getTable() {
		return table;
	}
	public boolean isUpPressed() {
		return upPressed;
	}
	public boolean isDownPressed() {
		return downPressed;
	}
	public void onEscPressed(JFrame frame){}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == up) {
			upPressed = true;
		}
		if(e.getKeyCode() == down) {
			downPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			onEscPressed(frame);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		upPressed = false;
		downPressed = false;
	}
}