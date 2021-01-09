package domein;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import persistentie.VliegmaatschappijMapper;

public class VliegmaatschappijRepository 
{
	private final List<Vliegmaatschappij> maatschappijen;
    private final VliegmaatschappijMapper vm;
	
    
    public VliegmaatschappijRepository() 
    {
		vm = new VliegmaatschappijMapper();
        maatschappijen = vm.leesTekstBestand("airlines.txt");
	}


	public List<Vliegmaatschappij> getMaatschappijen() {
		return maatschappijen;
	}


	public List<Vliegmaatschappij> geefAlleAirlinesMetMinstensAantalPartners(int aantal) {
		return maatschappijen.stream().filter(m -> m.getPartners().size() >= aantal).collect(Collectors.toList());
					  
	}


	public List<Vliegmaatschappij> geefAirlinesAlfabetischGesorteerd() {
		return maatschappijen.stream().sorted(Comparator.comparing(Vliegmaatschappij::getNaam)).collect(Collectors.toList());
	}


	public List<Vliegmaatschappij> geefAirlinesGesorteerdVolgensAantalPartners() {
		return maatschappijen.stream().sorted(Comparator.comparingInt(vm -> vm.getPartners().size())).collect(Collectors.toList());
	}


	public Map<Vliegmaatschappij,Integer> geefAirlinesAantalKeerPartner() 
	{
		return maatschappijen.stream().collect(Collectors.toMap(vm -> vm, vm -> vm.getPartners().size()));
	}
	
	public String geefEersteAirlineStartendMet(String woord)
	{
		return maatschappijen.stream().filter(vm -> vm.getNaam().matches(String.format("^%s.*", woord))).findAny().map(vm-> vm.getNaam()).orElse("notfound");
	}
	
	public Vliegmaatschappij geefEenAirlineMetPartner(String partner)
	{
		
		return maatschappijen.stream()
				.filter(m -> m.getPartners().contains(partner))
				.findAny()
				.orElse(null);	}
    
}
