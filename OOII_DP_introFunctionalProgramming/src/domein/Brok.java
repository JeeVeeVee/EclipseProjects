package domein;

public class Brok {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Brok [value=" + value + "]";
	}

	public Brok(String value) {
		super();
		this.value = value;
	}
	
	public Brok() {
		
	}
	
	
}
