package tricksproject.logic;

public class Location {
    private int idLocationNR, accountnr;
    private String locationType ,adres, categorie ;

    public Location(int idLocationNR, String locationType, String adres, String category,int accountnr) {
        this.idLocationNR = idLocationNR;
        this.locationType = locationType;
        this.adres = adres;
        this.accountnr=accountnr;
        this.categorie=category;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public int getIdLocationNR() {
        return idLocationNR;
    }

    public void setIdLocationNR(int idLocationNR) {
        this.idLocationNR = idLocationNR;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getAccountnr() {
        return accountnr;
    }

    public void setAccountnr(int accountnr) {
        this.accountnr = accountnr;
    }

    public String getCategory() {
        return categorie;
    }

    public void setCategory(String group) {
        this.categorie = group;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocationNR=" + idLocationNR +
                ", accountnr=" + accountnr +
                ", locationType='" + locationType + '\'' +
                ", adres='" + adres + '\'' +
                ", group='" + categorie + '\'' +
                '}';
    }
}
