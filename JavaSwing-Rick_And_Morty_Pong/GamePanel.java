package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int SPEED = 20;
	private boolean started, toHit, computerStart, computerSloveBug;
	private Board bo;
	private Ball ba;
	private Timer t;
	private Player leftP, rightP;
	private Eye rickLeft, rickRight, mortyLeft, mortyRight;
	private int playerScore, computerScore, counter;
	private int[] rickTriX, rickTriY, mortyTriX, mortyTriY, coverRickX, coverRickY, coverMortyX, coverMortyY;
	
	
	public GamePanel(){
		started = false;
		toHit = true;
		computerStart = false;
		computerSloveBug = false;
		
		playerScore = 0;
		computerScore = 0;
		counter = 0;
		
		bo = new Board();
		ba = new Ball();
		
		leftP = new Player();
		rightP = new Player();
		
		rickLeft = new Eye(190, 263, 5, 10);
		rickRight = new Eye(213, 263, 5, 10);
		mortyLeft = new Eye(620, 272, 9, 18);
		mortyRight = new Eye(664, 266, 8, 16);
		
		rickTriX = new int[3];
		rickTriY = new int[3];
		mortyTriX = new int[3];
		mortyTriY = new int[3];
		coverRickX = new int[4];
		coverRickY = new int[4];
		coverMortyX = new int[4];
		coverMortyY = new int[4];
		
		rickTriX[0] = 250;
		rickTriX[1] = 240;
		rickTriX[2] = 270;
		
		rickTriY[0] = 185;
		rickTriY[1] = 230;
		rickTriY[2] = 200;
		
		mortyTriX[0] = 510;
		mortyTriX[1] = 530;
		mortyTriX[2] = 570;
		
		mortyTriY[0] = 400;
		mortyTriY[1] = 420;
		mortyTriY[2] = 360;
		
		coverRickX[0] = 250;
		coverRickX[1] = 260;
		coverRickX[2] = 272;
		coverRickX[3] = 250;
		
		coverRickY[0] = 185;
		coverRickY[1] = 175;
		coverRickY[2] = 200;
		coverRickY[3] = 200;
		
		coverMortyX[0] = 510;
		coverMortyX[1] = 550;
		coverMortyX[2] = 530;
		coverMortyX[3] = 510;
		
		coverMortyY[0] = 400;
		coverMortyY[1] = 380;
		coverMortyY[2] = 420;
		coverMortyY[3] = 420;
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		ba.setDirection(initialDirection(true));
		
		t = new Timer(SPEED, this);
		t.start();
	}
	
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.WHITE);
		
		int width = getWidth();
		int height = getHeight();
		
		Graphics2D rick = (Graphics2D)(g);
		Graphics2D morty = (Graphics2D)(g);
		
		BufferedImage bi1 = null, bi2 = null;
		
		try{
			bi1 = ImageIO.read(new File("rick.jpg"));
			bi2 = ImageIO.read(new File("morty.jpg"));
		}
		
		catch(IOException ex){
			System.out.println("file wasn't found");
		}
		
		TexturePaint tp1 = new TexturePaint(bi1, new Rectangle(50, 216, 300, 168));
		TexturePaint tp2 = new TexturePaint(bi2, new Rectangle(530, 189, 200, 222));
		
		rick.setPaint(tp1);
		rick.fillRect(50, 216, 300, 168);
		
		morty.setPaint(tp2);
		morty.fillRect(530, 189, 200, 222);
		
		rickLeft.drawMe(g);
		rickRight.drawMe(g);
		mortyLeft.drawMe(g);
		mortyRight.drawMe(g);
		
		bo.setSize(width, height);
		
		g.setColor(Color.BLACK);
		g.drawRoundRect(250, 80, 250, 120, 40, 40);
		g.drawPolygon(rickTriX, rickTriY, 3);
		g.drawRoundRect(330, 400, 200, 100, 40, 40);
		g.drawPolygon(mortyTriX, mortyTriY, 3);
		
		g.setFont(new Font("times new roman", Font.ITALIC, 23));
		g.drawString("Looks like the score", 290, 120);
		g.drawString("is: "+playerScore+" : "+computerScore+" , Morty", 310, 160);
		g.setFont(new Font("times new roman", Font.ITALIC, 30));
		g.drawString("Aw jeez, Rick", 350, 450);
		
		
		g.setColor(Color.WHITE);
		g.fillPolygon(coverMortyX, coverMortyY, 4);
		g.fillPolygon(coverRickX, coverRickY, 4);
		
		if(!started){
			ba.setLocation(32, height/2);
			ba.setDirection(initialDirection(true));
			leftP.setLocation(bo.MARGIN+2, height/2-leftP.HEIGHT/2);
			rightP.setLocation(bo.getWidth()+9, height/2-rightP.HEIGHT/2);
		}
		
		else if(computerStart){
			ba.setLocation(745, height/2);
			ba.setDirection(initialDirection(false));
			leftP.setLocation(bo.MARGIN+2, height/2-leftP.HEIGHT/2);
			rightP.setLocation(bo.getWidth()+9, height/2-rightP.HEIGHT/2);
		}
		
		bo.drawMe(g);
		ba.drawMe(g);
		leftP.drawMe(g);
		rightP.drawMe(g);
		
		rick.dispose();
		morty.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(started&&!computerStart){
			ba.movement();
			rightP.movement(ba, bo.MARGIN, bo.MARGIN+bo.getHeight());
			checkLimits();
			rickLeft.setBallLocation((int)(ba.getBall().getCenterX()), (int)(ba.getBall().getCenterY()));
			rickRight.setBallLocation((int)(ba.getBall().getCenterX()), (int)(ba.getBall().getCenterY()));
			mortyLeft.setBallLocation((int)(ba.getBall().getCenterX()), (int)(ba.getBall().getCenterY()));
			mortyRight.setBallLocation((int)(ba.getBall().getCenterX()), (int)(ba.getBall().getCenterY()));
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if(!started){
				started = true;
			}
			if(computerStart){
				computerStart = false;
				computerSloveBug = true;
			}
		}
		else if(e.getKeyChar()==KeyEvent.VK_R){
			started = false;
			ba.setDirection(Math.random()*((Math.PI/2)+1)+(-Math.PI/2));
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			if(started){
				leftP.moveUp(bo.MARGIN);
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			if(started){
				leftP.moveDown(bo.MARGIN+bo.getHeight());
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public double initialDirection(boolean b){
		boolean ok = false;
		double d;
		
		if(b){ //player initial
			do{
				d = (Math.random()*((Math.PI/3)+1)+(-Math.PI/3));
				if(d <= -Math.PI/6 || d >= Math.PI/6)
					ok = true;
			}while(!ok);
		}
		else{ //computer initial
			counter = 0;
			do{
				d = (Math.random()*((4*Math.PI/3)+1)+(2*Math.PI/3));
				if(d <= 5*Math.PI/6 || d >= 7*Math.PI/6)
					ok = true;
			}while(!ok);
		}
		return d;
	}
	
	public void checkLimits() {
		Rectangle[] r = bo.getWalls();
		Rectangle ball = ba.getBall();
		Rectangle lp = leftP.getPlayer();
		Rectangle rp = rightP.getPlayer();

		double alpha = ba.getDirection();

		boolean hitSomething = false;

		for (int index = 0; index < r.length && !hitSomething; index++) {
			if (ball.intersects(r[index])) {
				if(toHit){
					switch (index) {
					case 0:
						started = false;
						computerScore++;
						computerSloveBug = false;
						break;
					case 1:
						ba.setDirection(-alpha);
						computerSloveBug = false;
						break;
					case 2:
						computerStart = true;
						if(counter > 10 || !computerSloveBug){
							playerScore++;
						}
						break;
					case 3:
						ba.setDirection(2 * Math.PI - alpha);
						computerSloveBug = false;
						break;
					}
					toHit = false;
					hitSomething = true;
				}
			}
		}
		
		if(!hitSomething){
			if(ball.intersects(lp)){
				if(toHit){
					if(ball.getMinX() < lp.getMaxX()){
						int hitLocation = (int) (ball.getCenterY() - leftP.getTop());
						ba.setDirection(Math.PI/3-hitLocation*(Math.PI/180));
						toHit = false;
					}
					else if(ball.getMaxY() > lp.getMinY() || ball.getMinY() < lp.getMaxY()){
						ba.setDirection(alpha+Math.PI);
						toHit = false;
					}
				}
				hitSomething = true;
			}
			else if(ball.intersects(rp)){
				if(toHit){
					if(ball.getMaxX() > rp.getMinX()){
						int hitLocation = (int) (ball.getCenterY() - rightP.getTop());
						ba.setDirection(2*Math.PI/3 + hitLocation*(Math.PI/180));
						toHit = false;
					}
					else if(ball.getMaxY() > rp.getMinY() || ball.getMinY() < rp.getMaxY()){
						ba.setDirection(alpha+Math.PI);
						toHit = false;
					}
				}
				hitSomething = true;
			}
		}
		
		if(!hitSomething){
			toHit = true;
		}
		if(computerSloveBug){
			counter++;
		}
	}
}
