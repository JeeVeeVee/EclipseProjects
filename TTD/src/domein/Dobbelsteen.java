package domein;

import java.security.SecureRandom;
import static domein.InitDobbelsteen.*;

public class Dobbelsteen {
	private int aantalVlakken;
    private final SecureRandom random;

    public Dobbelsteen(int aantalVlakken) {
        setAantalVlakken(aantalVlakken);
        random = new SecureRandom();
    }

    public Dobbelsteen() {
        this(DEFAULT);
    }

    public int getAantalVlakken() {
        return aantalVlakken;
    }

    private void setAantalVlakken(int aantalVlakken) {

        if (aantalVlakken < MIN_GRENS
                || aantalVlakken > MAX_GRENS) {
            throw new IllegalArgumentException(
                    String.format(
                            "aantal vlakken moet liggen tussen %d en %d",
                            MIN_GRENS, MAX_GRENS));
        }
        this.aantalVlakken = aantalVlakken;
    }

    public int gooi() {
        return random.nextInt(aantalVlakken) + 1;
    }

}
