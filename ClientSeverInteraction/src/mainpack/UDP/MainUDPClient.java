package mainpack.UDP;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUDPClient {

    public static void main(String[] args) {
        UDPClient client = new UDPClient();
        System.out.println("Client started");
        try {
            client.start();
        } catch (IOException ex) {
            Logger.getLogger(MainUDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
