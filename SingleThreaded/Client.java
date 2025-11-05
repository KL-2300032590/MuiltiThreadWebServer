package SingleThreaded;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client 
{
    public void run() throws Exception
    {
      int port = 1982;
      InetAddress address = InetAddress.getByName("localhost");
      Socket socket= new Socket(address, port);
      PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
      BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      toSocket.println("Hello from the client");
      String response = fromSocket.readLine();
      System.out.println("Response from the server: " + response);
        toSocket.close();   
        fromSocket.close();
        socket.close();
    } 
    public static void main(String[] args) throws Exception
    {
        try{
        Client client = new Client();
        client.run();
        } catch(Exception e){
            e.printStackTrace();
        }   
    }   
}
