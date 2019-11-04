package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	private int left, top;
	private final int SPEED = 9;
	protected final int WIDTH = 10;
	protected final int HEIGHT = 120;
	
	public Player(){
		this(0,0);
	}
	
	public Player(int l, int t){
		setLocation(l,t);
	}
	
	public void setLocation(int l, int t){
		this.left = l;
		this.top = t;
	}
	
	public int getTop(){
		return top;
	}
	
	public void movement(Ball b, int t, int bottom) {
		if (b.getBall().getCenterY() > (top) + HEIGHT / 2) {
			top += 2*SPEED/3;
		} else if (b.getBall().getCenterY() < (top) + HEIGHT / 2) {
			top -= 2*SPEED/3;
		}
		
		if(top < t){
			top = t;
		}
		else if(top + HEIGHT > bottom){
			top = bottom - HEIGHT;
		}
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(left, top, WIDTH, HEIGHT);
	}
	
	public Rectangle getPlayer(){
		return new Rectangle(left, top, WIDTH, HEIGHT);
	}
	
	public void moveUp(int lim){
		top-=SPEED*2;
		if(top < lim){
			top = lim;
		}
	}
	
	public void moveDown(int lim){
		top+=SPEED*2;
		if(top + HEIGHT > lim){
			top = lim - HEIGHT;
		}
	}
}
