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
		setTitle("문제만들기");
		setSize(300, 140);

		text1 = new JLabel("주관식입니까? 객관식입니까?");
		top = new JPanel();
		top.add(text1);
		
		radio1=new JRadioButton("주관식");
		radio2=new JRadioButton("객관식");
		radio1.addActionListener(this);
		radio2.addActionListener(this);
		radio=new JPanel();
		radio.add(radio1);
		radio.add(radio2);
		
		text2 = new JLabel("아직 선택되지않았습니다.");
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
			text2.setText("주관식이 선택되었습니다.");
		}
		else if(a.getSource()==radio2) {
			text2.setText("객관식이 선택되었습니다.");
		}
	}
	public static void main(String[] args) {
		new RadioButtonFrame();
	}
}
