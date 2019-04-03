package main;

import java.awt.Image;
import java.util.ArrayList;

/* Sprite Ŭ������ ��ӹ��� �ڽ�Ŭ���� Ship�� ���� */
public class Ship extends Sprite {
	private GalagaGame game;	// ���� ��ü�� ���� ����
	
	/* ������-�θ�Ŭ���� �����ڸ� ȣ�������ν� �̹����ε���� ��ġ�� �� */
	public Ship(GalagaGame g, String filename) {//,int x, int y) {
		//super(filename, x, y); �������� ũ�� �� ��ġ�� �����ϱ� ���� �ش� �����ڷθ� �����
		super(filename);
		this.game = g;
	}
	
	/* �θ� Ŭ������ move �޼ҵ带 ��ġ */
	@Override
	public void move() {
		// ȭ���� ������ϸ� ������� �ʰ���
		if (((this.getDx()<0)&&(this.getX() < 0))||((this.getDx()>0)&&(this.getX()+this.getImage().getWidth(null)>this.game.getB_W()))) {
			return;
		}
		super.move();
	}
	
	/* �θ� Ŭ������ �浹ó�� �޼ҵ带 ��ġ */
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Enemy) {	// �������� �浹��
			game.endGame();	// ���� ����
		}
	}
}
