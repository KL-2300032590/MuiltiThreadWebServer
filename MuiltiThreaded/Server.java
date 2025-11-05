package MuiltiThreaded;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Hello from server");
                out.flush();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 1982;
        Server server = new Server();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(10000);
            System.out.println("Server started on port: " + port);

            while (true) {
                Socket accpSocket = serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(accpSocket));
                thread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
