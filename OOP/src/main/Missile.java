package main;

public class Missile extends Sprite {
	private GalagaGame game;	// ���� ��ü�� ���� ����
	
	/* ������ */
	public Missile(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
		// TODO Auto-generated constructor stub
	}
	
	/* �θ� Ŭ������ move �޼ҵ带 ��ġ */
	@Override
	public void move() {
		super.move();
		// �̻����� ȭ���� ����� ����������
		if (this.getY()+this.getImage().getHeight(null)<0) {
			game.removeSprite(this);
		}
	}
	
	/* �θ� Ŭ������ �浹 ó�� �޼ҵ� ��ġ */
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Enemy) {	// ������ü���� �浹 ��
			game.removeSprite(this);	// ���� �װ�
			game.removeSprite(other);	// ���� �׵���
		}
	}
}
