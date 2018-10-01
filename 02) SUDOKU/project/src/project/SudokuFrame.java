package project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import project.PuzzleType;
import project.NewGameListener;

public class SudokuFrame extends JFrame {
	private JPanel buttonSelectionPanel; // 전체적인 틀
	
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("잼쓰!"); ///재밌는 스도쿠
		this.setMinimumSize(new Dimension(1000, 800));

		JMenuBar menuBar = new JMenuBar();
		JMenu newGame = new JMenu("새 게임");
		JMenuItem nineByNineGame = new JMenuItem("9*9 게임");
		nineByNineGame.addActionListener(new NewGameListener(PuzzleType.NINEBYNINE, 26));
		newGame.add(nineByNineGame);
		menuBar.add(newGame);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90,500));


	}
	public static void main(String[] args) {
		SudokuFrame frame = new SudokuFrame();
		frame.setVisible(true);
	}
}
