package domein;

public class Held {

	Wapen wapen;
	
	public Held() {
		this.setWapen(null);
	}

	public void vecht() {
		wapen.valAan();
	}

	public void setWapen(Wapen wapen) {
		if (wapen == null) {
			this.wapen = new Handen();
		} else {
			this.wapen = wapen;
		}
	}
}
