package mainpack.TCP;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {
    public static final int PORT = 56894;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String timeReceiving;

    public void start() throws IOException {

        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                byte[] buf = new byte[256];
                int var;
                StringBuilder sb = new StringBuilder();

                while ((var = is.read(buf)) > -1) {
                    sb.append(new String(buf, 0, var));
                }
                timeReceiving = formatter.format(LocalTime.now());
                String msgIn = sb.toString();
                if (msgIn.equalsIgnoreCase("end")) {
                    System.out.println("Confirmation received.\n The server finished its job");
                    socket.close();
                    server.close();
                    break;
                }
                System.out.println("В " + timeReceiving + " received a message: \"" + msgIn + "\". Client address: " + socket.getInetAddress().getHostAddress());
                String msgOut = timeReceiving;
                os.write(msgOut.getBytes());
                os.flush();
                socket.shutdownOutput();
                System.out.println("sent to the sender.");
            }
        }
    }
}
