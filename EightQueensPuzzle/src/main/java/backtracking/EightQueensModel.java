package backtracking;

import javax.swing.table.DefaultTableModel;

public class EightQueensModel extends DefaultTableModel {

	private static final long serialVersionUID = 6861096099507114149L;
	private int[][] map;

	public EightQueensModel(int size) {
		if (size == 0) {
			throw new RuntimeException("Invalid size, must be greater thant 0");
		}
		map = new int[8][8];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = 0;
			}
		}
	}

	@Override
	public int getRowCount() {
		if (map != null) {
			return map.length;
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		// it is a square
		return getRowCount();
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (row >= getRowCount()) {
			throw new RuntimeException("Invalid range");
		}
		if (column >= getColumnCount()) {
			throw new RuntimeException("Invalid range");
		}
		return map[row][column];
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (((Integer)aValue) == 0) {
			map[row][column] = (Integer) aValue;
		} else {

			if (map[row][column] != 0) {
				throw new RuntimeException(new UnableToSetQueenException());
			}

			for (int i = column - 1; i >= 0; i--) {
				if (map[row][i] != 0) {
					throw new RuntimeException(new UnableToSetQueenException());
				}
			}

			for (int i = row - 1; i >= 0; i--) {
				if (map[i][column] != 0) {
					throw new RuntimeException(new UnableToSetQueenException());
				}
			}

			int j = column - 1;
			for (int i = row - 1; i >= 0 && j >= 0; i--) {
				if (map[i][j] != 0) {
					throw new RuntimeException(new UnableToSetQueenException());
				}
				j--;
			}

			j = column + 1;
			for (int i = row - 1; i >= 0 && j < 8; i--) {
				if (map[i][j] != 0) {
					throw new RuntimeException(new UnableToSetQueenException());
				}
				j++;
			}

			map[row][column] = (Integer) aValue;
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e1) {
//			}
			
		}

	}
}