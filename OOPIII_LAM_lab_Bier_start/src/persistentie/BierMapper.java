package persistentie;

import domein.Bier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.Line;


public class BierMapper {

    public List<Bier> inlezenBieren(String naamBestand) {
    	File file = new File(naamBestand);
    	List<Bier> outputList = new ArrayList<>();
    	try {
			Scanner fileReader = new Scanner(file);
			while(fileReader.hasNext()) {
				String[] lineStringSplitted = fileReader.nextLine().split(" ");
				String naamString = lineStringSplitted[0];
				String typString = lineStringSplitted[1];
				double percentage = Double.parseDouble(lineStringSplitted[2]);
				double beoordeling = Double.parseDouble(lineStringSplitted[3]);
				String brouwerijString = new String();
				for(int i = 4; i < lineStringSplitted.length; i++) {
					brouwerijString += lineStringSplitted[i] + " ";
				}
				Bier bier = new Bier(naamString, typString, percentage, beoordeling, brouwerijString);
				System.out.println(bier);
				outputList.add(bier);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	return outputList;
    }
}
