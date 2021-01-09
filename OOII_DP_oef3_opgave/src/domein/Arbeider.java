package domein;

public class Arbeider extends Werknemer{
    
    private double uurloon;
    private int ploegenstelsel;

    public Arbeider(String voornaam, String familienaam, String geboorteDatum,double uurloon, int ploegenstelsel) {
        super(voornaam, familienaam, geboorteDatum);
        this.uurloon = uurloon;
        this.ploegenstelsel = ploegenstelsel;
    }

    @Override
    public double geefJaarInkomst() {
        return uurloon * ploegenstelsel;
    }
    
    
}
