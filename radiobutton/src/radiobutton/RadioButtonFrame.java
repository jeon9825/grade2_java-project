package radiobutton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonFrame extends JFrame implements ActionListener {
	private JRadioButton radio1, radio2, radio3;
	private JLabel text1, text2;
	private JPanel top, radio, result;

	public RadioButtonFrame() {
		setTitle("���������");
		setSize(300, 140);

		text1 = new JLabel("�ְ����Դϱ�? �������Դϱ�?");
		top = new JPanel();
		top.add(text1);
		
		radio1=new JRadioButton("�ְ���");
		radio2=new JRadioButton("������");
		radio1.addActionListener(this);
		radio2.addActionListener(this);
		radio=new JPanel();
		radio.add(radio1);
		radio.add(radio2);
		
		text2 = new JLabel("���� ���õ����ʾҽ��ϴ�.");
		result=new JPanel();
		result.add(text2);
		
		this.add(top,BorderLayout.NORTH);
		this.add(radio, BorderLayout.CENTER);
		this.add(result, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==radio1) {
			text2.setText("�ְ����� ���õǾ����ϴ�.");
		}
		else if(a.getSource()==radio2) {
			text2.setText("�������� ���õǾ����ϴ�.");
		}
	}
	public static void main(String[] args) {
		new RadioButtonFrame();
	}
}
