package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle{

	double y, yVelocity;
	boolean upAccel, downAccel;
	int player, x;
	final double GRAVITY = 0.9;
	Ball b1;
	
	/*Constructor*/
	public AIPaddle(int player, Ball ball) {
		upAccel = false;
		downAccel = false;
		y = 210; 
		yVelocity = 0;
		b1 = ball;
		
		if (player == 1) {
			x = 20;
		} else {
			x = 640;
		}
		
		y += yVelocity;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}

	
	public void move() {
		y = b1.getY() - 40;
		
		if (y < 0) {
			y = 0;
		} else if (y > 420) {
			y = 420;
		}
	}
	
	public int getY() {
		//Return the integer cast of y.
		return (int) y;
	}
	
	
	
}
