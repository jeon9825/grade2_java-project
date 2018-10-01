package project;

public class Puzzle {
	
	protected String [][] board;
	protected boolean [][] mutable;
	private final int ROWS; //��
	private final int COLUMNS; //��
	private final int BOXWIDTH; //�ڽ�����
	private final int BOXHEIGHT; //�ڽ�����
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
