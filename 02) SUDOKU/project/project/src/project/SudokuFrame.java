package project;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import project.PuzzleType;
import project.NewGameListener;

public class SudokuFrame extends JFrame {

	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("�뾲!"); ///
		this.setMinimumSize(new Dimension(1000, 800));

		JMenuBar menuBar = new JMenuBar();
		JMenu newGame = new JMenu("�� ����");
		JMenuItem nineByNineGame = new JMenuItem("9*9 ����");
		nineByNineGame.addActionListener(new NewGameListener(PuzzleType.NINEBYNINE, 26));

	}
	public static void main(String[] args) {
		SudokuFrame frame = new SudokuFrame();
		frame.setVisible(true);
	}
}
