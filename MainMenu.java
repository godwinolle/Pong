package com.main;

import java.awt.*;
import java.awt.event.*;
public class MainMenu extends MouseAdapter{
	public boolean active;
	
	//button play 
	private Rectangle playBox;
	private String playTxt = "Play";
	private boolean pHighlight = false;
	
	//button quit
	private Rectangle quitBox;
	private String quitTxt = "Quit";
	private boolean qHighlight = false;
	
	private Font font;
	
	public MainMenu(PlayPong pong) {
		active = true;
		pong.start();
		
		int w, h, x, y;
		
		w = 300;
		h = 150;
		
		y = PlayPong.HEIGHT/2 - h/2;
		x = PlayPong.WIDTH * 4 - w/2;
		
		playBox = new Rectangle(100, y, w, h);
		
		x = PlayPong.WIDTH * 3/4 - w/2;
		quitBox = new Rectangle(x, y, w, h);
		
		font = new Font("Roboto", Font.PLAIN, 100);
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g.setFont(font);
		//when highlighted, its going to change from black to white
		g.setColor(Color.black);
		if(pHighlight) {
			g.setColor(Color.white);
		}
		g2D.fill(playBox);
		
		g.setColor(Color.black);
		if(qHighlight) {
			g.setColor(Color.white);
		}
		g2D.fill(quitBox);
		
		g.setColor(Color.white);
		g2D.draw(playBox);
		g2D.draw(quitBox);
		
		
		int strWidth;
		int strHeight;
		
		
		//drawing the string for Play Button
		strWidth = g.getFontMetrics(font).stringWidth(playTxt);
		strHeight = g.getFontMetrics(font).getHeight();
		
		g.setColor(Color.green);
		double yeah = playBox.getX() + playBox.getWidth()/2 - strWidth/2;
		double ye = playBox.getY() + playBox.getHeight()/2 + strHeight/4;
		g.drawString(playTxt, (int) yeah, (int) ye);
		
		//drawing the string for Quit button
		g.setColor(Color.red);
		double nahh = quitBox.getX() + quitBox.getWidth()/2 - strWidth/2;
		double no = quitBox.getY() + quitBox.getHeight()/2 + strHeight/4;
		g.drawString(quitTxt, (int) nahh, (int) no);
	}
	
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		
		if(playBox.contains(p))
			active = false;
		else if(quitBox.contains(p))
			System.exit(0);
			
	}
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		
		pHighlight = playBox.contains(p);
		qHighlight = quitBox.contains(p);
	}
}
