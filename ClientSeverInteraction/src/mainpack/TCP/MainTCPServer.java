package mainpack.TCP;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainTCPServer {

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        System.out.println("Server started");
        try {
            server.start();
        } catch (IOException ex) {
            Logger.getLogger(MainTCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
