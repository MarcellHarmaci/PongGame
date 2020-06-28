package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
	private Canvas table;
	private Timer timer = new Timer(9998, this);
	
	
	public TimerListener(Canvas myTable) {
		table = myTable;
	}
	
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	private void gameOver() {
		JFrame frame = table.getFrame();
		frame.remove(table);
		Menu newMenu = new Menu(frame);
		frame.setSize(500, 300);
		frame.add(newMenu);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(timer.equals(e.getSource())) {
			if (table.getGame()) {
				table.move();
			}
			else {
				timer.stop();
				gameOver();
			}
		}
		
	}
	
}
