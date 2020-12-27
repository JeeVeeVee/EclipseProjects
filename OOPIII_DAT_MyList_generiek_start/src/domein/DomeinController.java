package domein;

import java.io.File;
import java.util.List;

import persistentie.PersistentieController;

public class DomeinController {
    
    private PersistentieController pc = new PersistentieController();
    
    public void persisteerBierGegevensAlsObject(String tekstFileNaam, String objectFileNaam){    
    	System.out.println("stap 3");
        PersistentieController pc = new PersistentieController();
        List<Bier> listBier = pc.leesBieren(new File(tekstFileNaam));
        MyListIterable<Bier> myListIterable = new MyListIterable<>();
        listBier.forEach(bier -> myListIterable.insertAtBack(bier));
        //listBier.forEach(myListIterable::insertAtBack);
        pc.persisteerObject(myListIterable, new File(objectFileNaam));
        System.out.println("done");
    }
    
}
