package test;

import main.Canvas;
import main.PongKeyListener;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.swing.*;

public class CanvasTest {
	@Test
	public void goalTest() {
		JFrame frame = new JFrame();
		PongKeyListener pkl1 = new PongKeyListener(frame, 1, 2);
		PongKeyListener pkl2 = new PongKeyListener(frame, 1, 2);
		Canvas table = new Canvas(frame, pkl1, pkl2);
		table.goal();
		assertEquals(1, table.getPoints(1));
	}
	@Test
	public void getGameTest() {
		JFrame frame = new JFrame();
		PongKeyListener pkl1 = new PongKeyListener(frame, 1, 2);
		PongKeyListener pkl2 = new PongKeyListener(frame, 1, 2);
		Canvas table = new Canvas(frame, pkl1, pkl2);
		assertEquals(true, table.getGame());
		for(int i = 0; i <= 10; i++) {
			table.goal();
		}
		assertEquals(10, table.getPoints(2));
		assertEquals(false, table.getGame());
	}
}
