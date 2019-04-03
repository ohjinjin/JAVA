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
import javax.swing.JOptionPane;
import javax.swing.JOptionPane.*;
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
	
	private ArrayList<Sprite> Sprites;	// ��������Ʈ���� ���� �迭
	private int cntEnemies;	// ������ ���� count
	private int level;	// ���� ����
	private int trialOfCrt;	// ġƮŰ �Ҵ緮
	
	/* ������ private�� ���� */
	private GalagaGame() {
		setBackground(Color.BLACK);	// �г��� ������ �������� ����(����-����(delegation))
		setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));	// ���ǰ�ü�� 800X600(2����)�� �����Ͽ� ������ ����
		setDoubleBuffered(true);	// ���� ���۷� ����
		
		this.cntEnemies = 0;	//������ ���� �ʱ�ȭ
		this.level = 1;	// ������ 1�� �ʱ�ȭ
		this.trialOfCrt = 3;	// ġƮŰ 3���� ��ȸ �Ҵ�
		this.Sprites = new ArrayList<Sprite>();	//��������Ʈ���� ���� �迭�ʱ�ȭ
		
		initSprites();	//��������Ʈ ��ҵ��� �ʱ�ȭ�ϴ� �޼ҵ� ȣ��
		
		timer = new Timer(DELAY, this);	// �ִϸ��̼��� ������ Ÿ�̸Ӱ�ü����
		timer.start();	// Ÿ�̸� ����
		
		addKeyListener(this);	// Ű���帮���ʸ� ���
		this.setFocusable(true);	// Ű����κ��� ��Ŀ���� ����-Ű���� ����̽��� �����쿡 �� ��Ŀ���� �־�߸� �۵��ϵ��ϵǾ�����
	}
	
	/* �̱��� ���� ��ü�� ���� ��ü���� ��ȯ getter�޼ҵ� */
	public static GalagaGame getGalagaGameObject() {
		if (galagaGameObject == null) {	// ���� ������Ʈ�� ���� �������� �ʾ��� ��쿡��
			galagaGameObject = new GalagaGame();	// ���Ӱ�ü�� ����
		}
		return galagaGameObject;	// �����ν��Ͻ��� ��ȯ
	}
	
	/* ��������Ʈ�� �ʱ�ȭ�Լ�  */
	private void initSprites() {
		ship = new Ship(this, "spaceship.png");//,(B_WIDTH-ship.getImage().getWidth(this))/2,B_HEIGHT-ship.getImage().getHeight(this));	// ship ��ü ����
		ship.setX((B_WIDTH-ship.getImage().getWidth(this))/2);	// ���ּ��� x��ǥ �ʱ�ȭ-�������� ���� ���߾ӿ� ��ġ
		ship.setY(B_HEIGHT-ship.getImage().getHeight(this));	// ���ּ��� y��ǥ �ʱ�ȭ-�������� �ϴܿ� ��ġ
		Sprites.add(ship);	// ��������Ʈ���� �����迭�� ��Ÿ�� �߰�
		
		// ���������� ������ ���� ���� 1ź������ 1��, 2ź������ 2��, ... , nź������ n��(�� n<=7)
		for (int row = 0 ; row < (this.level<7?this.level:7); row++) {
			for (int col = 0; col < 12; col++) {	// �� �ٴ� 12������ ����
				Enemy alien = new Enemy(this, "enemy.png");	// ���� ��ü �����Ͽ�
				alien.setY(0+row*alien.getImage().getHeight(this));	// ��ġ �����ϰ�
				alien.setX(0+col*alien.getImage().getWidth(this));
				alien.setDx(5*this.level);	// �ӵ� ����
				Sprites.add(alien);	// ��������Ʈ���� �迭�� �߰�
				this.cntEnemies += 1;	// ������ �� ����
			}
		}
	}
	
	/* ���� ���� �޼ҵ� */
	public void startGame(int level) {
		this.level = level;	// ���� ����
		this.trialOfCrt = 3;	// ġƮŰ �Ҵ緮�� �ʱ�ȭ ���Ǵ� 3ȸ�� ��ȸ
		JOptionPane.showMessageDialog(null, "Win! Level : " + this.level);	// ���� �� �� ������ �޼��� �ڽ��� �˾������ֱ� 
		Sprites.clear();	// ��������Ʈ �迭�� ��Ҹ� ��� clear
		initSprites();	// ��������Ʈ ��ü�� �ʱ�ȭ�ϴ� �޼ҵ� ȣ��
	}
	
	/* ���� ���� �޼ҵ� */
	public void  endGame() {
		JOptionPane.showMessageDialog(null, "Game Over!");	// ���� ���� �� �޼��� �ڽ��� �˾������ֱ�
		System.exit(0);	// ���α׷� ����
	}
	
	/* ��������Ʈ ���� �޼ҵ� */
	public void removeSprite(Sprite sprite) {
		if (sprite instanceof Enemy)	// ������ü���
			cntEnemies -= 1;	// ������ �� ī���� �ϴ� ������ �ϳ� ���̰�
		Sprites.remove(sprite);	// ��������Ʈ �迭���� �ش� ��ü ����
	}
	
	/* �̻��� �߻� �޼ҵ� */
	public void launchMissile() {
		Missile tmpMissile = new Missile(this,"missile.png");	// �̻��� ��ü ����
		tmpMissile.setX(Sprites.get(0).getX()+(Sprites.get(0).getImage().getWidth(null)-tmpMissile.getImage().getWidth(null))/2);	// �̻����� ���ּ��� ���߾ӿ��� ��������
		tmpMissile.setY(Sprites.get(0).getY());
		tmpMissile.setDy(-5*this.level);	// �ӵ� ����
		Sprites.add(tmpMissile);	// ��������Ʈ �迭�� �ش� ��ü �߰�
	}
	public void launchCritical() {
		Friends tmpMissile = new Friends(this,"friends.png");	// �Ʊ����� ��ü ����
		tmpMissile.setX(Sprites.get(0).getX()+(Sprites.get(0).getImage().getWidth(null)-tmpMissile.getImage().getWidth(null))/2);	// �Ʊ� ������ ���ּ��� ���߾ӿ��� ��������
		tmpMissile.setY(Sprites.get(0).getY());
		tmpMissile.setDy(-5*this.level);	// �ӵ� ����
		Sprites.add(tmpMissile);	// ��������Ʈ �迭�� �ش� ��ü �߰�
	}
	
	
	/* Game Loop �� �ʼ� ���1 - ĳ���͵��� �׷��ִ� �޼ҵ� */
	/* JpanelŬ������ �޼ҵ� paintComponent(JVM�� �����층�� ���Ͽ� �ڵ����� ȣ�� �� �޼ҵ�)�� �������̵� */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// �������̵�, �θ�Ŭ������ ���ǵ� paintComponent�� ���� ȣ��
		
		Graphics2D g2d = (Graphics2D)g;	// 2���� �׷��� ��ü�� Ÿ��ĳ�����Ͽ� ����

		for (Sprite S:Sprites) {	// ��������Ʈ���� ��� �׷��ش�
			g2d.drawImage(S.getImage(),S.getX(),S.getY(),null);
		}
		 
		Toolkit.getDefaultToolkit().sync();	// ���� ������ ��� ��ũ�� �����ش�.
	}
	
	/* Game Loop �� �ʼ� ���2, 3 - ĳ���͵��� ��ġ�� �����Ű�� �޼ҵ�, �浹 ���� �޼ҵ� */
	/* Ÿ�̸Ӱ�ü�� delay ���� ��ȯ�� actionevent��� �̺�Ʈ��ü�� �޾� �̺�Ʈ ó��, �������̵�  */
	@Override
	public void actionPerformed(ActionEvent e) {

		// ĳ���͵��� ��ġ�� �����Ű�µ�, �� �������� ��������Ʈ���� �迭���� �����Ǿ���ϴ� ��찡 �߻��� �� �־ foreach�� �Ұ��Ͽ� �Ʒ��Ͱ��� ����
		for (int p = 0; p<Sprites.size();p++) {
			Sprites.get(p).move();
		}
		
		// ĳ���͵鳢���� �浹�� �����ϴµ�, �� �������� ��������Ʈ���� �迭���� �����Ǿ���ϴ� ��찡 �߻��� �� �־ foreach�� �Ұ��Ͽ� �Ʒ��Ͱ��� ����
		for (int p = 0; p <Sprites.size();p++) {
			for (int s = p + 1; s<Sprites.size(); s++) {
				Sprite me = (Sprite)Sprites.get(p);
				Sprite other = (Sprite)Sprites.get(s);

				// ��ȣ �浹�� �Ͼ��°� ����� Ȯ���� �ʿ���
				if (me.checkCollision(other)) {
					me.handleCollision(other);
					other.handleCollision(me);
				}
			}
		}
		
		/* ������ ��ġ��� �� ������ ���۵ǵ��� */
		if (this.cntEnemies <= 0) {
			// ���� ���� �޼ҵ� ȣ��
			startGame(++level);
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
			// �̻��� ���������ϴ� ���
			launchMissile();
			
		}
		if (key == KeyEvent.VK_SHIFT) {
			// ġƮŰ - �Ʊ� ������ �����ϴ� ���, ġƮŰ ��ȸ�� ���Ҵٸ� ����ϵ���
			if ((trialOfCrt--)>0) {
				launchCritical();
			}
			
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
	
	/* ������ ȭ�鿡 ���� �����(�ʺ�,����)���� getter�޼ҵ�  */
	public int getB_W() {
		return this.B_WIDTH;
	}
	
	public int getB_H() {
		return this.B_HEIGHT;
	}
	
}
