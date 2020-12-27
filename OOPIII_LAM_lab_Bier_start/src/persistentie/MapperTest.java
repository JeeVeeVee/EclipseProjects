package persistentie;

public class MapperTest {
	public static void main(String[] args) {
		BierMapper bierMapper = new BierMapper();
		bierMapper.inlezenBieren("bieren.txt");
	}

}
