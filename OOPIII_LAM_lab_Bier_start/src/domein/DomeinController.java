package domein;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class DomeinController
{
    private BierWinkel bierWinkel;

    public DomeinController()
    {
        bierWinkel = new BierWinkel();
    }
  
    public long geefAantalBierenMetMinAlcoholPercentage(double percentage)
    {
    	return bierWinkel.geefAantalBierenMetMinAlcoholPercentage(percentage);
    }
    
    public List<String> geefLijstAlleBierenMetMinAlcoholPercentage(double percentage)
    {
    	return bierWinkel.geefAlleBierenMetMinAlcoholPercentage(percentage).stream().map(bier -> bier.toString()).collect(Collectors.toList());
    }
    
    public List<String> geefAlleBieren()
    {
        return null;
    }
    
    public String geefNamenBieren()
    {
    	StringBuilder builder = new StringBuilder();
        bierWinkel.geefNamenBieren().stream().forEach(bier -> builder.append(bier + "\n"));
        return builder.toString();
    }
    
    public String geefBierMetHoogsteAlcoholPercentage()
    {
        return bierWinkel.geefBierMetHoogsteAlcoholPercentage().toString();
    }
    
    public String geefBierMetLaagsteAlcoholPercentage()
    {
        return bierWinkel.geefBierMetLaagsteAlcoholPercentage().toString();
    }
    
    public List<String> geefGeordendOpAlcoholGehalteEnNaam()
    {
       return bierWinkel.geefGeordendOpAlcoholGehalteEnNaam().stream().map(Bier::toString).collect(Collectors.toList());
    }
    
    public String geefAlleNamenBrouwerijen()
    {
    	StringBuilder builder = new StringBuilder();
        bierWinkel.geefAlleNamenBrouwerijen().stream().forEach(bier -> builder.append(bier + "\n"));
        return builder.toString();    }
    
    public String geefAlleNamenBrouwerijenMetWoord(String woord)
    {
    	StringBuilder builder = new StringBuilder();
        bierWinkel.geefAlleNamenBrouwerijenMetWoord(woord).stream().forEach(bier -> builder.append(bier + "\n"));
        return builder.toString();
    }

    public String opzettenAantalBierenPerSoort()
    {   //naar BierWinkel --> map<String, Long>
        bierWinkel.opzettenAantalBierenPerSoort().forEach((key, value) -> System.out.printf("%s : %d\n", key, value));
        return "pray";
    }

    public String opzettenOverzichtBierenPerSoort()
    {  // naar BierWinkel --> map<String, List<Bier>>
        bierWinkel.opzettenOverzichtBierenPerSoort().entrySet().stream().forEach(key -> 
        	System.out.println(key));
        return "skr";
    }

    /*
    private       String overzichtToString(Map<    > map)
    {  //hulp voor map --> String
         return null;
    }
    */

}
