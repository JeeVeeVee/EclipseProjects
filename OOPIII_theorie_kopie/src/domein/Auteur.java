/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;


public class Auteur
{

    private String naam, voornaam;

    public Auteur(String naam, String voornaam)
    {
       setNaam(naam);
       setVoornaam(voornaam);
    }

    public String getNaam()
    {
        return naam;
    }

    public String getVoornaam()
    {
        return voornaam;
    }

    private void setNaam(String naam)
    {
        this.naam = naam;
    }

    private void setVoornaam(String voornaam)
    {
        this.voornaam = voornaam;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s", naam, voornaam);
    }
}

