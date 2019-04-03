package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/* 키보드이벤트와 타이머객체로부터 반환 받게될 액션이벤트에대한 자격을 가지고 있으며(listener 이벤트 구현), JPanel을 상속받은 board 클래스를 정의 -> 상속으로 코드의 재활용 */
public class GalagaGame extends JPanel implements KeyListener, ActionListener {
	private static GalagaGame galagaGameObject;	// 싱글톤 보드객체에 대한 참조변수
	
	private final int B_WIDTH = 1600;	// 윈도우 창 가로길이
	private final int B_HEIGHT = 1000;	// 윈도우 창 세로길이
	private final int DELAY = 30;	// 타이머 딜레이 값
 
	private Timer timer;	// 타이머 참조변수-애니메이션 구현을 위해 필요함(구성으로 코드의 재활용)
	private Ship ship;	// Sprite 클래스를 상속받은 Ship 클래스 객체에 대한 참조변수(구성으로 코드의 재활용)
	
	private ArrayList<Sprite> Sprites;
	private int cntEnemies;
	private int level;
	
	/* 생성자 private로 설정 */
	private GalagaGame() {
		setBackground(Color.BLACK);	// 패널의 배경색을 검정으로 설정(구성-위임(delegation))
		setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));	// 디멘션객체를 800X600(2차원)로 생성하여 사이즈 설정
		setDoubleBuffered(true);	// 더블 버퍼로 설정
		
		this.cntEnemies = 0;
		this.level = 1;
		this.Sprites = new ArrayList<Sprite>();
		
		initSprites();
		
		timer = new Timer(DELAY, this);	// 애니메이션을 구현할 타이머객체생성
		timer.start();	// 타이머 시작
		
		addKeyListener(this);	// 키보드리스너를 등록
		this.setFocusable(true);	// 키보드로부터 포커스를 얻어옴-키보드 디바이스는 윈도우에 그 포커스가 있어야만 작동하도록되어있음
	}
	
	/* 싱글톤 게임 객체를 위한 객체참조 반환 getter메소드 */
	public static GalagaGame getGalagaGameObject() {
		if (galagaGameObject == null) {
			galagaGameObject = new GalagaGame();
		}
		return galagaGameObject;
	}
	
	/* 스프라이트들 초기화함수  */
	private void initSprites() {
		ship = new Ship(this, "spaceship.png");//,(B_WIDTH-ship.getImage().getWidth(this))/2,B_HEIGHT-ship.getImage().getHeight(this));	// ship 객체 생성
		ship.setX((B_WIDTH-ship.getImage().getWidth(this))/2);	// 우주선의 x좌표 초기화-윈도우의 가로 정중앙에 위치
		ship.setY(B_HEIGHT-ship.getImage().getHeight(this));	// 우주선의 y좌표 초기화-윈도우의 하단에 위치
		Sprites.add(ship);
		
		for (int row = 0 ; row < 5; row++) {
			for (int col = 0; col < 12; col++) {
				Enemy alien = new Enemy(this, "enemy.png");
				alien.setY(0+row*alien.getImage().getHeight(this));
				alien.setX(0+col*alien.getImage().getWidth(this));
				alien.setDx(5*this.level);
				Sprites.add(alien);
				this.cntEnemies += 1;
			}
		}
	}
	
	public void startGame(int level) {
		this.level = level;
		Sprites.clear();
		initSprites();
	}
	
	public void  endGame() {
		System.exit(0);
	}
	
	public void removeSprite(Sprite sprite) {
		if (sprite instanceof Enemy)
			cntEnemies -= 1;
		Sprites.remove(sprite);
	}
	
	public void launchMissile() {
		Missile tmpMissile = new Missile(this,"missile.png");
		tmpMissile.setX(Sprites.get(0).getX()+(Sprites.get(0).getImage().getWidth(null)-tmpMissile.getImage().getWidth(null))/2);	// 미사일이 우주선의 정중앙에서 나오도록
		tmpMissile.setY(Sprites.get(0).getY());
		tmpMissile.setDy(-5*this.level);
		Sprites.add(tmpMissile);
	}
	
	/* Game Loop 내 필수 요소1 - 캐릭터들을 그려주는 메소드 */
	/* Jpanel클래스의 메소드 paintComponent(JVM의 스케쥴링을 통하여 자동으로 호출 할 메소드)를 오버라이딩 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// 오버라이딩, 부모클래스에 정의된 paintComponent를 먼저 호출
		
		Graphics2D g2d = (Graphics2D)g;	// 2차원 그래픽 객체로 타입캐스팅하여 저장

		for (Sprite S:Sprites) {
			g2d.drawImage(S.getImage(),S.getX(),S.getY(),null);
		}
		
		Toolkit.getDefaultToolkit().sync();	// 더블 버퍼의 경우 싱크를 맞춰준다.
	}
	
	/* Game Loop 내 필수 요소2, 3 - 캐릭터들의 위치를 변경시키는 메소드, 충돌 감지 메소드 */
	/* 타이머객체가 delay 이후 반환할 actionevent라는 이벤트객체를 받아 이벤트 처리, 오버라이딩  */
	@Override
	public void actionPerformed(ActionEvent e) {

		for (int p = 0; p<Sprites.size();p++) {
			Sprites.get(p).move();
		}
		
		for (int p = 0; p <Sprites.size();p++) {
			for (int s = p + 1; s<Sprites.size(); s++) {
				Sprite me = (Sprite)Sprites.get(p);
				Sprite other = (Sprite)Sprites.get(s);
				
				if (me.checkCollision(other)) {
					me.handleCollision(other);
					other.handleCollision(me);
				}
			}
		}
		
		/* 적군을 해치우면 새 게임이 시작되도록 */
		if (this.cntEnemies <= 0) {
			startGame(++level);
			System.out.println(level);
		}
		
		repaint();	// JVM 스케쥴링 시 시간이 빌 때 paintComponent() 메소드를 호출하게 된다.
	}
	
	/* 키보드 이벤트 처리 메소드 구현_1 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();	// 키보드 이벤트 발생 객체의 키코드를 반환받아 key에 저장
		
		if (key == KeyEvent.VK_LEFT) {
			// 우주선이 왼쪽으로 움직이게
			ship.setDx(-3*this.level);
		}
		if (key == KeyEvent.VK_RIGHT) {
			// 우주선이 오른쪽으로 움직이게
			ship.setDx(3*this.level);
		}
		if (key == KeyEvent.VK_UP) {
			// 우주선이 위쪽으로 움직이게
			ship.setDy(-3*this.level);
		}
		if (key == KeyEvent.VK_DOWN) {
			// 우주선이 아래쪽으로 움직이게
			ship.setDy(3*this.level);
		}
		if (key == KeyEvent.VK_SPACE) {
			// 미사일 나가도록_해당 메소드를 호출함으로써 sprite.missile != null이 됨
			launchMissile();
			
		}
		repaint();	// paintComponent()메소드를 JVM이 시간이 날때 호출해주도록
	}

	/* 키보드 이벤트 처리 메소드 구현_2 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();	// 키보드 이벤트 발생 객체의 키코드를 반환받아 key에 저장

		// 우주선의 움직임이 멈추도록
		if (key == KeyEvent.VK_LEFT) {
			ship.setDx(0);
		}
		if (key == KeyEvent.VK_RIGHT) {
			ship.setDx(0);
		}
		if (key == KeyEvent.VK_UP) {
			ship.setDy(0);
		}
		if (key == KeyEvent.VK_DOWN) {
			ship.setDy(0);
		}
		repaint();	// paintComponent()메소드를 JVM이 시간이 날때 호출해주도록
	}
	
	/* 키보드 이벤트 처리 메소드 구현_3 */
	public void keyTyped(KeyEvent e) {
		// do-nothing
	}
	
	public int getB_W() {
		return this.B_WIDTH;
	}
	
	public int getB_H() {
		return this.B_HEIGHT;
	}
	
}
