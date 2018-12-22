package project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Frame extends JFrame {

	private JPanel buttonSelectionPanel;
	private Panel sPanel;

	public Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setMinimumSize(new Dimension(800, 600));

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");

		JMenuItem nineByNineGame = new JMenuItem("9 By 9 Game");
		nineByNineGame.addActionListener(new NewGameListener(PuzzleType.NINEBYNINE, 26));

		newGame.add(nineByNineGame);

		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);

		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800, 600));

		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90, 500));

		sPanel = new Panel();

		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);

		rebuildInterface(PuzzleType.NINEBYNINE, 25);
	}

	public void rebuildInterface(PuzzleType puzzleType, int fontSize) {
		Puzzle generatedPuzzle = new Generator().generateRandomSudoku(puzzleType);
		sPanel.newSudokuPuzzle(generatedPuzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		for (String value : generatedPuzzle.getValidValues()) {
			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(50, 50));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}

	private class NewGameListener implements ActionListener {

		private PuzzleType puzzleType;
		private int fontSize;

		public NewGameListener(PuzzleType puzzleType, int fontSize) {
			this.puzzleType = puzzleType;
			this.fontSize = fontSize;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(puzzleType, fontSize);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame frame = new Frame();
				frame.setVisible(true);
			}
		});
	}
}
