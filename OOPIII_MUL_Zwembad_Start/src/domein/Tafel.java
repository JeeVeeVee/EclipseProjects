package domein;
import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

// VUL DE KLASSE VERDER AAN
public class Tafel {

    private static final SecureRandom generator = new SecureRandom();
    private boolean vatLeeg = false;
    
    private ArrayBlockingQueue<Boolean> emmers;

    public Tafel(int aantalEmmers) {
    	emmers = new ArrayBlockingQueue<>(aantalEmmers);
    }

    public void vulEmmer() {
    	try {
			Thread.sleep(generator.nextInt(1000) + 1000);
	    	emmers.put(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    public boolean pakEmmer() {
    	boolean emmerGenomen = false; 
    	boolean value = false;
    	while(!vatLeeg && emmerGenomen) {
    		try {
    			value = emmers.poll(50L, TimeUnit.MILLISECONDS);
    			if(value) {
    				emmerGenomen = true;
    			}
    		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return emmerGenomen;

    }

    public void setVatIsLeeg() {
        vatLeeg = true;
    }

}
