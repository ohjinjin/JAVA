package main;

import javax.swing.JFrame;

public class Example extends JFrame {
	
	public Example() {
		initUI();
	}
	
	private void initUI() {
		add(new Board());
		pack();
		setTitle("Rocket Anima");
		setLocationRelativeTo(null);
//		setVisible(true);
	}
	
	public static void main(String[] args) {
		Example ex = new Example();
		ex.setVisible(true);
	}
}
