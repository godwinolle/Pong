package com.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
public class PlayPong extends Canvas implements Runnable{
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = WIDTH * 9/16;
	
	public boolean running = false;
	private Thread PongThread;
	
	private Ball ball;
	private Paddle paddle1;
	private Paddle paddle2;
	public MainMenu menu;
	
	public PlayPong() {
		canvasSetup();
		new Window("Simple Pong", this);
		initialize();
		
		this.addKeyListener(new KeyInput(paddle1, paddle2));
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.setFocusable(true);
	}

	private void initialize() {
		//initialize ball
		ball = new Ball();
		
		//initialize paddles
		paddle1 = new Paddle(Color.green, true);
		paddle2 = new Paddle(Color.red, false);
		
		//initialize main menu
		menu = new MainMenu(this);
	}

	private void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}

	public void run() {
		this.requestFocus();
		
		//game Timer
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			if(running) draw();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void update() {
		if(!menu.active) {
			//update ball
			ball.update(paddle1, paddle2);
			//update paddle
			paddle1.update(ball);
			paddle2.update(ball);
		}
	}

	private void draw() {
		//initialize drawing tools
		
		BufferStrategy buffer = this.getBufferStrategy();
		
		if(buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		//draw background
		drawBackground(g);
		
		if(menu.active) {
			menu.draw(g);
		}
		
		//draw ball
		ball.draw(g);
		
		//draw paddles and scores
		paddle1.draw(g);
		paddle2.draw(g);
		
		//actually drawing everything
		g.dispose();
		buffer.show();
	}

	private void drawBackground(Graphics g) {
		//black background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//dotted line in middle
		g.setColor(Color.white);
		Graphics2D g2D = (Graphics2D) g;
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {10}, 0);
		
		g2D.setStroke(dashed);
		g2D.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
	}

	public void start() {
		PongThread = new Thread(this);
		PongThread.start();
		running = true;
	}
	
	public void stop() {
		try {
			PongThread.join();
			running = false;
		} catch (InterruptedException e) {
			System.out.println("Thread not found");
		}
		
	}
	
	public static void main(String[] args) {
		new PlayPong();
	}

	public static int sign(double d) {
		if(d <= 0)
			return -1;
		return 1;
	}

	public static int ensureRange(int val, int min, int max) {
		return Math.min(Math.max(val, min), max);
	}
}
