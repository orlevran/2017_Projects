package HomeWork003;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Player implements Moveable {
	private final int LEFT_SIDE = 0;
	private final int RIGHT_SIDE = 1;
	protected final int RADIUS = 10;
	protected int MARGIN = 20;
	protected final double SPEED = 2.1;
	
	protected double centerX, centerY, alpha, playerSpeed;
	protected Color teamColor;
	protected int side;
	
	public Player(){
		this(0,0,0,Color.WHITE,0);
	}
	
	public Player(int x, int y, double angle, Color c, int s){
		setCenter(x, y);
		setAlpha(angle);
		teamColor = c;
		side = s;
	}
	
	public Player(Color c, int s){
		this(0,0,0,c,s);
		if(s==RIGHT_SIDE){
			setAlpha(Math.PI);
		}
		else if(s==LEFT_SIDE){
			setAlpha(0);
		}
	}
	public void setCenter(int xPoint, int yPoint){
		centerX = xPoint;
		centerY = yPoint;
	}
	public void setAlpha(double angle){
		alpha = angle;
	}
	public double getX(){
		return centerX;
	}
	public double getY(){
		return centerY;
	}
	public double getAlpha(){
		return alpha;
	}
	public double getPlayerSpeed(){
		return playerSpeed;
	}
	
	public abstract void decideWhatToDo(Ball b, boolean control);
	
	public void drawMe(Graphics g){
		g.setColor(teamColor);
		g.fillOval((int)(centerX-RADIUS), (int)(centerY-RADIUS), RADIUS*2, RADIUS*2);
		g.setColor(Color.BLACK);
		g.drawOval((int)(centerX-RADIUS), (int)(centerY-RADIUS), RADIUS*2, RADIUS*2);
		g.drawLine((int)(centerX), (int)(centerY), (int)(centerX+RADIUS*Math.cos(alpha)), (int)(centerY+RADIUS*Math.sin(alpha)));
	}
}
