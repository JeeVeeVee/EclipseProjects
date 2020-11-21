package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PrimitiveIterator.OfDouble;


public class PingClient {
    private InetAddress host ;
    private String hostName = "localhost"; //default
    private int portnr = 5555;  //default
    private final int PINGAANTAL = 10;
    private final int TOKEN_TIMESTAMP = 2; //positie in packet
    private final int MAX_WAIT_TIME = 1000;
    private long min = 999999, max = 0, somRTT = 0;
    private int aangekomen = 0, verloren = 0;

    public static void main(String[] args) {
        new PingClient().run(args);
    }

    public void run(String args[])  {
        try {
            if (args.length>0) {
                hostName =args[0];
            }
            if (args.length ==2){
                portnr = Integer.parseInt(args[1]);
            }
            //maak netwerkconnectie
            host = InetAddress.getByName(hostName);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setSoTimeout(MAX_WAIT_TIME); 
            // wachttijd -> verlopen -> SocketTimeOutExecption gegooid

            // verstuur PINGAANTAL keer een bericht met huidig tijdstip
            for(int pingNr=1; pingNr <= PINGAANTAL; pingNr++) {
            	// data voor bericht:
            	SimpleDateFormat timeNow = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            	String timedStr = timeNow.format(new Date(System.currentTimeMillis()));
            	String message = String.format("Ping #%d %s (%s)", pingNr, System.currentTimeMillis(), timedStr );
                // verpak bericht, verstuur en wacht op antwoord print de ontvangenData
            	DatagramPacket ping_verzoekDatagramPacket = new DatagramPacket(message.getBytes(), message.length(),
            			host, portnr);
            	datagramSocket.send(ping_verzoekDatagramPacket);
            	DatagramPacket ping_antwoord = new DatagramPacket(new byte[message.length()], message.length());
            	//wacht op antwoord 
            	try {
            		datagramSocket.receive(ping_antwoord);
            		aangekomen++;
            		printData(ping_antwoord);
					
				} catch (SocketTimeoutException e) {
					System.out.printf("Ping %d No response was received from server", pingNr);
					verloren++;
				}
            }                      
            System.out.printf("min: %d, max: %d, gem: %d, packets loss: %.0f%%%n", min, max, somRTT/PINGAANTAL
                                                                                                ,(PINGAANTAL-aangekomen)/(double)PINGAANTAL*100);

        } 
          //end-try
          catch(UnknownHostException ue) {
        	   System.out.println("onbekende host : " + ue.getMessage());
           } catch (IOException ioe) {
        	   System.out.println("probleem  : " + ioe.getMessage());
		}
                
    }

    private void printData(DatagramPacket request) {
    	//haal de info uit het ontvangen packet, toon op de console
    	long rtt = 0;
    	String response = new String(request.getData(), 0, request.getLength());
    	String[] tokens = response.split(" ");
    	long verzonden_timestamp = Long.valueOf(tokens[2]);
    	long ontvangen_timestamp = System.currentTimeMillis();
    	rtt = verzonden_timestamp - ontvangen_timestamp;
        System.out.printf("%s  Received from %s (RTT=%dms)%n", response,  request.getAddress().getHostAddress(), rtt);
        updateRTTs(rtt);  
    }

    private void updateRTTs(long rtt) {
    	//bereken  min, max, somRTT (voor gemiddelde
    	if(min > rtt) {
    		min = rtt;
    	}
    	if(max < rtt) {
    		max = rtt;
    	}
    	somRTT += rtt;
    }
}