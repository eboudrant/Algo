package backtracking;

public class EightQueenPuzzle {

	private EightQueensModel model;
	private int[] queens = new int[8];

	public EightQueenPuzzle() {
		this.model = new EightQueensModel(queens.length);
		for (int i = 0; i < queens.length; i++) {
			queens[i] = i + 1;
		}
	}

	public EightQueensModel getModel() {
		return model;
	}

	public void resolve() {

		long start = System.currentTimeMillis();
		resolve(0, 0, 1);
		System.out.println("Solution found in "
				+ (System.currentTimeMillis() - start) + " msec");

	}

	
	/**
	 * @TODO : improve the code
	 */
	public boolean resolve(int row, int column, int queen) {

		for (int i = row; i < model.getRowCount();) {

			int iterationCount = 0;

			for (int j = column; j + 1 != column; j++) {
				
				iterationCount++;
				
				if (iterationCount == model.getColumnCount()) {
					break;
				}
				
				if (j == model.getColumnCount()) {
					j = 0;
				}

				try {

					model.setValueAt(queen, i, j);
					model.fireTableCellUpdated(i, j);

					 System.out.println("Placed queen " + queen + " on [" + i
					 + "," + j + "]");

					if (resolve(i + 1, j, queen + 1)) {
						return true;
					}

				} catch (RuntimeException e) {

					System.out.println("Cannot place queen " + queen + " on ["
							+ i + "," + j + "]");
					if (!(e.getCause() instanceof UnableToSetQueenException)) {
						e.printStackTrace();
						System.exit(-1);
					}

				}

			}

			 System.out.println("Cannot place on the column " + i
			 + "--> bactrack");
			 System.out.println("  Reset [" + (row - 1) + "," + column + "] "
			 + i + "");

			model.setValueAt(0, row - 1, column);
			model.fireTableCellUpdated(row - 1, column);
			column++;
			if (column == model.getColumnCount()) {
				column = 0;
			}
			 System.out.println("  Resolve [" + (row - 1) + "," + column +
			 "]");

			return false;

		}

		return true;

	}

}
