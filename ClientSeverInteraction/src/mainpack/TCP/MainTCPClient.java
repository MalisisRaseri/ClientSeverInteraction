package mainpack.TCP;

public class MainTCPClient {

    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        System.out.println("The client is started");
        client.start();
        System.out.println("The client is finished");
    }

}
