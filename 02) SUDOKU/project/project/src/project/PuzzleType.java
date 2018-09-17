package project;

public enum PuzzleType {

	//9*9 ������
	NINEBYNINE(9, 9, 3, 3, new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" });
	
	private final int rows; // ���� ���� =>9
	private final int columns; // ���� ���� =>9
	private final int boxW; // �ڽ��� ����ũ��
	private final int boxH; // �ڽ��� ����ũ�� => �ڽ��� 3*3
	private final String[] validValues;

	// ������
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