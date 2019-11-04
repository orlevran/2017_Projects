package HomeWork003;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SoccerField sf;
	private Ball b;
	private Timer t;
	private Team homeTeam, awayTeam;
	private boolean hasStarted;
	private int homeScore, awayScore, controlledIndex;
	
	public DrawPanel(){
		sf = new SoccerField();
		b = new Ball();
		t = new Timer();
		MyTask task = new MyTask();
		t.schedule(task, 1000, 40);
		hasStarted = false;
		controlledIndex = 4;
		
		homeTeam = new Team(Color.GREEN, 1);
		awayTeam = new Team(Color.BLUE, 0);
		homeScore = 0;
		awayScore = 0;
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		int height = getHeight();
		int width = getWidth();
		sf.setSize(width, height);
		
		if(!hasStarted){
			homeTeam.reArrange(width, height);
			awayTeam.reArrange(width, height);
			b.goToInitalPosition(width, height);
		}
		
		sf.drawMe(g, homeScore, awayScore);
		homeTeam.drawMe(g);
		awayTeam.drawMe(g);
		b.drawMe(g);
		
	}
	
	public void checkIfGoal(){
		Rectangle ballRec = new Rectangle(b.getX(), b.getY(), b.RADIUS*2, b.RADIUS*2);
		if(ballRec.intersects(sf.getLeftGate())){
			hasStarted = false;
			awayScore++;
		}
		else if(ballRec.intersects(sf.getRightGate())&&ballRec.getMaxX() > SoccerField.getWidth() - sf.getMargin()*2){
			hasStarted = false;
			homeScore++;
		}
	}
	
	private class MyTask extends TimerTask{
		public void run(){
				hasStarted = true;
				b.cheackAngle(sf);
				homeTeam.move(b,controlledIndex, false);
				awayTeam.move(b,controlledIndex, true);
				b.move();
				b.checkPlayers(homeTeam.players, 1);
				b.checkPlayers(awayTeam.players, 0);
				checkIfGoal();
				
				repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			controlledIndex++;
			if(controlledIndex > 4){
				controlledIndex = 0;
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			homeTeam.getOrder(b, e, controlledIndex, true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			homeTeam.getOrder(b, e, controlledIndex, true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			homeTeam.getOrder(b, e, controlledIndex, true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			homeTeam.getOrder(b, e, controlledIndex, true);
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
