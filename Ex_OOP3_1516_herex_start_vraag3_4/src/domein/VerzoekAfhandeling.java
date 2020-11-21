package domein;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VerzoekAfhandeling implements Runnable{

    private final Socket socket;
    private final Zoo zoo;
    private final VerzoekLogger verzoekLogger;

    VerzoekAfhandeling(Socket connection, Zoo zoo, VerzoekLogger verzoekLogger) {
        socket = connection;
        this.zoo = zoo;
        this.verzoekLogger = verzoekLogger;
    }

    @Override
    public void run() {
    	ObjectOutputStream objectOutputStream;// = socket.getOutputStream();
    	ObjectInputStream objectInputStream; 
    	try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.flush();
	    	objectInputStream = new ObjectInputStream(socket.getInputStream());
			Verzoek verzoek = (Verzoek) objectInputStream.readObject();
			verwerkVerzoek(verzoek);
			objectOutputStream.writeObject(verzoek);
			objectOutputStream.flush();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }
        private void verwerkVerzoek(Verzoek verzoek) {
            //TODO
            String logBericht = "skr";
            verzoekLogger.log( logBericht);
            switch (verzoek.getQuery()) {
            case "query1": {
                List<Dier> li = zoo.geefDierenVanSoortMetNaam(verzoek.getParameter());
                verzoek.setResult(li);
                break;
            }
            case "query2": {
                Double gemiddelde = zoo.geefGemiddeldeGewichtVanDierenInGebouwMetNaam(verzoek.getParameter());
                List<Double> li = new ArrayList<>(Arrays.asList(gemiddelde));
                verzoek.setResult(li);
                break;
            }
            case "query3": {
                List<String> li;
                try {
                    li = zoo.geefNamenVanDierenVanVerzorgerMetNummer(Integer.parseInt(verzoek.getParameter()));
                } catch (NumberFormatException ex) {
                    System.out.println("Geen numerieke waarde");
                    li =new ArrayList<>();
                }
                verzoek.setResult(li);
                break;
            }
            case "query4": {
                List<Verzorger> li = zoo.verzorgersInGebouwMetNaam(verzoek.getParameter());
                verzoek.setResult(li);
                break;
            }
            default:
                verzoek.setResult(new ArrayList<>());
            }
            verzoekLogger.log(String.format("%s afgehandeld", logBericht) );
    }
    
}
