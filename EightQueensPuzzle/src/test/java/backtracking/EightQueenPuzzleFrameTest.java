package backtracking;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.junit.Test;

public class EightQueenPuzzleFrameTest {

	private Object object = new Object();

	@Test
	public void testIt() {

		EightQueenPuzzleFrame frame = new EightQueenPuzzleFrame();

		frame.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				synchronized (object) {
					object.notify();
				}

			}
		});

		frame.drawAndResolve();
		
		synchronized (object) {
			try {
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("End");
	}

}
