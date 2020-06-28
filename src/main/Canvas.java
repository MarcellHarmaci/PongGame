package main;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
	// Swing
	private JFrame frame;
	private PongKeyListener pongListener1;
	private PongKeyListener pongListener2;
	// Paddles
	private Polygon paddle1;
	private Polygon paddle2;
	private int paddleHeight = 200;
	// Ball
	private Ball ball = new Ball(30, 750, 400, 5, 5);
	private Color paddleColor;
	private Color ballColor;
	// Gameplay
	private boolean game;
	private int pointsP1;
	private int pointsP2;
	private Font myFont = new Font("Agency FB", Font.BOLD,  80);
	
	public Canvas(JFrame myFrame, PongKeyListener listener1, PongKeyListener listener2) {
		frame = myFrame;
		pongListener1 = listener1;
		pongListener2 = listener2;
		
		Settings settings = new Settings(frame);
		paddleColor = settings.getPaddleColor();
		ballColor = settings.getBallColor();
		
		pointsP1 = 0;
		pointsP2 = 0;
		game = true;
		
		int[] x1 = {50, 75, 75, 50};
		int[] y1 = {300, 300, 300 + paddleHeight, 300 + paddleHeight};
		paddle1 = new Polygon(x1, y1, 4);
		
		int[] x2 = {1461, 1486, 1486, 1461};
		int[] y2 = {300, 300, 300 + paddleHeight, 300 + paddleHeight};
		paddle2 = new Polygon(x2, y2, 4);
		
		setVisible(true);
		setFocusable(true);
	}
	
	public boolean getGame() {
		return game;
	}
	public JFrame getFrame() {
		return frame;
	}
	public int getPoints(int playerNum) {
		if(playerNum == 1)
			return pointsP1;
		else if(playerNum == 2)
			return pointsP2;
		else
			return -1;
	}
	
	public void goal() {
		if(ball.getPlace()[0] <= 0) {
			pointsP2++;
		} else pointsP1++;
		
		ball.reset(this);
		
		// Ending game at score 10
		if(pointsP1 >= 10 || pointsP2 >= 10) {
			game =  false;
		}
	}
	
	private void movePaddle(Polygon paddle, PongKeyListener pongListener) {
		if(paddle.getBounds().y > 0) {
			if (pongListener.isUpPressed() && !pongListener.isDownPressed()) {
				paddle.translate(0, -4);
			}
		}
		Dimension size = this.getSize();
		if(paddle.getBounds().y + paddleHeight < size.getHeight()) {
			if (pongListener.isDownPressed() && !pongListener.isUpPressed()) {
				paddle.translate(0, 4);
			}
		}
	}
	
	public void move() {
		// Moving ball
		ball.bounce(this, paddle1, paddle2);
		ball.move();
		
		// Moving paddles
		movePaddle(paddle1, pongListener1);
		movePaddle(paddle2, pongListener2);
		
		frame.repaint();
		
	}
	@Override
	public void paintComponent(Graphics g) {
		// Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getSize().width, getSize().height);
		g.setColor(Color.WHITE);
		g.fillRect(getSize().width/2 - 2, 0, 4, getSize().height);
		
		// Points
		g.setFont(myFont);
		g.drawString(String.valueOf(pointsP1), getSize().width / 4, getSize().height / 2);
		g.drawString(String.valueOf(pointsP2), getSize().width * 3/4, getSize().height / 2);
		
		// Paddles
		g.setColor(paddleColor);
		g.fillPolygon(paddle1);
		g.fillPolygon(paddle2);
		
		// Ball
		ball.paint(g, ballColor);
		
		// Clearing table when game ends
		if(!game) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getSize().width, getSize().height);
		}
		
	}
	
}
