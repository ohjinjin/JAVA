package main;

import java.awt.Image;
import javax.swing.ImageIcon;

/* 우주선의 상위 클래스가 될 Sprite 클래스 정의  */
public class Sprite {
	private Image sprite;	// 스프라이트 객체의 이미지
	private int x,y;	// 스프라이트 객체의 좌표 x, y
	private int dx, dy;	// x, y증분으로 스프라이트 객체의 움직임의 보폭을 관할
	
	/* 생성자-파일 이름을 인수로 받아와 메모리에 해당 이미지 파일 적재하는 함수 호출 및 초기화 */
	public Sprite(String filename) {
		loadImage(filename);	// 이미지 객체를 반환 받아 sprite라는 이미지 객체 참조 변수 필드가 참조하도록 하는 함수 호출
		this.x = 0;
		this.y = 0;
		this.dx = 0;
		this.dy = 0;
	}
	
	/* private 변수들 접근자 메소드 정의 */
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
	
	/* private 변수들 설정자 메소드 정의 */
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
	
	/* 이미지파일 메모리에 적재후 Image 객체로 반환받기 */
	private void loadImage(String filename) {
		ImageIcon i = new ImageIcon("D:\\2019\\1학기\\JAVA Application Programming\\JAVA응용프로그래밍_2\\"+filename);//spaceship.png
		sprite = i.getImage();
	}
	
	/* 움직이기 메소드 */
	public void move() {
		x += dx;
		y += dy;
	}
	
	
}
