package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class Settings extends JPanel implements ItemListener, ActionListener {
	private JFrame frame;
	private JButton menuButton;
	private JRadioButton[] pButtons;
	private JRadioButton[] bButtons;
	private Color paddleColor;
	private Color ballColor;
	private static File file;
	static {
		file = new File("colorFile.ser");
	}
	
	public Settings(JFrame onFrame) {
		// Deserialization
		Color[] colors = deserializeSettings();
		paddleColor = colors[0];
		ballColor = colors[1];
		
		frame = onFrame;
		this.setLayout(new GridLayout(2, 5));
		
		// Initializing radioButtons
		JLabel paddleLabel = new JLabel("Paddle Color");
		JRadioButton pRed, pGreen, pBlue, pWhite;
		if(paddleColor.equals(Color.RED))
			pRed = new JRadioButton("Red", true);
		else pRed = new JRadioButton("Red");
		if(paddleColor.equals(Color.GREEN))
			pGreen = new JRadioButton("Green", true);
		else pGreen = new JRadioButton("Green");
		if(paddleColor.equals(Color.BLUE))
			pBlue = new JRadioButton("Blue", true);
		else pBlue = new JRadioButton("Blue");
		if(paddleColor.equals(Color.WHITE))
			pWhite = new JRadioButton("White", true);
		else pWhite = new JRadioButton("White");
		
		JLabel ballLabel = new JLabel("Ball Color");
		JRadioButton bRed, bGreen, bBlue, bWhite;
		if(ballColor.equals(Color.RED))
			bRed = new JRadioButton("Red", true);
		else bRed = new JRadioButton("Red");
		if(ballColor.equals(Color.GREEN))
			bGreen = new JRadioButton("Green", true);
		else bGreen = new JRadioButton("Green");
		if(ballColor.equals(Color.BLUE))
			bBlue = new JRadioButton("Blue", true);
		else bBlue = new JRadioButton("Blue");
		if(ballColor.equals(Color.WHITE))
			bWhite = new JRadioButton("White", true);
		else bWhite = new JRadioButton("White");
		
		// Grouping and adding to JPanel
		pButtons = new JRadioButton[] {
			pRed, pGreen, pBlue, pWhite
		};
		bButtons = new JRadioButton[] {
			bRed, bGreen, bBlue, bWhite
		};
		ButtonGroup pGroup = new ButtonGroup();
		ButtonGroup bGroup = new ButtonGroup();
		
		this.add(paddleLabel, BorderLayout.WEST);
		for(JRadioButton pB : pButtons) {
			pGroup.add(pB);
			pB.addItemListener(this);
			pB.setVisible(true);
			this.add(pB);
		}
		menuButton = new JButton("Menu");
		menuButton.addActionListener(this);
		this.add(menuButton);
		
		this.add(ballLabel, BorderLayout.EAST);
		for(JRadioButton bB : bButtons) {
			bGroup.add(bB);
			bB.addItemListener(this);
			bB.setVisible(true);
			this.add(bB);
		}
		this.setVisible(true);
		
	}
	public Color getPaddleColor() {
		return paddleColor;
	}
	public Color getBallColor() {
		return ballColor;
	}
	private void setPaddleColor(Color newColor) {
		paddleColor = newColor;
	}
	private void setBallColor(Color newColor) {
		ballColor = newColor;
	}
	
	
	private void onMenuButton(JFrame frame) {
		serializeSettings();
		
		frame.remove(this);
		main.Menu newMenu = new Menu(frame);
		frame.add(newMenu);
		frame.revalidate();
		
	}
	public void serializeSettings() {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(paddleColor);
			oos.writeObject(ballColor);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found during serialization\nPath: " + file.toString());
		} catch (IOException e) {
			System.out.println("IOException caught during serialization");
		}
		
	}
	public Color[] deserializeSettings() {
		// Default values are 3, 0 (as WHITE paddle, RED ball)
		Color[] colors = new Color[]{Color.WHITE, Color.RED};
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			colors[0] = (Color) ois.readObject();
			colors[1] = (Color) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found during deserialization\nPath: " + file.toString());
		} catch (IOException e) {
			System.out.println("IOException caught during deserialization");
		} catch (ClassNotFoundException e) {
			System.out.println("File not found during deserialization");
		}
		
		return colors;
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			paddleButtonReaction(e, pButtons);
			ballButtonReaction(e, bButtons);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(menuButton.equals(e.getSource())) {
			onMenuButton(frame);
		}
		
	}
	public void paddleButtonReaction(ItemEvent e, JRadioButton[] buttons) {
		if(buttons[0].equals(e.getSource())) {
			setPaddleColor(Color.RED);
		}
		else if(buttons[1].equals(e.getSource())) {
			setPaddleColor(Color.GREEN);
		}
		else if(buttons[2].equals(e.getSource())) {
			setPaddleColor(Color.BLUE);
		}
		else if(buttons[3].equals(e.getSource())) {
			setPaddleColor(Color.WHITE);
		}
		
	}
	public void ballButtonReaction(ItemEvent e, JRadioButton[] buttons) {
		if(buttons[0].equals(e.getSource())) {
			setBallColor(Color.RED);
		}
		else if(buttons[1].equals(e.getSource())) {
			setBallColor(Color.GREEN);
		}
		else if(buttons[2].equals(e.getSource())) {
			setBallColor(Color.BLUE);
		}
		else if(buttons[3].equals(e.getSource())) {
			setBallColor(Color.WHITE);
		}
		
	}
	
}
