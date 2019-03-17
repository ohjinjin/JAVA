package main;

import java.awt.Image;

/* Sprite 클래스를 상속받은 자식클래스 Ship를 정의 */
public class Ship extends Sprite {
	private Sprite missile;	// field로 missile이라는 sprite 클래스의 인스턴스를 갖게됨
	
	/* 생성자-부모클래스 생성자를 호출함으로써 이미지로드등을 마치게 됨, 이후 missile이라는 참조변수에 null값으로 초기화 */
	public Ship(String filename) {
		super(filename);
		this.missile = null;//new Sprite("missile.png");
	}
	
	/* private 변수 missile에 대한 접근자 메소드 */
	public Sprite getMissile() {
		return missile;
	}
	
	/* 미사일발사 메소드, missile 객체의 초기위치 및 y증분을 변경하여 설정(모니터에서 수직방향으로만 이동하므로) */
	public void launchMissile() {
		this.missile = new Sprite("missile.png");
		this.missile.setX(this.getX()+(this.getImage().getWidth(null)-this.missile.getImage().getWidth(null))/2);	// 미사일이 우주선의 정중앙에서 나오도록
		this.missile.setY(this.getY());
		this.missile.setDy(-5);
	}
}
