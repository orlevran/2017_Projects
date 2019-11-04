package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Board {
	private int top, left, width, height;
	protected final int MARGIN = 20;
	
	public void setSize(int w, int h){
		top = MARGIN;
		left = MARGIN;
		width = w - (MARGIN*2);
		height = h - (MARGIN*2);
	}
	
	public int getTop(){
		return top;
	}
	public int getLeft(){
		return left;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.BLACK);
		for(int index = 0 ; index < 3 ; index++){
			g.drawRect(left-index, top-index, width+index*2, height+index*2);
		}
	}
	
	public Rectangle getBorders(){
		Rectangle r = new Rectangle(left-2, top-2, width+2, height+2);
		return r;
	}
	
	public Rectangle[] getWalls(){
		Rectangle r[] = new Rectangle[4];
		
		r[0] = new Rectangle(0, 0, MARGIN, height + MARGIN * 2);
		r[1] = new Rectangle(0, 0, width + MARGIN * 2, MARGIN);
		r[2] = new Rectangle(MARGIN + width, 0, MARGIN, width + MARGIN*2);
		r[3] = new Rectangle(0, MARGIN + height, width + MARGIN * 2, MARGIN);
		
		return r;
	}
}
