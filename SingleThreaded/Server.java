package SingleThreaded;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Server
{
    public void run() throws IOException
    {
        int port = 1982;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        socket.close();
        
        while (true)
        {
            try
            {
            System.out.println("Server is listening on port " + port);
            Socket acceptedSocketConnection = socket.accept();
            PrintWriter toClient = new PrintWriter(acceptedSocketConnection.getOutputStream());
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedSocketConnection.getInputStream()));;
            toClient.println("Ack from the server");
            toClient.close();
            fromClient.close();
            acceptedSocketConnection.close();
            }
            catch (IOException e)
            {
                System.out.println("Socket timed out!");
            } 
        }
    }
    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println("Server is running...");
       
    }
}
