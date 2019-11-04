package Pong;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		GamePanel gp = new GamePanel();
		
		jf.add(gp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Pong");
		jf.setSize(800, 600);
		jf.setLocation(400, 100);
		jf.setVisible(true);
		jf.setResizable(false);
		
	}

}
