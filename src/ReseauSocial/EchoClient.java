package ReseauSocial;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                ObjectInputStream ois = new ObjectInputStream(echoSocket.getInputStream());
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Entrez 'reseauSocial' pour afficher l'objet ReseauSocial");
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                Object readObject = ois.readObject();
                try {
                    ReseauSocial result = (ReseauSocial) readObject;
                    result.afficher();
                } catch (ClassCastException e) {
                    System.out.println((String) readObject);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

