package main;

public class Missile extends Sprite {
	GalagaGame game;
	
	public Missile(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void move() {
		super.move();
		if (this.getY()+this.getImage().getHeight(null)<0) {
			game.removeSprite(this);
		}
	}
	
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Enemy) {
			game.removeSprite(this);
			game.removeSprite(other);
		}
	}
}
