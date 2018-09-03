package guibasic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestGuiSwingBasic extends JFrame {
	JButton b1, b2, b3, b4;
	JPanel p;

	TestGuiSwingBasic() {
		super("Hello java"); // ==setTitle("MyFrame 만들기");
		setSize(350, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로그램 종료

		p = new JPanel();
		p.setBackground(Color.white);

		p.setLayout(new FlowLayout());
		ImageIcon i1 = new ImageIcon("./ButtonImage/image_exitButton.jpg");
		ImageIcon i2 = new ImageIcon("./ButtonImage/image_exitButton2.jpg");
		
		b1=new JButton(i1);
		b1.setRolloverIcon(i2);
		b1.setBorderPainted(false);
		b1.setPreferredSize(new Dimension(300,50));
		
		p.add(b1);
		add(p);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TestGuiSwingBasic();
	}
}
