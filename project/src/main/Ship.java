package main;

import java.awt.Image;
import java.util.ArrayList;

/* Sprite 클래스를 상속받은 자식클래스 Ship를 정의 */
public class Ship extends Sprite {
	private GalagaGame game;
	private int B_W, B_H;
	
	/* 생성자-부모클래스 생성자를 호출함으로써 이미지로드등을 마치게 됨 */
	public Ship(GalagaGame g, String filename) {//,int x, int y) {
		//super(filename, x, y);
		super(filename);
		this.game = g;
	}
	
	@Override
	public void move() {
		if (((this.getDx()<0)&&(this.getX() <10))||((this.getDx()>0)&&(this.getX()>this.game.getB_W()))) {
			return;
		}
		super.move();
	}
	
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Enemy) {
			game.endGame();
		}
	}
	/* private 변수 missile에 대한 접근자 메소드 */
	/*public ArrayList<Missile> getMissiles() {
		return missiles;
	}*/
	
	/* 미사일발사 메소드, missile 객체의 초기위치 및 y증분을 변경하여 설정(모니터에서 수직방향으로만 이동하므로) */
	/*public void launchMissile() {
		Missile tmpMissile = new Missile("missile.png");
		tmpMissile.setX(this.getX()+(this.getImage().getWidth(null)-tmpMissile.getImage().getWidth(null))/2);	// 미사일이 우주선의 정중앙에서 나오도록
		tmpMissile.setY(this.getY());
		tmpMissile.setDy(-5);
		this.missiles.add(tmpMissile);
	}*/
	
	/* 화면을 벗어난 미사일 지우기 */
	/*public void removeMissile() {
		for (int i=0;i<missiles.size();i++) {	// ArrayList 목록을 수정해야할때는 foreach를 사용할 수 없음
			if (missiles.get(i).getY()+missiles.get(i).getImage().getHeight(null)<0) {
				missiles.remove(i);
			}
		}
	}*/
}
