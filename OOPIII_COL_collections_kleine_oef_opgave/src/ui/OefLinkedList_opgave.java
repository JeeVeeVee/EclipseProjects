package ui;

import java.util.*;

public class OefLinkedList_opgave {

    public OefLinkedList_opgave() {

    	Integer getallen[] = {4, 3, 9, 7};


//Cre�er een 'arrayList' en vul op met de array "getallen" (1 instructie)
//-----------------------------------------------------------------------------------

//Cre�er de linkedList "lijstInteger" en vul op met de inhoud van de arrayList ( 1 instructie )
//--------------------------------------------------------------------------------

        weergevenLijst("oplossing: 4 3 9 7", lijstInteger);

        metEenVerhogen(lijstInteger);
        weergevenLijst("oplossing: 5 4 10 8", lijstInteger);

        lijstInteger = metEenVerhogen_java8(lijstInteger);
        weergevenLijst("oplossing: 6 5 11 9", lijstInteger);

        String woorden[] = {"aaa", "bbb", "ccc"};

//Cre�er de LinkedList "lijstString" en vul op met de array "woorden" (1 instructie)
//-----------------------------------------------------------------------
     
        weergevenLijst("oplossing: aaa bbb ccc", lijstString);

        weergevenLijstOmgekeerdeVolgorde(
		"oplossing: ccc bbb aaa", lijstString);

    }

    public void metEenVerhogen(lijst) //------------
    {
        //Alle elementen van de lijst wijzigen:
        //alle elementen worden met 1 verhoogd
        //JAVA 7:

        //JAVA 8:
    }

    public List<Integer> metEenVerhogen_java8(List<Integer> lijst)
    {
        //De elementen van lijst worden met één verhoogd en worden
        //als nieuwe List<Integer> teruggegeven.
        //JAVA 8:

    }

    //generieke methode:
    public void weergevenLijst(String oplossing, lijst) //---                                       -------------
    // Alle elementen van de Collection<Integer> weergeven
    //---------------------------------------------------
    {
        System.out.printf("%s%n%s", oplossing, "           ");
        //TODO
        System.out.println();
    }

    //generieke methode:
    public void weergevenLijstOmgekeerdeVolgorde(
		String oplossing, lijst) {
    // Alle elementen van de List<Integer> in omgekeerde volgorde weergeven
    //--------------------------------------------------------------------------------------
       System.out.printf("%s%n%s", oplossing, "           ");
       //TODO: JAVA 7:

       System.out.println();


       System.out.println(
           "java 8 lijst omgekeerde volgorde: ");
       System.out.printf("%s%n%s", oplossing, "           ");
       //TODO: JAVA8:

       System.out.println();
    }

    public static void main(String args[]) {
        new OefLinkedList_opgave();
    }
}
