package main;

import java.awt.Image;
import java.util.ArrayList;

/* Sprite 클래스를 상속받은 자식클래스 Ship를 정의 */
public class Ship extends Sprite {
	private GalagaGame game;	// 게임 객체를 담을 변수
	
	/* 생성자-부모클래스 생성자를 호출함으로써 이미지로드등을 마치게 됨 */
	public Ship(GalagaGame g, String filename) {//,int x, int y) {
		//super(filename, x, y); 동적으로 크기 및 위치를 조절하기 위해 해당 생성자로만 사용함
		super(filename);
		this.game = g;
	}
	
	/* 부모 클래스의 move 메소드를 대치 */
	@Override
	public void move() {
		// 화면을 벗어나려하면 벗어나지지 않게함
		if (((this.getDx()<0)&&(this.getX() < 0))||((this.getDx()>0)&&(this.getX()+this.getImage().getWidth(null)>this.game.getB_W()))) {
			return;
		}
		super.move();
	}
	
	/* 부모 클래스의 충돌처리 메소드를 대치 */
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Enemy) {	// 적군과의 충돌시
			game.endGame();	// 게임 오버
		}
	}
}
