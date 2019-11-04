package HomeWork003;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		DrawPanel dp = new DrawPanel();
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1040, 740);
		jf.setLocation(100, 0);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setTitle("UEFA Champions Leauge final");
		jf.add(dp);
		
	}

}
