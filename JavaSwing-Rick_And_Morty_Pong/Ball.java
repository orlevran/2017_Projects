package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	private int ballX, ballY, ballSpeed = 13;
	protected final int RADIUS = 8;
	private double direction;
	
	public Ball(){
		ballY = 300;
		ballX = 32;
	}
	
	public void setLocation(int x, int y){
		ballX = x;
		ballY = y;
	}
	
	public int getX(){
		return ballX;
	}
	
	public int getY(){
		return ballY;
	}
	
	public double getDirection(){
		return direction;
	}
	public void setDirection(double d){
		if(d < 0){
			d += 2*Math.PI;
		}
		this.direction = d;
	}
	
	public void movement(){
		ballX =(int)(ballX + ballSpeed * Math.cos(direction));
		ballY = (int)(ballY + ballSpeed * Math.sin(direction));
	}
	
	public Rectangle getBall(){
		return new Rectangle(ballX, ballY, RADIUS*2, RADIUS*2);
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.BLACK);
		g.fillOval(ballX, ballY, RADIUS*2, RADIUS*2);
	}
}
