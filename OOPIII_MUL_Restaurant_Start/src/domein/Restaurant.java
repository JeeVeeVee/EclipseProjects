package domein;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
	private Order order;
	private Lock accessLock = new ReentrantLock();
	private Condition kanOrderWegBrengen = accessLock.newCondition(); //kelner
	private Condition kanOrderPlaatsen = accessLock.newCondition(); //kok
	
//	public Order haalOrderOp() { //get
//		accessLock.lock();
//		Order ref = null;
//		try {
//			
//			while(this.order == null) {
//				System.out.println("waiting in haalOrderOp");
//				kanOrderWegBrengen.await();
//			}
//			ref = this.order;
//			this.order = null;
//			kanOrderWegBrengen.signal();
//		} catch (InterruptedException e) {
//			System.out.println(e);

//		} finally {
//			accessLock.unlock();
//		}
//		return ref;			
//	}
//	
//	public void plaatsOrder(Order order) {
//		accessLock.lock();
//		try {
//			while(this.order != null) {
//				System.out.println("waiting in plaatsOrder");
//				kanOrderPlaatsen.await();	
//			}
//			this.order = null;
//			kanOrderPlaatsen.signal();
//		}catch (InterruptedException e) {
//				e.printStackTrace();
//		} finally {
//			accessLock.unlock();
//
	

	private ArrayBlockingQueue<Order> orders = new ArrayBlockingQueue<>(1);
	
	public void plaatsOrder (Order order) {
		try {
			this.orders.put(order);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Order haalOrderOp() {
	Order o = null;
	try {
		o = this.orders.take();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return o;
	}
	
	

}
