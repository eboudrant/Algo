package datastructure;

import org.junit.Test;

public class BlockingQueueTest {

	@Test
	public void testBlockingQueue() {

		final BlockingQueue<Long> bq = new BlockingQueue<Long>();

		Runnable get = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println(Thread.currentThread().getName() + " got " + bq.get());
					}
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " stopped");
				}

			}
		};

		final Thread t1 = new Thread(get, "t1");
		final Thread t2 = new Thread(get, "t2");
		final Thread t3 = new Thread(get, "t3");
		final Thread t4 = new Thread(get, "t4");

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		Runnable add = new Runnable() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				while (System.currentTimeMillis() - start < 10000) {
					bq.add(System.currentTimeMillis());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
					}
				}
				t1.interrupt();
				t2.interrupt();
				t3.interrupt();
				t4.interrupt();
				System.out.println(Thread.currentThread().getName() + " ended");
			}
		};
		Thread t0 = new Thread(add, "t0");

		t0.start();

		try {
			t0.join();
		} catch (InterruptedException e) {
		}

		System.out.println(Thread.currentThread().getName() + " ended");
	}
}
