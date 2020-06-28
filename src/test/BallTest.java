package test;

import main.Ball;
import main.Canvas;
import main.PongKeyListener;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.Assert.assertEquals;

public class BallTest {
	@Test
	public void bounceHorizTest() {
		Ball ball = new Ball(10, 50, 50, -3, 3);
		ball.bounceHoriz();
		
		int[] direction = {3, 3};
		
		assertEquals(ball.getDir()[0], direction[0]);
		assertEquals(ball.getDir()[1], direction[1]);
	}
	@Test
	public void bounceVerticTest() {
		Ball ball = new Ball(10, 50, 50, -3, 3);
		ball.bounceVertic();
		ball.move();
		
		int[] direction = {-3, -3};
		
		assertEquals(ball.getDir()[0], direction[0]);
		assertEquals(ball.getDir()[1], direction[1]);
	}
	@Test
	public void bounceFromWallTest() {
		JFrame frame = new JFrame();
		PongKeyListener pkl1 = new PongKeyListener(frame, 1, 2);
		PongKeyListener pkl2 = new PongKeyListener(frame, 1, 2);
		Canvas table = new Canvas(frame, pkl1, pkl2);
		table.setSize(100, 100);
		Polygon poly1 = new Polygon();
		Polygon poly2 = new Polygon();
		Ball ball = new Ball(10, 50, 0, 3, -3);
		
		ball.bounce(table, poly1, poly2);
		
		int[] direction = {3, 3};
		assertEquals(ball.getDir()[0], direction[0]);
		assertEquals(ball.getDir()[1], direction[1]);
	}
	@Test
	public void bounceFromPaddleTest() {
		JFrame frame = new JFrame();
		PongKeyListener pkl1 = new PongKeyListener(frame, 1, 2);
		PongKeyListener pkl2 = new PongKeyListener(frame, 1, 2);
		Canvas table = new Canvas(frame, pkl1, pkl2);
		table.setSize(100, 100);
		int[] coordX = {0, 10, 10, 0};
		int[] coordY = {0, 0, 100, 100};
		Polygon poly1 = new Polygon(coordX, coordY, 4);
		Polygon poly2 = new Polygon();
		Ball ball = new Ball(10, 9, 50, 3, -3);
		
		ball.bounce(table, poly1, poly2);
		
		int[] direction = {-3, -3};
		assertEquals(ball.getDir()[0], direction[0]);
		assertEquals(ball.getDir()[1], direction[1]);
	}
	@Test
	public void bounceFromBothTest() {
		JFrame frame = new JFrame();
		PongKeyListener pkl1 = new PongKeyListener(frame, 1, 2);
		PongKeyListener pkl2 = new PongKeyListener(frame, 1, 2);
		Canvas table = new Canvas(frame, pkl1, pkl2);
		table.setSize(100, 100);
		int[] coordX = {0, 10, 10, 0};
		int[] coordY = {0, 0, 100, 100};
		Polygon poly1 = new Polygon(coordX, coordY, 4);
		Polygon poly2 = new Polygon();
		Ball ball = new Ball(10, 9, 0, -3, -3);
		
		ball.bounce(table, poly1, poly2);
		
		int[] direction = {3, 3};
		assertEquals(direction[0], ball.getDir()[0]);
		assertEquals(direction[1], ball.getDir()[1]);
	}
	@Test
	public void moveTest() {
		Ball ball = new Ball(10, 50, 50, 3, 3);
		ball.move();
		
		int[] place = {53, 53};
		assertEquals(ball.getPlace()[0], place[0]);
		assertEquals(ball.getPlace()[1], place[1]);
	}
	@Test
	public void resetTest() {
		JFrame frame = new JFrame();
		PongKeyListener pkl1 = new PongKeyListener(frame, 1, 2);
		PongKeyListener pkl2 = new PongKeyListener(frame, 1, 2);
		Canvas table = new Canvas(frame, pkl1, pkl2);
		table.setSize(100, 100);
		Ball ball = new Ball(10, 0, 0, -3, -3);
		ball.reset(table);
		
		int[] place = {45, 45};
		assertEquals(ball.getPlace()[0], place[0]);
		assertEquals(ball.getPlace()[1], place[1]);
	}
	
}
