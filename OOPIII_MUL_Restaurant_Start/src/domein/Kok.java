package domein;

import java.security.SecureRandom;

public class Kok implements Runnable {
	private final Restaurant restaurant;
	private SecureRandom random;
	
	public Kok(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.random = new SecureRandom();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(random.nextInt(100));
				restaurant.plaatsOrder(new Order());
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	

}
