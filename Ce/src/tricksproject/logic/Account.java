package tricksproject.logic;

public class Account {

    private String AccountNaam;
    private String wachtwoord;
    private int accountNr;

    public Account(String accountNaam, String wachtwoord,int accountNr) {
        AccountNaam = accountNaam;
        this.wachtwoord = wachtwoord;
        this.accountNr=accountNr;
    }

    public String getAccountNaam() {
        return AccountNaam;
    }

    public void setAccountNaam(String accountNaam) {
        AccountNaam = accountNaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public int getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(int accountNr) {
        this.accountNr = accountNr;
    }
}