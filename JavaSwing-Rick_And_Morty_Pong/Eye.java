package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Eye {
	private int left, top, pupilLeft, pupilTop, ballX, ballY, pupilRadius, eyeRadius;
	
	public Eye(){
		left = 0;
		top = 0;
		setVector();
	}
	
	public Eye(int l, int t, int pr, int er){
		left = l;
		top = t;
		pupilRadius = pr;
		eyeRadius = er;
		setVector();
	}
	
	public void setBallLocation(int x, int y){
		ballX = x;
		ballY = y;
		setVector();
	}
	
	public void setVector(){
		int eyeX, eyeY, pupilX, pupilY;
		double dirX, dirY, length, distance;

		eyeX = left + eyeRadius;
		eyeY = top + eyeRadius;

		dirX = ballX - eyeRadius;
		dirY = ballY - eyeRadius;
		
		length = Math.sqrt(Math.pow(dirX, 2)+Math.pow(dirY, 2));
		
		dirX /= length;
		dirY /= length;
		
		distance = eyeRadius - pupilRadius;
		
		pupilX = (int) (dirX * distance + eyeX);
		pupilY = (int) (dirY * distance + eyeY);
		
		pupilTop = pupilY - pupilRadius;
		pupilLeft = pupilX - pupilRadius;
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(left, top, eyeRadius*2, eyeRadius*2);
		g.setColor(Color.BLACK);
		g.drawOval(left, top, eyeRadius*2, eyeRadius*2);
		g.fillOval(pupilLeft, pupilTop, pupilRadius*2, pupilRadius*2);
	}
}
