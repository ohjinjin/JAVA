package main;

import java.awt.Image;
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
		ImageIcon i = new ImageIcon("D:\\2019\\1�б�\\JAVA Application Programming\\JAVA�������α׷���_2\\"+filename);//spaceship.png
		sprite = i.getImage();
	}
	
	/* �����̱� �޼ҵ� */
	public void move() {
		x += dx;
		y += dy;
	}
	
	
}
