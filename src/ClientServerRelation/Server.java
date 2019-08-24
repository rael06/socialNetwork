package ClientServerRelation;

import Constants.Constants;
import ReseauSocial.Personne;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int port = Constants.PORT;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            do {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Object o = ois.readObject();
                if (o instanceof Personne) {
                    ((Personne) o).afficher();
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
