package main;

import javax.swing.JFrame;

/* 메인 메소드가 정의된 테스트 클래스 */
public class Example extends JFrame {
	
	/* 생성자 */
	public Example() {
		initUI();
	}
	
	private void initUI() {
		add(GalagaGame.getGalagaGameObject());	// 싱글톤 갤러그게임 객체 생성하여 윈도우에 추가-한 윈도우당 하나의 게임객체만 생성되도록
		pack();	// 패킹
		setTitle("GALAGA WARS");	// 윈도우창의 제목을 결정
		setLocationRelativeTo(null);	// 전체 윈도우의 가운데에 해당 윈도우가 위치하도록
	}

	/* 메인 메소드 정의 */
	public static void main(String[] args) {
		Example ex = new Example();	// example 객체 생성
		ex.setVisible(true);	// example 객체를 가시화
	}
}
