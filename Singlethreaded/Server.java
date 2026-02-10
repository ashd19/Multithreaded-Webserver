
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
   
    public void run() {
        int port = 8010;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Accepted connection from " + clientSocket.getRemoteSocketAddress());
                    PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
                    toClient.println("Hello from single-threaded server!");
                }
           
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}