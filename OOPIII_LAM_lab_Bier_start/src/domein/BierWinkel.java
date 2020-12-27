package domein;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import persistentie.PersistentieController;

public class BierWinkel {

    private final List<Bier> bieren;
    private PersistentieController pc = new PersistentieController();

    public BierWinkel() {
        bieren = pc.inlezenBieren("bieren.txt");
    }

    public List<Bier> getBieren() {
        return bieren;
    }

    public long geefAantalBierenMetMinAlcoholPercentage(double percentage) {
    	Predicate<Bier> minAlcoholPercentage = bier -> bier.getAlcoholgehalte() > percentage;
        return bieren.stream().filter(minAlcoholPercentage).count();
    }

    public List<Bier> geefAlleBierenMetMinAlcoholPercentage(double percentage) {
    	Predicate<Bier> minAlcoholPercentage = bier -> bier.getAlcoholgehalte() > percentage;
        return bieren.stream().filter(minAlcoholPercentage).collect(Collectors.toList());
    }

    public List<String> geefNamenBieren() {
        return bieren.stream().map(bieren -> bieren.getNaam()).collect(Collectors.toList());
    }

    //Bier met hoogst aantal graden
    public Bier geefBierMetHoogsteAlcoholPercentage() {
        return bieren.stream().max(Comparator.comparing(Bier::getAlcoholgehalte)).get();
    }

    //Bier met laagst aantal graden
    public Bier geefBierMetLaagsteAlcoholPercentage() {
    	return bieren.stream().min(Comparator.comparing(Bier::getAlcoholgehalte)).get();    }

    /*Zorg ervoor dat het resultaat gesorteerd wordt op alcoholgehalte van hoog naar laag, 
     en bij gelijk aantal graden op naam (alfabetisch).
     */
    public List<Bier> geefGeordendOpAlcoholGehalteEnNaam() {
        return bieren.stream().sorted(Comparator.comparing(Bier::getAlcoholgehalte).reversed().thenComparing(Bier::getNaam)).collect(Collectors.toList());

    }

    //Alle brouwerijen
    public List<String> geefAlleNamenBrouwerijen() {
        return bieren.stream().map(bier -> bier.getBrouwerij()).collect(Collectors.toList());
    }

    //Alle brouwerijen die het woord "van" bevatten
    public List<String> geefAlleNamenBrouwerijenMetWoord(String woord) {
        return geefAlleNamenBrouwerijen().stream().filter(naam -> naam.contains(woord)).collect(Collectors.toList());
    }

    public Map<String, List<Bier>> opzettenOverzichtBierenPerSoort() {
        return bieren.stream().collect(Collectors.groupingBy(Bier::getSoort));
    }

    public Map<String, Long> opzettenAantalBierenPerSoort() {
        return bieren.stream().collect(Collectors.groupingBy(Bier::getSoort, Collectors.counting()));
    }
    
    
}
