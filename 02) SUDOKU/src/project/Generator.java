package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Generator {

	public Puzzle generateRandomSudoku(PuzzleType puzzleType) {
		Puzzle puzzle = new Puzzle(puzzleType.getRows(), puzzleType.getColumns(), puzzleType.getBoxWidth(),
				puzzleType.getBoxHeight(), puzzleType.getValidValues());
		Puzzle copy = new Puzzle(puzzle);

		Random randomGenerator = new Random();

		List<String> notUsedValidValues = new ArrayList<String>(Arrays.asList(copy.getValidValues()));
		for (int r = 0; r < copy.getNumRows(); r++) {
			int randomValue = randomGenerator.nextInt(notUsedValidValues.size());
			copy.makeMove(r, 0, notUsedValidValues.get(randomValue), true);
			notUsedValidValues.remove(randomValue);
		}

		backtrackSudokuSolver(0, 0, copy);

		int numberOfValuesToKeep = (int) (0.4 * (copy.getNumRows() * copy.getNumRows()));

		for (int i = 0; i < numberOfValuesToKeep;) {
			int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
			int randomColumn = randomGenerator.nextInt(puzzle.getNumColumns());

			if (puzzle.isSlotAvailable(randomRow, randomColumn)) {
				puzzle.makeMove(randomRow, randomColumn, copy.getValue(randomRow, randomColumn), false);
				i++;
			}
		}

		return puzzle;
	}

	private boolean backtrackSudokuSolver(int r, int c, Puzzle puzzle) {
		if (!puzzle.inRange(r, c)) {
			return false;
		}

		if (puzzle.isSlotAvailable(r, c)) {

			for (int i = 0; i < puzzle.getValidValues().length; i++) {

				if (!puzzle.numInRow(r, puzzle.getValidValues()[i]) && !puzzle.numInCol(c, puzzle.getValidValues()[i])
						&& !puzzle.numInBox(r, c, puzzle.getValidValues()[i])) {

					puzzle.makeMove(r, c, puzzle.getValidValues()[i], true);

					if (puzzle.boardFull()) {
						return true;
					}

					if (r == puzzle.getNumRows() - 1) {
						if (backtrackSudokuSolver(0, c + 1, puzzle))
							return true;
					} else {
						if (backtrackSudokuSolver(r + 1, c, puzzle))
							return true;
					}
				}
			}
		}

		else {
			if (r == puzzle.getNumRows() - 1) {
				return backtrackSudokuSolver(0, c + 1, puzzle);
			} else {
				return backtrackSudokuSolver(r + 1, c, puzzle);
			}
		}

		puzzle.makeSlotEmpty(r, c);

		return false;
	}
}