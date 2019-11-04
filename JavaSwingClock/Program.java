package HomeWork001;

import javax.swing.JFrame;

public class Program {

	public static void main(String[] args) {
		int minute = (int)(Math.random()*60);
		int hour = (int)(Math.random()*24);
		JFrame f = new JFrame("Ticking away the moments that make up a dull day");
		DrawClock dc = new DrawClock(minute, hour);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(dc);
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		System.out.println(hour+":"+minute);
	}

}
