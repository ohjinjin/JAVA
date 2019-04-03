package main;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/* ���ּ��� ���� Ŭ������ �� Sprite Ŭ���� ����  */
public class Sprite {
	private Image sprite;	// ��������Ʈ ��ü�� �̹���
	private int x,y;	// ��������Ʈ ��ü�� ��ǥ x, y
	private int dx, dy;	// x, y�������� ��������Ʈ ��ü�� �������� ������ ����
	
	/* ������-���� �̸��� �μ��� �޾ƿ� �޸𸮿� �ش� �̹��� ���� �����ϴ� �Լ� ȣ�� �� �ʱ�ȭ */
	public Sprite(String filename) {
		loadImage(filename);	// �̹��� ��ü�� ��ȯ �޾� sprite��� �̹��� ��ü ���� ���� �ʵ尡 �����ϵ��� �ϴ� �Լ� ȣ��
		this.x = 0;
		this.y = 0;
		this.dx = 0;
		this.dy = 0;
	}
	
	/* ������ �����ε�-���� �̸��� �μ��� �޾ƿ� �޸𸮿� �ش� �̹��� ���� �����ϴ� �Լ� ȣ�� �� x, y �� ���� �ʱ�ȭ */
	public Sprite(String filename, int x, int y) {
		loadImage(filename);	// �̹��� ��ü�� ��ȯ �޾� sprite��� �̹��� ��ü ���� ���� �ʵ尡 �����ϵ��� �ϴ� �Լ� ȣ��
		this.x = x;
		this.y = y;
		this.dx = 0;
		this.dy = 0;
	}
	
	/* private ������ ������ �޼ҵ� ���� */
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public int getDx() {
		return this.dx;
	}
	
	public int getDy() {
		return this.dy;
	}
	
	public Image getImage() {
		return sprite;
	}
	
	/* private ������ ������ �޼ҵ� ���� */
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	/* �̹������� �޸𸮿� ������ Image ��ü�� ��ȯ�ޱ� */
	private void loadImage(String filename) {
		
		ImageIcon i = new ImageIcon("C:\\Users\\jinsun\\eclipse-workspace\\project\\src\\main\\"+filename);//spaceship.png
		sprite = i.getImage();
	}
	
	/* �����̱� �޼ҵ� */
	public void move() {
		x += dx;
		y += dy;
	}
	
	/* �ٸ� ��������Ʈ���� �浹 ���θ� ����Ͽ� ��ȯ */
	public boolean checkCollision(Sprite other) {
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		myRect.setBounds(this.x,this.y,this.sprite.getWidth(null),this.sprite.getHeight(null));
		otherRect.setBounds(other.getX(),other.getY(),other.getImage().getWidth(null),other.getImage().getHeight(null));
		
		return myRect.intersects(otherRect);
	}
	
	/* �浹�� ó���ϴ� �޼ҵ� */
	public void handleCollision(Sprite other) {
		
	}
}
