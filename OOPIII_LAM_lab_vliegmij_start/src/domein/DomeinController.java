package domein;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import persistentie.VliegmaatschappijMapper;

public class DomeinController {
	private final VliegmaatschappijRepository vliegmijRepository;

	public DomeinController() {
		this.vliegmijRepository = new VliegmaatschappijRepository();
	}

	public String geefAirlines() {
		List<Vliegmaatschappij> lijst = vliegmijRepository.getMaatschappijen();
		return zetLijstOmNaarString(lijst);

	}

	private String zetLijstOmNaarString(List<Vliegmaatschappij> lijst) {
		return lijst.stream().map(e -> e.toString()).collect(Collectors.joining("\n"));

	}

	public List<String> geefAlleAirlinesMetMinstensAantalPartners(int aantal) {
		return vliegmijRepository.geefAlleAirlinesMetMinstensAantalPartners(aantal)
				.stream().map(Vliegmaatschappij::toString).collect(Collectors.toList());
	}

	public String geefAirlinesAlfabetischGesorteerd() {
		return vliegmijRepository.geefAirlinesAlfabetischGesorteerd().stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
	}

	public String geefAirlinesGesorteerdVolgensAantalPartners() {
		return vliegmijRepository.geefAirlinesGesorteerdVolgensAantalPartners().stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
	}

	public String geefAirlinesAantalKeerPartner() {
		Map<Vliegmaatschappij, Integer> map = vliegmijRepository.geefAirlinesAantalKeerPartner();
		return map.entrySet().stream().map(entry -> String.format("%s is %d keer partner%n", entry.getKey().getNaam(), entry.getValue()))
				.collect(Collectors.joining(""));
	}
	
	public String geefEersteAirlineStartendMet(String woord) {
		return vliegmijRepository.geefEersteAirlineStartendMet(woord);
	}
	
	public Vliegmaatschappij geefEenAirlineMetPartner(String partner) {
		return vliegmijRepository.geefEenAirlineMetPartner(partner);
	}
}
