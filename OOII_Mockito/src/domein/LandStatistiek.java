package domein;

public class LandStatistiek {
	private final String landCode;
	private double verhouding;
	
	public LandStatistiek(String landCode, double verhouding) {
		this.landCode = landCode;
		this.verhouding = verhouding;
	}
	
	public String getLandCode() {
		return landCode;
	}
	
	public double getVerhouding() {
		return verhouding;
	}
}
