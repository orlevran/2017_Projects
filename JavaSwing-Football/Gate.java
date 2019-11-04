package HomeWork003;

import java.awt.Color;
import java.awt.Graphics;

public class Gate {
	int left, top, width, height;
	
	public void setSize(int l, int t, int w, int h){
		left = l;
		top = t;
		width = w;
		height = h;
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.WHITE);
		g.drawRect(left, top, width, height);
	}
}
