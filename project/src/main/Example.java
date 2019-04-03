package main;

import javax.swing.JFrame;

public class Example extends JFrame {
	
	public Example() {
		initUI();
	}
	
	private void initUI() {
		add(GalagaGame.getGalagaGameObject());
		pack();
		setTitle("GALAGA WARS");
		setLocationRelativeTo(null);
//		setVisible(true);
	}
	
	public static void main(String[] args) {
		Example ex = new Example();
		ex.setVisible(true);
	}
}
