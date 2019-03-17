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
import javax.swing.JPanel;
import javax.swing.Timer;

/* Ű�����̺�Ʈ�� Ÿ�̸Ӱ�ü�κ��� ��ȯ �ްԵ� �׼��̺�Ʈ������ �ڰ��� ������ ������(listener �̺�Ʈ ����), JPanel�� ��ӹ��� board Ŭ������ ���� -> ������� �ڵ��� ��Ȱ�� */
public class Board extends JPanel implements KeyListener, ActionListener {
	
	private final int B_WIDTH = 800;	// ������ â ���α���
	private final int B_HEIGHT = 600;	// ������ â ���α���
	private final int DELAY = 60;	// Ÿ�̸� ������ ��

	private Timer timer;	// Ÿ�̸� ��������(�������� �ڵ��� ��Ȱ��)
	private Ship ship;	// Sprite Ŭ������ ��ӹ��� Ship Ŭ���� ��ü�� ���� ��������(�������� �ڵ��� ��Ȱ��)
	
	/* ������ */
	public Board() { 
		initBoard();
	}
	
	/* ����panel �ʱ�ȭ�Լ�  */
	private void initBoard() {
		setBackground(Color.BLACK);	// �г��� ������ �������� ����(����-����(delegation))
		setPreferredSize(new Dimension(B_WIDTH,B_HEIGHT));	// ���ǰ�ü�� 800X600(2����)�� �����Ͽ� ������ ����
		setDoubleBuffered(true);	// ���� ���۷� ����
		ship = new Ship("spaceship.png");	// ship ��ü ����
		ship.setX((B_WIDTH-ship.getImage().getWidth(this))/2);	// ���ּ��� x��ǥ �ʱ�ȭ-�������� ���� ���߾ӿ� ��ġ
		ship.setY(B_HEIGHT-ship.getImage().getHeight(this));	// ���ּ��� y��ǥ �ʱ�ȭ-�������� �ϴܿ� ��ġ
		
		timer = new Timer(DELAY, this);	// �ִϸ��̼��� ������ Ÿ�̸Ӱ�ü����
		timer.start();	// Ÿ�̸� ����
		
		addKeyListener(this);	// Ű���帮���ʸ� ���
		this.setFocusable(true);	// Ű����κ��� ��Ŀ���� ����-Ű���� ����̽��� �����쿡 �� ��Ŀ���� �־�߸� �۵��ϵ��ϵǾ�����
	}
	
	/* JpanelŬ������ �޼ҵ� paintComponent(JVM�� �����층�� ���Ͽ� �ڵ����� ȣ�� �� �޼ҵ�)�� �������̵� */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	// �������̵�, �θ�Ŭ������ ���ǵ� paintComponent�� ���� ȣ��
		
		Graphics2D g2d = (Graphics2D)g;	// 2���� �׷��� ��ü�� Ÿ��ĳ�����Ͽ� ����
		g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(),this);	// ���ּ� �׸��� �Լ� ȣ��
		
		if (ship.getMissile()!=null) {	// ���ּ��� �̻��� ��������Ʈ ��ü�� ������ �ִٸ�
			g2d.drawImage(ship.getMissile().getImage(), ship.getMissile().getX(), ship.getMissile().getY(),this);	// �̻��� �׸��� �Լ��� ȣ��
		}
		Toolkit.getDefaultToolkit().sync();	// ���� ������ ��� ��ũ�� �����ش�.
	}
	
	/* Ÿ�̸Ӱ�ü�� delay ���� ��ȯ�� actionevent��� �̺�Ʈ��ü�� �޾� �̺�Ʈ ó��, �������̵�  */
	@Override
	public void actionPerformed(ActionEvent e) {
		ship.move();	// ���ּ��� x, y ��ǥ�� �������ش�.
		if (ship.getMissile()!=null) {	// ���ּ��� �̻��� ��������Ʈ ��ü�� ������ �ִٸ�
			ship.getMissile().move();	// �̻����� x, y ��ǥ�� �������ش�.
		}
		
		repaint();	// JVM �����층 �� �ð��� �� �� paintComponent() �޼ҵ带 ȣ���ϰ� �ȴ�.
	}
	
	/* Ű���� �̺�Ʈ ó�� �޼ҵ� ����_1 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();	// Ű���� �̺�Ʈ �߻� ��ü�� Ű�ڵ带 ��ȯ�޾� key�� ����
		
		if (key == KeyEvent.VK_LEFT) {
			// ���ּ��� �������� �����̰�
			ship.setDx(-3);
		}
		if (key == KeyEvent.VK_RIGHT) {
			// ���ּ��� ���������� �����̰�
			ship.setDx(3);
		}
		if (key == KeyEvent.VK_UP) {
			// ���ּ��� �������� �����̰�
			ship.setDy(-3);
		}
		if (key == KeyEvent.VK_DOWN) {
			// ���ּ��� �Ʒ������� �����̰�
			ship.setDy(3);
		}
		if (key == KeyEvent.VK_SPACE) {
			// �̻��� ��������_�ش� �޼ҵ带 ȣ�������ν� sprite.missile != null�� ��
			ship.launchMissile();
			
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
	
}
