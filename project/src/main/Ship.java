package main;

import java.awt.Image;
import java.util.ArrayList;

/* Sprite Ŭ������ ��ӹ��� �ڽ�Ŭ���� Ship�� ���� */
public class Ship extends Sprite {
	private GalagaGame game;
	private int B_W, B_H;
	
	/* ������-�θ�Ŭ���� �����ڸ� ȣ�������ν� �̹����ε���� ��ġ�� �� */
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
	/* private ���� missile�� ���� ������ �޼ҵ� */
	/*public ArrayList<Missile> getMissiles() {
		return missiles;
	}*/
	
	/* �̻��Ϲ߻� �޼ҵ�, missile ��ü�� �ʱ���ġ �� y������ �����Ͽ� ����(����Ϳ��� �����������θ� �̵��ϹǷ�) */
	/*public void launchMissile() {
		Missile tmpMissile = new Missile("missile.png");
		tmpMissile.setX(this.getX()+(this.getImage().getWidth(null)-tmpMissile.getImage().getWidth(null))/2);	// �̻����� ���ּ��� ���߾ӿ��� ��������
		tmpMissile.setY(this.getY());
		tmpMissile.setDy(-5);
		this.missiles.add(tmpMissile);
	}*/
	
	/* ȭ���� ��� �̻��� ����� */
	/*public void removeMissile() {
		for (int i=0;i<missiles.size();i++) {	// ArrayList ����� �����ؾ��Ҷ��� foreach�� ����� �� ����
			if (missiles.get(i).getY()+missiles.get(i).getImage().getHeight(null)<0) {
				missiles.remove(i);
			}
		}
	}*/
}
