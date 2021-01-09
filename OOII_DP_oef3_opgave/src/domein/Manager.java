package domein;

public class Manager extends Bediende{
    
    private double premie;

    public Manager(String voornaam, String familienaam, String geboorteDatum, double maandwedde, double premie) {
        super(voornaam, familienaam, geboorteDatum, maandwedde);
        this.premie = premie;
    }
    
    @Override
    public double geefJaarInkomst() {
        return super.geefJaarInkomst() + premie;
    }
}
