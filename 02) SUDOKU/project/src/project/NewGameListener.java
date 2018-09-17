package project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import project.PuzzleType;

public class NewGameListener implements ActionListener {

	private PuzzleType puzzleType;
	private int fontSize;
	
	public NewGameListener(PuzzleType puzzleType,int fontSize) {
		this.puzzleType = puzzleType;
		this.fontSize = fontSize;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

