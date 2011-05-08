package datastructure;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<E> {

	private Queue<E> queue = new LinkedList<E>();
	private Object resource = new Object();

	public void add(E object) {
		synchronized (resource) {
			queue.add(object);
			resource.notify();
		}
	}

	public E get() throws InterruptedException {
		synchronized (resource) {
			while (queue.size() == 0) {
				resource.wait();
			}
			return queue.poll();
		}
	}

}
