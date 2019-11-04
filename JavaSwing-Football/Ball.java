package HomeWork003;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball implements Moveable {
	public final int RADIUS = 10;
	private int ballX, ballY;
	private double speed, alpha;
	
	public Ball(){
		speed = 10;
		alpha = 2*Math.PI*Math.random();
	}
	
	@Override
	public void goToInitalPosition(int width, int height) {
		ballX = width/2;
		ballY = height/2;
	}

	@Override
	public void move() {
			double directionX = directionX();
			double directionY = directionY();
			
		ballX = (int)(ballX + speed * directionX);
		ballY = (int)(ballY + speed * directionY);
	}
	
	public void drawMe(Graphics g){
		Graphics2D g2d = (Graphics2D)(g);
		
		BufferedImage bi = null;
		
		try{
			bi = ImageIO.read(new File("ball.jpg"));
		}
		
		catch(IOException ex){
			System.out.println("Error");
		}
		
		TexturePaint tp = new TexturePaint(bi, new Rectangle(ballX+10, ballY, 50, 50));
		
		g2d.setPaint(tp);
		g2d.fillOval(ballX - RADIUS, ballY - RADIUS, RADIUS*2, RADIUS*2);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(ballX - RADIUS, ballY - RADIUS, RADIUS*2, RADIUS*2);
		
		g2d.dispose();
	}
	
	public int getX(){
		return ballX;
	}
	public int getY(){
		return ballY;
	}
	public void setLocation(int x, int y){
		ballX = x;
		ballY = y;
	}
	public double directionX(){
		return Math.cos(alpha);
	}
	public double directionY(){
		return Math.sin(alpha);
	}
	public void setAlpha(double a){
		alpha = a;
	}
	
	public void cheackAngle(SoccerField sf){
		double dirX = directionX();
		double dirY = directionY();
		
		Rectangle r = sf.getBorders();
		
		if(dirX > 0){
			if(ballX+RADIUS >= r.getMaxX()){
				alpha = Math.PI-alpha;
			}
		}
		else{
			if(ballX-RADIUS <= r.getMinX()){
				alpha = Math.PI-alpha;
			}
		}
		
		if(dirY > 0){
			if(ballY + RADIUS > r.getMaxY()){
				alpha = -alpha;
			}
		}
		else{
			if(ballY - RADIUS < r.getMinY()){
				alpha = -alpha;
			}
		}
	}
	public void checkPlayers(Player[] p, int side){
		Rectangle br = new Rectangle(ballX, ballY, RADIUS, RADIUS);
		for(int index = 0 ; index < p.length ; index++){
			Rectangle pr = new Rectangle((int)(p[index].getX()), (int)(p[index].getY()), RADIUS*2, RADIUS*2);
			if(pr.intersects(br)){
				if(side==0){
					if(alpha <= 3*Math.PI/2 && alpha >= Math.PI/2){
						alpha = Math.PI-alpha;
					}
					else{
						alpha = -Math.PI/2 + Math.random()*Math.PI;
					}
				}
				else if(side==1){
					if(alpha >= -Math.PI/2 && alpha <= Math.PI){
						alpha = Math.PI-alpha;
					}
					else{
						alpha = -Math.PI/2 + Math.random()*Math.PI;
					}
				}
			}
		}
	}
}
