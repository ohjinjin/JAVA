package main;

public class Friends extends Sprite {
	private GalagaGame game;	// 게임 객체를 참조할 변수
	
	/* 생성자 */
	public Friends(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
		// TODO Auto-generated constructor stub
	}
	
	/* 부모 클래스의 move 메소드를 대치 */
	@Override
	public void move() {
		super.move();
		// 화면을 완전히 벗어나게 되면 
		if (this.getY()+this.getImage().getHeight(null)<0) {
			game.removeSprite(this);	// 없어지도록
		}
	}
	
	/* 부모 클래스의 충돌 처리 메소드 대치 */
	@Override
	public void handleCollision(Sprite other) {
		// 적군과 부딪히게되면
		if (other instanceof Enemy) {
			game.removeSprite(other);	// 치트키라 나자신은 그대로 살되, 적군은 죽도록
		}
	}
}
