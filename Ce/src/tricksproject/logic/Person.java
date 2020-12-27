package tricksproject.logic;

import tricksproject.db.DBException;
import tricksproject.db.DBPerson;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String firstName ;
    private String lastName, accountName, password ;
    private int accountNr;
    private Account account;
    private ArrayList<String> emails;
    private ArrayList<String> telefoonNummers;
    private ArrayList<Location> responsibilities;

    public Person(int accountNr,String firstName, String lastName, String accountname, String password) throws DBException {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNr =accountNr;
        this.accountName=accountname;
        this.password=password;
        Account s = new Account(accountname, password,accountNr);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(int accountNr) {
        this.accountNr = accountNr;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public ArrayList<String> getTelefoonNummers() {
        return telefoonNummers;
    }

    public void addEmail(String mail) {
        emails.add(mail);
    }

    public void addTelefoonnummer(String nummer) {
        telefoonNummers.add(nummer);
    }

    public void removeEmail(String email) {

        if (emails.contains(email))
        {

                emails.remove(email);

        }
        else {
            System.out.println(email+" kon niet worden verwijderd.");
            return;
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public void setTelefoonNummers(ArrayList<String> telefoonNummers) {
        this.telefoonNummers = telefoonNummers;
    }

    public void removeTelefoonnummer(String nummer)
    {
        if (telefoonNummers.contains(nummer))
        {
            telefoonNummers.remove(nummer);
        }
        else {
            System.out.println(nummer+" kon niet worden verwijderd.");
            return;
        }
    }

    public void addResponsibility(Location l)
    {
        responsibilities.add(l);
    }

    public void deleteResponsibility(Location l)
    {
        if(responsibilities.contains(l))
        {
            responsibilities.remove(l);
        }
        else{
            System.out.println(l+" kon niet worden verwijderd.");
            return;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getLastName(), person.getLastName());
    }



    @Override
    public String toString(){
        return "Persoon[" + "Voornaam=" + firstName + "Achternaam=" + lastName ;
    }


}
