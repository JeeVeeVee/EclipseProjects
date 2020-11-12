package domein;

import java.security.SecureRandom;

public class Kelner implements Runnable {
	
	private final Restaurant restaurant;
	private final String naam;
	private SecureRandom random;

	public Kelner(Restaurant restaurant, String string) {
		this.restaurant = restaurant;
		this.naam = string;
		this.random = new SecureRandom();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(random.nextInt(100));
				Order order = restaurant.haalOrderOp();
				System.out.printf("Kelner %s krijgt %s\n", naam, order);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			
		}
		
	}

}
