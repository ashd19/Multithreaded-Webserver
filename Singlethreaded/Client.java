import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.InputStreamReader;

public class Client {

    public void run() throws IOException {
        int port = 8010;
        String host = "localhost";
        InetAddress address = InetAddress.getByName(host);

        try (Socket socket = new Socket(address, port)) {
            PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            toSocket.println("Hello from client");
            toSocket.println(""); // End of request headers simulation
            String line = fromSocket.readLine();
            System.out.println("Received from server: " + line);
            toSocket.println("Closing connection");

            fromSocket.close();
            toSocket.close();
            socket.close();
        }
    }

    public static void main(String[] args) {
        Client singleThreadWebServer_Client = new Client();
        try {
            singleThreadWebServer_Client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
