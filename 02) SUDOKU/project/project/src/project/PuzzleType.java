package project;

public enum PuzzleType {

	//9*9 스도쿠
	NINEBYNINE(9, 9, 3, 3, new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" });
	
	private final int rows; // 행의 개수 =>9
	private final int columns; // 열의 개수 =>9
	private final int boxW; // 박스의 가로크기
	private final int boxH; // 박스의 세로크기 => 박스는 3*3
	private final String[] validValues;

	// 생성자
	private PuzzleType(int rows, int columns, int boxW, int boxH, String[] validValues) { 
		this.rows = rows;
		this.columns = columns;
		this.boxW = boxW;
		this.boxH = boxH;
		this.validValues = validValues;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int getBoxW() {
		return boxW;
	}

	public int getBoxH() {
		return boxH;
	}

	public String[] getValidValues() {
		return validValues;
	}

}