package main;

public class Enemy extends Sprite {//
	private GalagaGame game;	// ���� ��ü�� ������ ����
	
	/* ������ */
	public Enemy(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
	}
	
	/* �θ� Ŭ������ move �޼ҵ带 ��ġ */
	@Override
	public void move() {
		
		// ȭ���� ����� �ʵ���
		if (((this.getDx()<0)&&(this.getX()<0))||((this.getDx()>0) && (this.getX()+this.getImage().getWidth(null)>this.game.getB_W()))) {
			this.setDx(this.getDx()*(-1));	// ���� ������Ű��
			this.setY(this.getY()+this.getImage().getHeight(null));	// ��ĭ ��������
		}

		// ȭ���� �ٴڿ� ���� ���
		if (this.getY()+this.getImage().getHeight(null)>this.game.getB_H()) {
			game.endGame();	// ���� ����
		}
		super.move();
	}

}
