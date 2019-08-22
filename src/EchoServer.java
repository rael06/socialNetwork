// https://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoServer.java
// Voir https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("reseauSocial")) {
                    System.out.println(inputLine);
                    oos.writeObject((new ReseauSocial()));
                    oos.flush();
                } else {
//                    System.out.println("Tapez 'reseauSocial'");
                    oos.writeObject("Tapez 'reseauSocial'");
                    oos.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
