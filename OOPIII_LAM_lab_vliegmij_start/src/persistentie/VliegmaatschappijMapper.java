package persistentie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import domein.Vliegmaatschappij;

public class VliegmaatschappijMapper
{
    public List<Vliegmaatschappij> leesTekstBestand
            (String naamBestand)
    {
    	List<Vliegmaatschappij> airlines=new ArrayList<>();
    	try (
			Scanner fileScanner = new Scanner(new File("airlines.txt"));
    			){
    		while(fileScanner.hasNext()) {
    			String[] splittedInput = fileScanner.nextLine().split(",");
    			Vliegmaatschappij vliegmaatschappij = new Vliegmaatschappij(splittedInput);
    			airlines.add(vliegmaatschappij);
    		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("airlines gelezen\n");
        return airlines;
    }
}

