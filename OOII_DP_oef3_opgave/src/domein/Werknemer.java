package domein;

public abstract class Werknemer {
    
    private final String voornaam;
    private final String familienaam;
    private final String geboorteDatum;

    public Werknemer(String voornaam, String familienaam, String geboorteDatum) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.geboorteDatum = geboorteDatum;
    }
    
    public abstract double geefJaarInkomst();
}
