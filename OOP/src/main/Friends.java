package main;

public class Friends extends Sprite {
	private GalagaGame game;	// ���� ��ü�� ������ ����
	
	/* ������ */
	public Friends(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
		// TODO Auto-generated constructor stub
	}
	
	/* �θ� Ŭ������ move �޼ҵ带 ��ġ */
	@Override
	public void move() {
		super.move();
		// ȭ���� ������ ����� �Ǹ� 
		if (this.getY()+this.getImage().getHeight(null)<0) {
			game.removeSprite(this);	// ����������
		}
	}
	
	/* �θ� Ŭ������ �浹 ó�� �޼ҵ� ��ġ */
	@Override
	public void handleCollision(Sprite other) {
		// ������ �ε����ԵǸ�
		if (other instanceof Enemy) {
			game.removeSprite(other);	// ġƮŰ�� ���ڽ��� �״�� ���, ������ �׵���
		}
	}
}
