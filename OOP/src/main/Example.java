package main;

import javax.swing.JFrame;

/* ���� �޼ҵ尡 ���ǵ� �׽�Ʈ Ŭ���� */
public class Example extends JFrame {
	
	/* ������ */
	public Example() {
		initUI();
	}
	
	private void initUI() {
		add(GalagaGame.getGalagaGameObject());	// �̱��� �����װ��� ��ü �����Ͽ� �����쿡 �߰�-�� ������� �ϳ��� ���Ӱ�ü�� �����ǵ���
		pack();	// ��ŷ
		setTitle("GALAGA WARS");	// ������â�� ������ ����
		setLocationRelativeTo(null);	// ��ü �������� ����� �ش� �����찡 ��ġ�ϵ���
	}

	/* ���� �޼ҵ� ���� */
	public static void main(String[] args) {
		Example ex = new Example();	// example ��ü ����
		ex.setVisible(true);	// example ��ü�� ����ȭ
	}
}
