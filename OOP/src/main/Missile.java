package main;

public class Missile extends Sprite {
	private GalagaGame game;	// 게임 객체를 담을 변수
	
	/* 생성자 */
	public Missile(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
		// TODO Auto-generated constructor stub
	}
	
	/* 부모 클래스의 move 메소드를 대치 */
	@Override
	public void move() {
		super.move();
		// 미사일이 화면을 벗어나면 없어지도록
		if (this.getY()+this.getImage().getHeight(null)<0) {
			game.removeSprite(this);
		}
	}
	
	/* 부모 클래스의 충돌 처리 메소드 대치 */
	@Override
	public void handleCollision(Sprite other) {
		if (other instanceof Enemy) {	// 적군객체와의 충돌 시
			game.removeSprite(this);	// 나도 죽고
			game.removeSprite(other);	// 적도 죽도록
		}
	}
}
