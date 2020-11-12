package domein;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class TellerThread implements Runnable {
	
	private IntegerProperty teller = new SimpleIntegerProperty();
	private Lock accessLock = new ReentrantLock();
	private Condition kanDoorgaan = accessLock.newCondition();
	private boolean pauze = false;
	private boolean going = true;
	
	public void addObserver(ChangeListener<? super Number> listener) {
		teller.addListener(listener);
	}
	
	public void suspend() {
		System.out.println("suspended");
		accessLock.lock();
		pauze = true;
		accessLock.unlock();
	}
	
	public void resume() {
		pauze = false;
	}
	
	public void stop() {
		accessLock.lock();
		going = false;
		accessLock.unlock();

	}
	
	@Override
	public void run() {
		while (going) {
			teller.set(teller.get()+1);
			try {
				Thread.sleep(100);
				while(pauze) {
					System.out.println("we wait");
				}	
			System.out.println(teller);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			} 			
		}
		
	}
}