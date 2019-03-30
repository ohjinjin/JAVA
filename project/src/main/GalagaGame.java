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

/* Ű�����̺�Ʈ�� Ÿ�̸Ӱ�ü�κ��� ��ȯ �ްԵ� �׼��̺�Ʈ������ �ڰ��� ������ ������(listener �̺�Ʈ ����), JPanel�� ��ӹ��� board Ŭ������ ���� -> ������� �ڵ��� ��Ȱ�� */
public class GalagaGame extends JPanel implements KeyListener, ActionListener {
	private static GalagaGame galagaGameObject;	// �̱��� ���尴ü�� ���� ��������
	
	private final int B_WIDTH = 1600;	// ������ â ���α���
	private final int B_HEIGHT = 1000;	// ������ â ���α���
	private final int DELAY = 30;	// Ÿ�̸� ������ ��
 
	private Timer timer;	// Ÿ�̸� ��������-�ִϸ��̼� ������ ���� �ʿ���(�������� �ڵ��� ��Ȱ��)
	private Ship ship;	// Sprite Ŭ������ ��ӹ��� Ship Ŭ���� ��ü�� ���� ��������(�������� �ڵ��� ��Ȱ��)
	
	private ArrayList<Sprite> Sprites;
	private int cntEnemies;
	private int level;
	
	/* ������ private�� ���� */
	private GalagaGame() {
		setBackground(Color.BLACK);	// �г��� ������ �������� ����(����-����(delegation))
		setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));	// ���ǰ�ü�� 800X600(2����)�� �����Ͽ� ������ ����
		setDoubleBuffered(true);	// ���� ���۷� ����
		
		this.cntEnemies = 0;
		this.level = 1;
		this.Sprites = new ArrayList<Sprite>();
		
		initSprites();
		
		timer = new Timer(DELAY, this);	// �ִϸ��̼��� ������ Ÿ�̸Ӱ�ü����
		timer.start();	// Ÿ�̸� ����
		
		addKeyListener(this);	// Ű���帮���ʸ� ���
		this.setFocusable(true);	// Ű����κ��� ��Ŀ���� ����-Ű���� ����̽��� �����쿡 �� ��Ŀ���� �־�߸� �۵��ϵ��ϵǾ�����
	}
	
	/* �̱��� ���� ��ü�� ���� ��ü���� ��ȯ getter�޼ҵ� */
	public static GalagaGame getGalagaGameObject() {
		if (galagaGameObject == null) {
			galagaGameObject = new GalagaGame();
		}
		return galagaGameObject;
	}
	
	/* ��������Ʈ�� �ʱ�ȭ�Լ�  */
	private void initSprites() {
		ship = new Ship(this, "spaceship.png");//,(B_WIDTH-ship.getImage().getWidth(this))/2,B_HEIGHT-ship.getImage().getHeight(this));	// ship ��ü ����
		ship.setX((B_WIDTH-ship.getImage().getWidth(this))/2);	// ���ּ��� x��ǥ �ʱ�ȭ-�������� ���� ���߾ӿ� ��ġ
		ship.setY(B_HEIGHT-ship.getImage().getHeight(this));	// ���ּ��� y��ǥ �ʱ�ȭ-�������� �ϴܿ� ��ġ
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
		tmpMissile.setX(Sprites.get(0).getX()+(Sprites.get(0).getImage().getWidth(null)-tmpMissile.getImage().getWidth(null))/2);	// �̻����� ���ּ��� ���߾ӿ��� ��������
		tmpMissile.setY(Sprites.get(0).getY());
		tmpMissile.setDy(-5*this.level);
		Sprites.add(tmpMissile);
	}
	
	/* Game Loop �� �ʼ� ���1 - ĳ���͵��� �׷��ִ� �޼ҵ� */
	/* JpanelŬ������ �޼ҵ� paintComponent(JVM�� �����층�� ���Ͽ� �ڵ����� ȣ�� �� �޼ҵ�)�� �������̵� */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// �������̵�, �θ�Ŭ������ ���ǵ� paintComponent�� ���� ȣ��
		
		Graphics2D g2d = (Graphics2D)g;	// 2���� �׷��� ��ü�� Ÿ��ĳ�����Ͽ� ����

		for (Sprite S:Sprites) {
			g2d.drawImage(S.getImage(),S.getX(),S.getY(),null);
		}
		
		Toolkit.getDefaultToolkit().sync();	// ���� ������ ��� ��ũ�� �����ش�.
	}
	
	/* Game Loop �� �ʼ� ���2, 3 - ĳ���͵��� ��ġ�� �����Ű�� �޼ҵ�, �浹 ���� �޼ҵ� */
	/* Ÿ�̸Ӱ�ü�� delay ���� ��ȯ�� actionevent��� �̺�Ʈ��ü�� �޾� �̺�Ʈ ó��, �������̵�  */
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
		
		/* ������ ��ġ��� �� ������ ���۵ǵ��� */
		if (this.cntEnemies <= 0) {
			startGame(++level);
			System.out.println(level);
		}
		
		repaint();	// JVM �����층 �� �ð��� �� �� paintComponent() �޼ҵ带 ȣ���ϰ� �ȴ�.
	}
	
	/* Ű���� �̺�Ʈ ó�� �޼ҵ� ����_1 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();	// Ű���� �̺�Ʈ �߻� ��ü�� Ű�ڵ带 ��ȯ�޾� key�� ����
		
		if (key == KeyEvent.VK_LEFT) {
			// ���ּ��� �������� �����̰�
			ship.setDx(-3*this.level);
		}
		if (key == KeyEvent.VK_RIGHT) {
			// ���ּ��� ���������� �����̰�
			ship.setDx(3*this.level);
		}
		if (key == KeyEvent.VK_UP) {
			// ���ּ��� �������� �����̰�
			ship.setDy(-3*this.level);
		}
		if (key == KeyEvent.VK_DOWN) {
			// ���ּ��� �Ʒ������� �����̰�
			ship.setDy(3*this.level);
		}
		if (key == KeyEvent.VK_SPACE) {
			// �̻��� ��������_�ش� �޼ҵ带 ȣ�������ν� sprite.missile != null�� ��
			launchMissile();
			
		}
		repaint();	// paintComponent()�޼ҵ带 JVM�� �ð��� ���� ȣ�����ֵ���
	}

	/* Ű���� �̺�Ʈ ó�� �޼ҵ� ����_2 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();	// Ű���� �̺�Ʈ �߻� ��ü�� Ű�ڵ带 ��ȯ�޾� key�� ����

		// ���ּ��� �������� ���ߵ���
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
		repaint();	// paintComponent()�޼ҵ带 JVM�� �ð��� ���� ȣ�����ֵ���
	}
	
	/* Ű���� �̺�Ʈ ó�� �޼ҵ� ����_3 */
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
