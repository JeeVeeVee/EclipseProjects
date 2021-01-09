package domein;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

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
        Set<Auto> autoSet = new HashSet<>(persistentieController.geefAutos());
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
        return autoSet.stream().collect(Collectors.toMap(Auto::getNummerplaat, a -> a));
    }

    // lijst sorteren - stap4
    private void sorteren(List<Onderhoud> lijstOnderhoud) {
    	lijstOnderhoud.stream().sorted(Comparator.comparing(Onderhoud::getNummerplaat).thenComparing(Onderhoud::getBegindatum))
    		.forEach(System.out::println);
    }

    // lijst samenvoegen - stap5
    private void aangrenzendePeriodenSamenvoegen(List<Onderhoud> lijstOnderhoud) {
        //java 7
            Iterator<Onderhoud> iterator = lijstOnderhoud.iterator();
            Onderhoud onderhoud = null;
            Onderhoud onderhoudNext = null;

            while (iterator.hasNext()){
                onderhoud = onderhoudNext;
                onderhoudNext = iterator.next();
                if (onderhoud != null && onderhoud.getNummerplaat().equals(onderhoudNext.getNummerplaat())){
                    if (onderhoud.getEinddatum().plusDays(1).equals(onderhoudNext.getBegindatum()))
                    {//samenvoegen:
                        onderhoud.setEinddatum(onderhoudNext.getEinddatum());
                        iterator.remove();
                        onderhoudNext = onderhoud;
                    }
                }
            }
        }

    // Maak map van onderhoud: volgens nummerplaat - stap6
    private Map<String, List<Onderhoud>>
            omzettenNaarOnderhoudMap(List<Onderhoud> onderhoudLijst) {
        return onderhoudLijst.stream().collect(Collectors.groupingBy(Onderhoud::getNummerplaat));
    }

    //Hulpmethode - nodig voor stap 7        
    private int sizeToCategorie(int size) {
        return switch (size) {
            case 0, 1 -> 0;
            case 2, 3 -> 1;
            default -> 2;
        };
    }

    // Maak overzicht: set van auto's - stap7
    private List<Set<Auto>> maakOverzicht(
            Map<String, List<Onderhoud>> autoOnderhoudMap) {
        //Hint:
        //van Map<String, List<Onderhoud>> 
        //naar Map<Integer, Set<Auto>> (hulpmethode gebruiken)
        //naar              List<Set<Auto>> 
    	ArrayList<Set<Auto>> outputArrayList = new ArrayList<>();
    	autoOnderhoudMap.keySet().stream().filter(key -> sizeToCategorie(autoOnderhoudMap.get(key).size()) == 0);
    	outputArrayList.add(null);
    	return outputArrayList;
    }

    public String autoMap_ToString() {
        //String res = autoMap.
        return autoMap.values()
                .stream()
                .sorted(Comparator.comparing(Auto::getNummerplaat))
                .map(Auto::toString)
                .collect(Collectors.joining("\n"))
                ;
    }

    public String autoOnderhoudMap_ToString() {
        //String res = autoOnderhoudMap.
        return autoOnderhoudMap.entrySet().stream()
                //.sorted(Comparator.comparing(Map.Entry::getKey())) // IS OKE, maar er is aangepaste meth
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> String.format("%s:%n%s", entry.getKey(), entry.getValue().stream() //Stream<Onderhoud>
                        .map(Onderhoud::toString)
                        .collect(Collectors.joining("\n"))))
                .collect(Collectors.joining("\n"))
        ;
    }

    public String overzicht_ToString() {
        overzichtteller = 1; //attribuut
        //String res = overzichtLijstVanAutos.
        //int teller = 1 // KAN NIET, WANT MOET EFFECTIVE FINAL ZIJN
        return overzichtLijstVanAutos.stream()
                .map(autoset -> String.format("%d%n%s", overzichtteller++
                    ,autoset.stream().map(Auto::toString).collect(Collectors.joining("\n"))))
                .collect(Collectors.joining("\n"))
        ;
    }


}
