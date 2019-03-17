package main;

import java.awt.Image;

/* Sprite Ŭ������ ��ӹ��� �ڽ�Ŭ���� Ship�� ���� */
public class Ship extends Sprite {
	private Sprite missile;	// field�� missile�̶�� sprite Ŭ������ �ν��Ͻ��� ���Ե�
	
	/* ������-�θ�Ŭ���� �����ڸ� ȣ�������ν� �̹����ε���� ��ġ�� ��, ���� missile�̶�� ���������� null������ �ʱ�ȭ */
	public Ship(String filename) {
		super(filename);
		this.missile = null;//new Sprite("missile.png");
	}
	
	/* private ���� missile�� ���� ������ �޼ҵ� */
	public Sprite getMissile() {
		return missile;
	}
	
	/* �̻��Ϲ߻� �޼ҵ�, missile ��ü�� �ʱ���ġ �� y������ �����Ͽ� ����(����Ϳ��� �����������θ� �̵��ϹǷ�) */
	public void launchMissile() {
		this.missile = new Sprite("missile.png");
		this.missile.setX(this.getX()+(this.getImage().getWidth(null)-this.missile.getImage().getWidth(null))/2);	// �̻����� ���ּ��� ���߾ӿ��� ��������
		this.missile.setY(this.getY());
		this.missile.setDy(-5);
	}
}
