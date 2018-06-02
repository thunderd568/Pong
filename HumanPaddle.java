package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle{

	double y, yVelocity;
	boolean upAccel, downAccel;
	int player, x;
	final double GRAVITY = 0.9;
	
	/*Constructor*/
	public HumanPaddle(int player) {
		upAccel = false;
		downAccel = false;
		y = 210; 
		yVelocity = 0;
		
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
		if (upAccel) {
			yVelocity -= 2;
		} else if (downAccel) {
			yVelocity += 2;
		} else if (!upAccel && !downAccel) {
			yVelocity *= GRAVITY;
		}
		//Cap the velocity at 5
		if (yVelocity >= 5) {
			yVelocity = 5;
		} else if (yVelocity <= -5) {
			yVelocity = -5;
		}
		
		y += yVelocity;
		
		if (y < 0) {
			y = 0;
		} else if (y > 420) {
			y = 420;
		}
	}

	public void setUpAccel(boolean input) {
		upAccel = input;
	}
	
	public void setDownAccel(boolean input) {
		downAccel = input;
	}
	
	public int getY() {
		//Return the integer cast of y.
		return (int) y;
	}
	
	
	
}
