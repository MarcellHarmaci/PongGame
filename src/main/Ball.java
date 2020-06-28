package main;

import java.awt.*;

public class Ball {
	private int[] place = new int[2];
	private int[] direction = new int[2];
	private int d;
	private Point topOfBall = new Point();
	private Point bottomOfBall = new Point();
	private Point leftOfBall = new Point();
	private Point rightOfBall = new Point();
	
	public Ball(int diameter, int placeX, int placeY, int dirX, int dirY) {
		d = diameter;
		place[0] = placeX;
		place[1] = placeY;
		direction[0] = dirX;
		direction[1] = dirY;
	}
	public int[] getPlace() {
		return place;
	}
	public int[] getDir() {
		return direction;
	}
	
	private void randomDir() {
		/*
		This method could randomise a new direction for the ball
		when a point is scored.
		 */
	}
	
	public void reset(main.Canvas table) {
		int width = table.getSize().width;
		int height = table.getSize().height;
		
		place[0] = (width - d) / 2;
		place[1] = (height - d) / 2;
		
		randomDir();
		
		table.repaint();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void bounce(Canvas table, Polygon paddle1, Polygon paddle2) {
		// Setting current locations
		topOfBall.setLocation(place[0] + d/2, place[1]);
		bottomOfBall.setLocation(place[0] + d/2, place[1] + d);
		leftOfBall.setLocation(place[0], place[1] + d/2);
		rightOfBall.setLocation(place[0] + d, place[1] + d/2);
		
		// Hit with paddle
		if(paddle1.contains(leftOfBall) || paddle2.contains(rightOfBall)) {
			bounceHoriz();
		}
		else if(paddle1.contains(topOfBall) || paddle1.contains(bottomOfBall)
			|| paddle2.contains(topOfBall) || paddle2.contains(bottomOfBall)) {
			bounceVertic();
		}
		// Hits bounds of table
		boolean goal = false;
		if(topOfBall.y <= 0 || bottomOfBall.y >= table.getSize().height)
			bounceVertic();
		else if(leftOfBall.x <= 0 || rightOfBall.x >= table.getSize().width)
			goal = true;
		
		// Incrementing players' points
		if(goal) {
			table.goal();
		}
	}
	public void bounceVertic() {
		direction[1] = direction[1] * (-1);
	}
	public void bounceHoriz() {
		direction[0] = direction[0] * (-1);
	}
	
	public void move() {
		place[0] += direction[0];
		place[1] += direction[1];
	}
	
	public void paint(Graphics g, Color ballColor) {
		g.setColor(ballColor);
		g.fillOval(place[0], place[1], d, d);
	}
	
}
