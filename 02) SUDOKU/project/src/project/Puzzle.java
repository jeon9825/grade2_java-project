package project;

public class Puzzle {
	
	protected String [][] board;
	protected boolean [][] mutable;
	private final int ROWS; //행
	private final int COLUMNS; //열
	private final int BOXWIDTH; //박스가로
	private final int BOXHEIGHT; //박스세로
	private final String [] VALIDVALUES;
	
	public Puzzle(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.BOXWIDTH = boxWidth;
		this.BOXHEIGHT = boxHeight;
		this.VALIDVALUES = validValues;
		this.board = new String[ROWS][COLUMNS];
		this.mutable = new boolean[ROWS][COLUMNS];
		initializeBoard();
		initializeMutableSlots();
	}
	
	public Puzzle(Puzzle p) {
		this.ROWS = p.ROWS;
		this.COLUMNS = p.COLUMNS;
		this.BOXWIDTH = p.BOXWIDTH;
		this.BOXHEIGHT = p.BOXHEIGHT;
		this.VALIDVALUES = p.VALIDVALUES;
		this.board = new String[ROWS][COLUMNS];
		for(int r = 0;r < ROWS;r++) {
			for(int c = 0;c < COLUMNS;c++) {
				board[r][c] = p.board[r][c];
			}
		}
		this.mutable = new boolean[ROWS][COLUMNS];
		for(int r = 0;r < ROWS;r++) {
			for(int c = 0;c < COLUMNS;c++) {
				this.mutable[r][c] = p.mutable[r][c];
			}
		}
	}
	
	private void initializeMutableSlots() {
		
	}

	private void initializeBoard() {
		
	}
	
}
