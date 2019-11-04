package HomeWork003;

import java.awt.Color;

public class GoalKeeper extends Player {
	public GoalKeeper(int x, int y, double angle, Color c, int s){
		super(x,y,angle,c,s);
	}
	public GoalKeeper(Color c, int s){
		super(c,s);
		playerSpeed = SPEED+1;
	}
	@Override
	public void goToInitalPosition(int width, int height) {
		centerX = MARGIN+RADIUS+side*(width-MARGIN*2-RADIUS*2);
		centerY = height/2;
	}

	@Override
	public void move() {
			double directionX = Math.cos(alpha);
			double directionY = Math.sin(alpha);
			if(centerY + RADIUS > SoccerField.getHeight()/2+100){
				centerY = SoccerField.getHeight()/2+100 - RADIUS;
			}
			else if(centerY - RADIUS < SoccerField.getHeight()/2-100){
				centerY = SoccerField.getHeight()/2-100 + RADIUS;
			}
			
			centerX = (int)(centerX+playerSpeed*directionX);
			centerY = (int)(centerY+playerSpeed*directionY);
	}

	@Override
	public void decideWhatToDo(Ball b, boolean control) {
		if(!control){
			if(b.getY()>centerY){
				alpha = Math.PI/2;
			}
			else{
				alpha = -Math.PI/2;
			}
		}
	}
}
