package test;

import main.Settings;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

import static org.junit.Assert.assertEquals;

public class SettingsTest {
	@Test
	public void serializationTest() {
		JFrame frame = new JFrame();
		Settings settings = new Settings(frame);
		settings.serializeSettings();
		settings.deserializeSettings();
		assertEquals(Color.WHITE, settings.getPaddleColor());
		assertEquals(Color.RED, settings.getBallColor());
	}
	
}
