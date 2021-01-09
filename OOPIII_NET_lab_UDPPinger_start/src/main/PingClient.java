package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PingClient {
    private InetAddress host ;
    private String hostName = "localhost"; //default
    private int portnr = 5555;  //default
    private final int PINGAANTAL = 10;
    private final int TOKEN_TIMESTAMP = 2; //positie in packet
    private final int MAX_WAIT_TIME = 1000;
    private long min = 999999, max = 0, somRTT = 0;
    private int aangekomen = 0;

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
		

          // verstuur PINGAANTAL keer een bericht met huidig tijdstip
          int pingNr=1;
          for (int ping = 1; ping < PINGAANTAL; ping ++) {
              // data voor bericht:
        	  SimpleDateFormat timeNow = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
              String timedStr = timeNow.format(new Date(System.currentTimeMillis()));
              String message = String.format("Ping #%d %s (%s)", pingNr, System.currentTimeMillis(), timedStr );
              // verpak bericht, verstuur en wacht op antwoord print de ontvangenData
              // update de teller
              DatagramPacket pingverzoekPacket = new DatagramPacket(message.getBytes(), message.length(), host, portnr);
              datagramSocket.send(pingverzoekPacket);
              DatagramPacket pingAntwoordPacket = new DatagramPacket(message.getBytes(), message.length());
              try {
				datagramSocket.receive(pingAntwoordPacket);
				aangekomen++;
				printDataEnUpdateStat(pingAntwoordPacket);
              } catch (Exception e) {
				// TODO: handle exception
              }
          	}
       } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
    }

    private void printDataEnUpdateStat(DatagramPacket request) {
        //haal de info uit het ontvangen packet, toon op de console
        String response = new String(request.getData(), request.getOffset(), request.getLength());
        String[] tokens = response.split(" ");
        long verzonden_timestamp = Long.parseLong(tokens[TOKEN_TIMESTAMP]);
        long ontvangen_timestamp = System.currentTimeMillis();
        long rtt = ontvangen_timestamp - verzonden_timestamp;
        System.out.printf("%s  Received from %s (RTT=%dms)%n", response,  request.getAddress().getHostAddress(), rtt);
        updateRTTs(rtt);
    }

    private void updateRTTs(long rtt) {
        //bereken  min, max, somRTT (voor gemiddelde)
        if (min > rtt){
            min = rtt;
        }
        if (max < rtt) {
            max = rtt;
        }
        somRTT += rtt;
    }
}