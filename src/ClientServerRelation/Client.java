package ClientServerRelation;

import Constants.Constants;
import GraphicsInterfaces.Club.CreateClub;
import GraphicsInterfaces.Personne.CreateMember;
import GraphicsInterfaces.Sport.CreateSport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String host;
    private int port;

    public Client() {
        host = Constants.HOST;
        port = Constants.PORT;
    }

    public void contact(Object object) {
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ) {
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
