package com.main;

import javax.swing.JFrame;

public class Window{
	
	public Window(String name, PlayPong pong) {
		JFrame frame = new JFrame(name);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.add(pong);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
