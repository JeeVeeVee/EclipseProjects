package domein;

import java.awt.print.Printable;
import java.io.File;
import java.time.LocalDate;
import java.util.*;

import persistentie.AutoMapper;
import persistentie.PersistentieController;

public class Garage {

    private final File auto;
    private final File onderhoud;
    private Map<String, Auto> autoMap;
    private Map<String, List<Onderhoud>> autoOnderhoudMap;
    private List<Set<Auto>> overzichtLijstVanAutos;

    private final int AANTAL_OVERZICHTEN = 3;
    private int overzichtteller;

    public Garage(String bestandAuto, String bestandOnderhoud) {
        auto = new File(bestandAuto);
        onderhoud = new File(bestandOnderhoud);
        initGarage();
    }

    private void initGarage() {
        PersistentieController persistentieController
                = new PersistentieController(auto, onderhoud);

        //Set<Auto> inlezen - stap1
        Set<Auto> autoSet = new HashSet<>();
        autoSet.addAll(persistentieController.geefAutos());
        System.out.println("STAP 1");

        // Maak map van auto's: volgens nummerplaat - stap2
        autoMap = omzettenNaarAutoMap(autoSet);
        System.out.println("STAP 2");

        // Onderhoud inlezen - stap3
        List<Onderhoud> onderhoudLijst = 
                persistentieController.geefOnderhoudVanAutos();
        System.out.println("STAP 3 : " + onderhoudLijst);
        
        // lijst sorteren - stap4
        sorteren(onderhoudLijst);
        System.out.println("STAP 4");

        // lijst samenvoegen - stap5
        aangrenzendePeriodenSamenvoegen(onderhoudLijst);
        System.out.println("STAP 5");

        // Maak map van onderhoud: volgens nummerplaat - stap6
        autoOnderhoudMap = omzettenNaarOnderhoudMap(onderhoudLijst);
        System.out.println("STAP 6");

        // Maak overzicht: set van auto's - stap7
        overzichtLijstVanAutos = maakOverzicht(autoOnderhoudMap);
        System.out.println("STAP 7");
        overzichtLijstVanAutos.forEach(System.out::println);
    }

    // Maak map van auto's: volgens nummerplaat - stap2
    private Map<String, Auto> omzettenNaarAutoMap(Set<Auto> autoSet) {
    	HashMap<String, Auto> outputHashMap = new HashMap<>();
    	List<Auto> autos = new ArrayList<>(autoSet);
    	Iterator<Auto> iterator = autos.iterator();
    	while(iterator.hasNext()) {
    		Auto auto  = iterator.next();
    		outputHashMap.put(auto.getNummerplaat(), auto);
    	}
    	return outputHashMap;
    }

    // lijst sorteren - stap4
    private void sorteren(List<Onderhoud> lijstOnderhoud) {
        Collections.sort(lijstOnderhoud);
    }

    // lijst samenvoegen - stap5
    private void aangrenzendePeriodenSamenvoegen(List<Onderhoud> lijstOnderhoud) {
//java 7
    	Iterator<Onderhoud> iterator = lijstOnderhoud.iterator();
	    while(iterator.hasNext()) {
	        Onderhoud onderhoud = iterator.next();
	        Onderhoud onderhoudNext = iterator.next();
	        if (onderhoud.getEinddatum().plusDays(1).equals(onderhoudNext.getBegindatum())){
	        	lijstOnderhoud.remove(onderhoud);
	        	lijstOnderhoud.remove(onderhoudNext);
	        	lijstOnderhoud.add(new Onderhoud(onderhoud.getBegindatum(), onderhoudNext.getEinddatum(), onderhoud.getNummerplaat()));
	        }     
    	}


    }

    // Maak map van onderhoud: volgens nummerplaat - stap6
    private Map<String, List<Onderhoud>>
            omzettenNaarOnderhoudMap(List<Onderhoud> onderhoudLijst) {
        return null;
    }

    //Hulpmethode - nodig voor stap 7        
    private int sizeToCategorie(int size) {
        if(size == 0 || size == 1){
            return 0;
        } else if (size == 2 || size == 3){
            return 1;
        } else {
            return 2;
        }
    }

    // Maak overzicht: set van auto's - stap7
    private List<Set<Auto>> maakOverzicht(
            Map<String, List<Onderhoud>> autoOnderhoudMap) {
        //Hint:
        //van Map<String, List<Onderhoud>> 
        //naar Map<Integer, Set<Auto>> (hulpmethode gebruiken)
        //naar              List<Set<Auto>> 
        return null;
    }

//Oefening DomeinController:
    public String autoMap_ToString() {
        //String res = autoMap.
        return null;
    }

    public String autoOnderhoudMap_ToString() {
        //String res = autoOnderhoudMap.
        return null;
    }

    public String overzicht_ToString() {
        overzichtteller = 1;
        //String res = overzichtLijstVanAutos.
        return null;
    }

}
