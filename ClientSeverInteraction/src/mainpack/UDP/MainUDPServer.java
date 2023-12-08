package mainpack.UDP;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUDPServer {

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        System.out.println("Server started");
        try {
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(MainUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
