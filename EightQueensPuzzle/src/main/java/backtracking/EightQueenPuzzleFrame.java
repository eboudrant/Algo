package backtracking;

import javax.swing.JFrame;
import javax.swing.JTable;

public class EightQueenPuzzleFrame {

	private JFrame frame;
	private EightQueenPuzzle puzzle;

	/**
	 * Pretty basic Swing display
	 */
	public EightQueenPuzzleFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(10, 10, 160, 160);
		frame.setResizable(false);
		frame.setVisible(true);
		JTable table = new JTable();
		frame.add(table);
		table.setEnabled(false);
		puzzle = new EightQueenPuzzle();
		table.setModel(puzzle.getModel());
	}
	
	public void drawAndResolve() {


		puzzle.resolve();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	
	
}
