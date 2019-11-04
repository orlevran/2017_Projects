package HomeWork003;

import java.awt.Color;

public class BottomDefender extends Player {
	public BottomDefender(int x, int y, double angle, Color c, int s){
		super(x,y,angle,c,s);
	}
	public BottomDefender(Color c, int s){
		super(c,s);
		playerSpeed = SPEED;
	}
	@Override
	public void goToInitalPosition(int width, int height) {
		centerX = MARGIN+(width-MARGIN*2)/5+side*((width-MARGIN*2)*0.6);
		centerY = MARGIN+3*((height-MARGIN*2)/4);
	}

	@Override
	public void move() {
			double directionX = Math.cos(alpha);
			double directionY = Math.sin(alpha);
			if(centerY - RADIUS < SoccerField.getHeight()/2){
				centerY = SoccerField.getHeight()/2 + RADIUS;
			}
			centerX = (int)(centerX+playerSpeed*directionX);
			centerY = (int)(centerY+playerSpeed*directionY);
	}

	@Override
	public void decideWhatToDo(Ball b, boolean control) {
		if(!control){
			if(b.getX()>SoccerField.getWidth()/2&&side==0){
				if(b.getY()>centerY){
					alpha = Math.PI/2;
				}
				else{
					alpha = -Math.PI/2;
				}
			}
			else if(b.getX()<SoccerField.getWidth()/2&&side==1){
				if(b.getY()>centerY){
					alpha = Math.PI/2;
				}
				else{
					alpha = -Math.PI/2;
				}
			}
			else{
				double distY = b.getY()-centerY;
				double distX = b.getX()-centerX;
				
				alpha = Math.atan(distY/distX);
				
				if(distX < 0){
					alpha = alpha + Math.PI;
				}
			}
		}
	}
}
