package HomeWork003;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Team {
	private final int NUM_OF_PLAYERS = 5;
	protected Player[] players;
	private Color teamColor;
	private int side;
	
	public Team(){
		this(Color.WHITE,0);
	}
	
	public Team(Color c, int s){
		teamColor = c;
		side = s;
		players = new Player[NUM_OF_PLAYERS];
		players[0] = new GoalKeeper(teamColor, side);
		players[1] = new TopDefender(teamColor, side);
		players[2] = new BottomDefender(teamColor, side);
		players[3] = new TopForward(teamColor, side);
		players[4] = new BottomForward(teamColor, side);
	}
	
	public void reArrange( int width, int height){
		for(int index = 0 ; index < players.length ; index++){
			players[index].goToInitalPosition(width, height);
		}
	}
	
	public void drawMe(Graphics g){
		for(int index = 0 ; index < players.length ; index++){
			players[index].drawMe(g);
		}
	}
	
	public void move(Ball b, int controlledIndex, boolean home){
		for(int index = 0 ; index < players.length ; index++){
			if(index != controlledIndex && !home){
				players[index].decideWhatToDo(b, false);
				players[index].move();	
			}
			else if(home){
				players[index].decideWhatToDo(b, false);
				players[index].move();	
			}
		}
	}
	
	public void checkBall(Ball b){
		for(int index = 0 ; index < players.length ; index++){
			double distance = Math.sqrt(Math.pow(b.getX()-players[index].getX(), 2)+Math.pow(b.getY()-players[index].getY(), 2));
			if(distance < b.RADIUS*2){
				players[index].decideWhatToDo(b, true);
			}
		}
	}
	
	public void getOrder(Ball b, KeyEvent e, int controlledIndex, boolean home){
		for(int index = 0 ; index < players.length ; index++){
			if(e==null){
				move(b,controlledIndex, home);
			}
			else if(index==controlledIndex){
				switch(e.getKeyCode()){
				case KeyEvent.VK_UP:
					players[controlledIndex].setCenter((int)(players[controlledIndex].getX()+players[controlledIndex].getPlayerSpeed()*Math.cos(players[controlledIndex].getAlpha())),(int)(players[controlledIndex].getY()+players[controlledIndex].getPlayerSpeed()*Math.sin(players[controlledIndex].getAlpha())));
					break;
				case KeyEvent.VK_DOWN:
					players[controlledIndex].setCenter((int)(players[controlledIndex].getX()- players[controlledIndex].getPlayerSpeed()*Math.cos(players[controlledIndex].getAlpha())),(int)(players[controlledIndex].getY()- players[controlledIndex].getPlayerSpeed()*Math.sin(players[controlledIndex].getAlpha())));
					break;
				case KeyEvent.VK_LEFT:
					players[controlledIndex].setAlpha(players[controlledIndex].getAlpha()-Math.PI/10);
					break;
				case KeyEvent.VK_RIGHT:
					players[controlledIndex].setAlpha(players[controlledIndex].getAlpha()+Math.PI/10);
					break;
				}
			}
		}
	}
}
