package com.main;

import java.awt.*;
public class Ball {
	public static final int SIZE = 16;
	
	private int x, y;
	private int xVel, yVel; //value of -1 or 1
	private int speed = 5;
	
	public Ball() {
		reset();
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	private void reset() {
		// initial position
		x = PlayPong.WIDTH / 2 - SIZE / 2;
		y = PlayPong.HEIGHT / 2 - SIZE / 2;
		
		//initialize velocities
		xVel = PlayPong.sign(Math.random() * 2.0 - 1);
		yVel = PlayPong.sign(Math.random() * 2.0 - 1);
	}
	
	public void changeYDir() {
		yVel *= -1;
	}
	public void changeXDir() {
		xVel *= -1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, SIZE, SIZE);
		
	}

	public void update(Paddle p1, Paddle p2) {
		//this updates the movement of the ball
		x += xVel * speed;
		y += yVel * speed;
		
		//collisions
		if(y + SIZE >= PlayPong.HEIGHT || y <= 0) {
			changeYDir();
		}
		
		//with walls
		if(x + SIZE >= PlayPong.WIDTH) {
			p1.addPoint();
			reset();
		}
		
		if(x <= 0) {
			p2.addPoint();
			reset();
		}
			
	}
}
