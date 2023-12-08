package mainpack.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.nio.charset.StandardCharsets;


public class UDPClient {
    private static final int PORT = 56894;
    private static final int DATA_LENGTH = 256;
    private DatagramPacket packetToSend;
    private DatagramPacket packetToReceive;

    public void start() throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            packetToSend = new DatagramPacket(new byte[DATA_LENGTH], DATA_LENGTH, InetAddress.getByName("192.168.0.103"), PORT);
            packetToReceive = new DatagramPacket(new byte[DATA_LENGTH], DATA_LENGTH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            while (true) {
                System.out.println("Enter the message: \n");
                String msgOut = reader.readLine();
                byte[] temp = msgOut.getBytes();
                for (int i = 0; i < temp.length; i += DATA_LENGTH - 1) {
                    int length = DATA_LENGTH - 1;
                    int len = (i + length) < temp.length ? length : temp.length - i;
                    packetToSend.setData(temp, i, len);
                    socket.send(packetToSend);
                }
                if (msgOut.equalsIgnoreCase("end")) {
                    System.out.println("Client finished its job");
                    break;
                }
                System.out.println("Message sent. Waiting for confirmation...");
                socket.receive(packetToReceive);
                String msgIn = new String(packetToReceive.getData(), 0, packetToReceive.getLength());
                System.out.println("Confirmation received. The time of response: " + msgIn);
            }
        }
    }
}
