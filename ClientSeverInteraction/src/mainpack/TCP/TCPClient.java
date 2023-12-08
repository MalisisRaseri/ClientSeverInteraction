package mainpack.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClient {

    public static final int PORT = 56894;
    public static final String HOST = "192.168.0.103";

    public void start(){
        while(true) {
            try(Socket socket = new Socket(HOST, PORT);
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                System.out.println("Enter the message: \n");
                String msgOut = reader.readLine();
                os.write(msgOut.getBytes());
                socket.shutdownOutput();
                if(msgOut.equalsIgnoreCase("end")){
                    socket.close();
                    break;
                }
                System.out.println("Waiting...");
                StringBuilder sb = new StringBuilder();
                byte[] buf = new byte[256];
                int var;
                while((var=is.read(buf))>-1)
                {
                    sb.append(new String(buf, 0, var));
                }
                String msgIn = sb.toString();
                System.out.println("Confirmation received. Server feedback: " + msgIn);
                socket.close();
            }
            catch(IOException ioe){}
        }
    }
}

