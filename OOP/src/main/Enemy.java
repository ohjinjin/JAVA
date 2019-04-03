package main;

public class Enemy extends Sprite {//
	private GalagaGame game;	// 게임 객체를 참조할 변수
	
	/* 생성자 */
	public Enemy(GalagaGame g, String filename) {
		super(filename);
		this.game = g;
	}
	
	/* 부모 클래스의 move 메소드를 대치 */
	@Override
	public void move() {
		
		// 화면을 벗어나지 않도록
		if (((this.getDx()<0)&&(this.getX()<0))||((this.getDx()>0) && (this.getX()+this.getImage().getWidth(null)>this.game.getB_W()))) {
			this.setDx(this.getDx()*(-1));	// 방향 반전시키기
			this.setY(this.getY()+this.getImage().getHeight(null));	// 한칸 내려오기
		}

		// 화면의 바닥에 닿을 경우
		if (this.getY()+this.getImage().getHeight(null)>this.game.getB_H()) {
			game.endGame();	// 게임 오버
		}
		super.move();
	}

}
