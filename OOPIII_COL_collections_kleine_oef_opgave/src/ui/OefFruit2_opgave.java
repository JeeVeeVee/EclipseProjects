package ui;

import java.util.*;

class CollectionOperaties {
    
    //methode verwijderOpLetter
    //-------------------------
	public static void verwijderOpLetter(List<String> list, char c) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if(iterator.next().indexOf(c) >= 0) {
				iterator.remove();
			}
		}
	}

	public static void verwijderSequence(List<String> list, String string) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if(iterator.next().equals(string)) {
				iterator.remove();
			}
		}
	}

    //methode verwijderSequence
    //-------------------------
}

public class OefFruit2_opgave {

    public static void main(String args[]) {
        String kist[][] = {{"appel", "peer", "citroen", "kiwi", "perzik"},
        {"banaan", "mango", "citroen", "kiwi", "zespri", "pruim"},
        {"peche", "lichi", "kriek", "kers", "papaya"}};

        List<String> list = new ArrayList<>();
        List<String[]> kistenList = Arrays.asList(kist);
        for(String[] strings : kistenList) {
        	list.addAll(Arrays.asList(strings));
        }
        String mand[];

//Voeg de verschillende kisten samen in een ArrayList list.
//--------------------------------------------------------


        CollectionOperaties.verwijderOpLetter(list, 'p');
        System.out.println("na verwijder letter ('p') :  " + list + "\n");

        CollectionOperaties.verwijderSequence(list, "kiwi");
        System.out.println("na verwijder sequence (kiwi) : " + list + "\n");

//Plaats het resultaat terug in een array mand en sorteer die oplopend.
//---------------------------------------------------------------------


//Geef de inhoud van de array "mand" terug
//----------------------------------------


    }
}
