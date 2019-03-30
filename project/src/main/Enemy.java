package main;

public class Enemy extends Sprite {
	GalagaGame game;
	
	public Enemy(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
	}
	
	@Override
	public void move() {
		if (((this.getDx()<0)&&(this.getX()<10))||((this.getDx()>0) && (this.getX()>this.game.getB_W()))) {
			this.setDx(this.getDx()*(-1));
			this.setY(this.getY()+this.getImage().getHeight(null));
		}
		/*if (E.getX()+E.getImage().getWidth(null) >= B_WIDTH) {
			E.setDx(-5);
			E.setY(E.getY() + E.getImage().getHeight(null));
		}
		else if (E.getX() < 0) {
			E.setDx(5);
			E.setY(E.getY() + E.getImage().getHeight(null));
		}*/
		if (this.getY()>this.game.getB_H()) {
			game.endGame();
		}
		super.move();
	}

}
