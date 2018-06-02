package Pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	HumanPaddle p1;
	AIPaddle p2;
	Ball b1;
	boolean gameStarted;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		gameStarted = false;
		b1 = new Ball();
		p1 = new HumanPaddle(1);
		p2 = new AIPaddle(2, b1);
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//If ball is off the screen on the left or right side
		if (b1.getX() < -10) {
			g.setColor(Color.red); //Need to change the color so we can see it.
			g.drawString("Game Over\nYou Lose >P", 280, 250);
		} else if (b1.getX() > 710) {
			g.setColor(Color.red);
			g.drawString("Game over, You win!", 290, 250);
		} else {
			p1.draw(g);
			p2.draw(g);
			b1.draw(g);
		}
		
		if (!gameStarted) {
			g.setColor(Color.white);
			g.drawString("Press ENTER to Start the Game", 260, 350);
		}
		
	}
	
	public void update (Graphics g) {
		paint(g);
		
	}
	
	public void run() {
		for (;;) {
			if (gameStarted) {
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1, p2);
			} 
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
	}
}
