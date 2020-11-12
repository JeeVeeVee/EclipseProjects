package domein;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatBodyUDP extends Thread {

    private final StringProperty displayArea = new SimpleStringProperty();
    private final BooleanProperty connected = new SimpleBooleanProperty();
    protected DatagramSocket datagramSocket;
    protected DatagramPacket datagramPacket;
    protected int contactedPort;
    protected InetAddress contactedAddress;
    private final String name;
    private final String host;

    public ChatBodyUDP(String statusname, String hostName) {
        host = hostName;
        name = statusname;
    }

    @Override
    public void run() {
        try {
            if (name.equalsIgnoreCase("Server")) {
                datagramSocket = new DatagramSocket(5000);
            } else {
                datagramSocket = new DatagramSocket();
                contactedPort = 5000;
                try {
                    contactedAddress = InetAddress.getByName(host);
                } catch (UnknownHostException ex) {
                    updateDisplay(ex.getMessage());
                }
                setConnected(true);
                sendPacket("Client contacted");
            }
        } catch (SocketException ex) {
            updateDisplay("\nNo datagram socket : " + ex.getMessage());
        }
        waitForPackets();
    }

    public StringProperty displayAreaProperty() {
        return displayArea;
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }

    protected void updateDisplay(String mes) {
        displayArea.set(mes);
    }

    private void setConnected(boolean isConnected) {
        connected.set(isConnected);
    }

    public void sendMessage(String message) {
        updateDisplay("\n" + name + ">>> " + message);
        sendPacket("\n" + name + ">>> " + message);
    }

    public void waitForPackets() {
        byte[] data = new byte[100];
        while (true) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(data, data.length);

                datagramSocket.receive(receivePacket);
                
                contactedPort = receivePacket.getPort();
                contactedAddress = receivePacket.getAddress();
                updateDisplay("\nPacket received:"
                        + "\nFrom host: " + contactedAddress
                        + "\nHost port: " + contactedPort
                        + "\nLength: " + receivePacket.getLength()
                        + "\nContaining:\n\t" + new String(receivePacket.getData(),
                                0, receivePacket.getLength()));
                if (!connected.get()) {
                    setConnected(true);
                }
            } catch (IOException ex) {
                updateDisplay("\n " + ex.getMessage());
            }
        }
    }

    private void sendPacket(String message) {
        DatagramPacket sendPacket = new DatagramPacket(
                message.getBytes(), message.getBytes().length, contactedAddress, contactedPort);
        try {
            datagramSocket.send(sendPacket);
        } catch (IOException ex) {
            updateDisplay("Error send packet : " + ex.getMessage());
        }
    }

}
