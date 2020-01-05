package com.main;

import java.awt.*;

public class Paddle {
	private int x, y;
	private int vel = 0;
	private int speed = 10;
	private int width = 22;
	private int height = 85;
	private int score = 0;
	private Color color;
	private boolean left;
	
	public Paddle(Color c, boolean left) {
		this.color = c;
		this.left = left;
		
		if(left)
			x = 0;
		else
			x = PlayPong.WIDTH - width;
		y = PlayPong.HEIGHT/2 - height/2;
	}
	
	public void addPoint() {
		score++;
	}

	public void draw(Graphics g) {
		//draw paddle
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		//draw score
		int sx;
		String scoreTxt = Integer.toString(score);
		Font font = new Font("Roboto", Font.PLAIN, 50);
		
		int strWidth = g.getFontMetrics(font).stringWidth(scoreTxt) + 1;
		int padding = 25; //25 pixels
		
		if(left)
			sx = PlayPong.WIDTH/2 - padding - strWidth;
		else
			sx = PlayPong.WIDTH/2 + padding;
		
		g.setFont(font);
		g.drawString(scoreTxt, sx, 50);
	}

	public void update(Ball b) {
		//update position
		
		y = PlayPong.ensureRange(y += vel, 0, PlayPong.HEIGHT - height);
		
		int ballX = b.getX();
		int ballY = b.getY();
		
		//collisions with ball
		if(left == true) {
			if(ballX <= width && ballY + Ball.SIZE >= y && ballY <= y + height)
				b.changeXDir();	
		}
		else if(ballX + Ball.SIZE >= PlayPong.WIDTH - width && ballY + Ball.SIZE >= y && ballY <= y + height) {
				b.changeXDir();
		}
		
	}

	public void switchDirections(int i) {
		vel = speed * i;
	}
	
	public void stop() {
		vel = 0;
	}
}
