package HomeWork003;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SoccerField {
	private static int width, height;
	private final int DELTA = 15, MARGIN = 20;
	private Gate leftGate, rightGate;
	
	public SoccerField(){
		leftGate = new Gate();
		rightGate = new Gate();
	}
	
	public void setSize(int w, int h){
		width = w;
		height = h;
	}
	
	public void drawMe(Graphics g, int home, int away){
		for(int index = 0 ; index < DELTA ; index++){
			if(index%2==0){
				g.setColor(new Color(100, 200, 100));
			}
			else{
				g.setColor(new Color(120, 230, 120));
			}
			g.fillRect(index*(width/DELTA), 0, width/DELTA, height);
		}
		
		g.setColor(Color.WHITE);
		
		for(int index = 0 ; index < 3 ; index++){
			g.drawRect(MARGIN+index, MARGIN+index, width-(MARGIN*2+index*2), height-(MARGIN*2+index*2));
		}
		
		g.drawLine(width/2, MARGIN, width/2, height-MARGIN);
		
		g.drawOval(width/2-100, height/2-100, 200, 200);
		g.fillOval(width/2-10, height/2-10, MARGIN, MARGIN);
		
		g.drawRect(MARGIN, height/2-100, 100, 200);
		g.drawRect(width - 120, height/2-100, 100, 200);
		
		leftGate.setSize(MARGIN, height/2-50, MARGIN, 100);
		rightGate.setSize(width - 40, height/2-50, MARGIN, 100);
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("times new roman",Font.BOLD,25));
		g.drawString("Home team: "+away, width/2+50, MARGIN);
		g.drawString("Away team "+home, width/4, MARGIN);
		
		leftGate.drawMe(g);
		rightGate.drawMe(g);
	}
	
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	public int getMargin(){
		return MARGIN;
	}
	public Rectangle getBorders(){
		return new Rectangle(MARGIN, MARGIN, width-MARGIN*2, height-MARGIN*2);
	}
	public Rectangle getLeftGate(){
		return new Rectangle(leftGate.left, leftGate.top, leftGate.width, leftGate.height);
	}
	public Rectangle getRightGate(){
		return new Rectangle(rightGate.left+MARGIN, rightGate.top, rightGate.width, rightGate.height);
	}
}
