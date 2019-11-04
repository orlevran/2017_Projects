package HomeWork001;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class DrawClock extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int minute, hour;
	final double SIX_DEGREES = Math.PI/30;
	final double THIRTY_DEGREES = Math.PI/6;
	final double SIXTY_DEGREES = Math.PI/3;
	final double NINETY_DEGREES = Math.PI/2;
	final double HUNDRED_TWENTY_DEGREES = 2*Math.PI/3;
	final double SCALES_BEGIN = 0.85;
	final double SCALES_END = 0.9;
	final double NUMBERS_LOCATION = 0.95;
	final double MINUTE_SIZE1 = 0.8;
	final double MINUTE_SIZE2 = 0.3;
	final double HOUR_SIZE1 = 0.6;
	final double HOUR_SIZE2 = 0.225;
	private Timer t = new Timer();
	
	public DrawClock(int minute, int hour){
		setMinute(minute);
		setHour(hour);
		MyTimerTask task = new MyTimerTask();
		t.schedule(task, 1, 1000);
	}
	
	protected int getMinute(){
		return this.minute;
	}
	protected int getHour(){
		return this.hour;
	}
	protected void setMinute(int minute){
		if(minute == 60){
			this.minute = 0;
			setHour(getHour()+1);
		}
		else if(minute >= 0 && minute < 60)
			this.minute = minute;
		else
			this.minute = 0;
	}
	protected void setHour(int hour){
		if(hour >= 0 && hour < 12)
			this.hour = hour;
		else if(hour >= 12 && hour < 24)
			this.hour = hour-12;
		else
			this.hour = 0;
	}
	
	protected void paintComponent(Graphics g){
		setBackground(new Color(190,17,20));
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		int radius = Math.min(getWidth(), getHeight())/2;
		super.paintComponent(g);
		g.setColor(new Color(250,250,170));
		g.fillOval(centerX - radius, centerY - radius, radius*2, radius*2); //the biggest circle of the clock
		g.setColor(new Color(70, 70, 240));
		g.fillOval((int)(centerX - radius/1.11),(int)(centerY - radius/1.11), (int)(radius*1.81), (int)(radius*1.81)); //the ocean
		g.setColor(new Color(200,200,255));
		g.fillArc((int)(centerX - radius/1.11), (int)(centerY - radius/1.11), (int)(radius*1.81), (int)(radius*1.81), 0, 180); //the sky
		g.setColor(new Color(250,250,170));
		g.setColor(Color.YELLOW);
		g.fillOval(centerX + radius/2, centerY - radius/2, radius/5, radius/5); //the sun
		
		int[] xPointsMinute = {centerX, (int)(centerX+MINUTE_SIZE2*radius*Math.cos(SIXTY_DEGREES-getMinute()*(SIX_DEGREES))), (int)(centerX+MINUTE_SIZE1*radius*Math.cos(NINETY_DEGREES-getMinute()*(SIX_DEGREES))), (int)(centerX+MINUTE_SIZE2*radius*Math.cos(HUNDRED_TWENTY_DEGREES-getMinute()*(SIX_DEGREES)))};
		int[] yPointsMinute = {centerY, (int)(centerY-MINUTE_SIZE2*radius*Math.sin(SIXTY_DEGREES-getMinute()*(SIX_DEGREES))), (int)(centerY-MINUTE_SIZE1*radius*Math.sin(NINETY_DEGREES-getMinute()*(SIX_DEGREES))), (int)(centerY-MINUTE_SIZE2*radius*Math.sin(HUNDRED_TWENTY_DEGREES-getMinute()*(SIX_DEGREES)))};
		int[] xPointsHour = {centerX, (int)(centerX+HOUR_SIZE2*radius*Math.cos(SIXTY_DEGREES-getHour()*(THIRTY_DEGREES))), (int)(centerX+HOUR_SIZE1*radius*Math.cos(NINETY_DEGREES-getHour()*(THIRTY_DEGREES))), (int)(centerX+HOUR_SIZE2*radius*Math.cos(HUNDRED_TWENTY_DEGREES-getHour()*(THIRTY_DEGREES)))};
		int[] yPointsHour = {centerY, (int)(centerY-HOUR_SIZE2*radius*Math.sin(SIXTY_DEGREES-getHour()*(THIRTY_DEGREES))), (int)(centerY-HOUR_SIZE1*radius*Math.sin(NINETY_DEGREES-getHour()*(THIRTY_DEGREES))), (int)(centerY-HOUR_SIZE2*radius*Math.sin(HUNDRED_TWENTY_DEGREES-getHour()*(THIRTY_DEGREES)))};
		
		g.setColor(new Color(220,190,170));
		g.fillPolygon(xPointsMinute, yPointsMinute, 4); //drawing the hand of the minutes
		g.setColor(new Color(190, 220, 170));
		g.fillPolygon(xPointsHour, yPointsHour, 4); //drawing the hand of the hours
		
		g.setColor(Color.BLACK);
		g.drawLine(centerX, centerY, (int)(centerX+MINUTE_SIZE1*radius*Math.cos(NINETY_DEGREES-getMinute()*(SIX_DEGREES))), (int)(centerY-MINUTE_SIZE1*radius*Math.sin(NINETY_DEGREES-getMinute()*(SIX_DEGREES)))); //drawing the line of the hand of the minutes
		g.drawPolygon(xPointsMinute, yPointsMinute, 4); //drawing the polygon of the hand of the minutes
		g.drawLine(centerX, centerY, (int)(centerX+HOUR_SIZE1*radius*Math.cos(NINETY_DEGREES-getHour()*(THIRTY_DEGREES))), (int)(centerY-HOUR_SIZE1*radius*Math.sin(NINETY_DEGREES-getHour()*(THIRTY_DEGREES)))); //drawing the line of the hand of the hours
		g.drawPolygon(xPointsHour, yPointsHour, 4); //drawing the polygon of the hand of the hours
		
		
		
		for(int index = 0 ; index < 60 ; index+=5){ //drawing the scales
			g.drawLine((int)(centerX + SCALES_BEGIN*radius*Math.cos(NINETY_DEGREES-index*SIX_DEGREES)), (int)(centerY - SCALES_BEGIN*radius*Math.sin(NINETY_DEGREES-index*SIX_DEGREES)), (int)(centerX + SCALES_END*radius*Math.cos(NINETY_DEGREES-index*SIX_DEGREES)), (int)(centerY - SCALES_END*radius*Math.sin(NINETY_DEGREES-index*SIX_DEGREES)));
		}
		
		for(int index = 1 ; index <= 12 ; index++){ //placing the numbers of the scales
			g.drawString(""+index,(int)(centerX + NUMBERS_LOCATION*radius*Math.cos(NINETY_DEGREES-index*THIRTY_DEGREES)),(int)(centerY - NUMBERS_LOCATION*radius*Math.sin(NINETY_DEGREES-index*THIRTY_DEGREES)));
		}
		
		g.drawOval(centerX - radius/12, centerY - radius/12 , radius/6, radius/6); //the small circle in the middle of the clock
		g.setColor(new Color(250,250,170));
		g.fillOval(centerX - radius/12, centerY - radius/12 , radius/6, radius/6);
		
	}
	
	private class MyTimerTask extends TimerTask{
		public void run(){
			setMinute(getMinute()+1);
			repaint();
		}
	}
}
