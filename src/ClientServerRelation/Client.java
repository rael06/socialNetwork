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

    public Client(Object _object, Object graphicClass) {
        host = Constants.HOST;
        port = Constants.PORT;
        send(_object, graphicClass);
    }

    private void send(Object object, Object graphicClass) {
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
//            System.out.println((String) ois.readObject());
//            if (ois.readBoolean()) {
//                if (graphicClass instanceof CreateMember) ((CreateMember) graphicClass).setVisible(false);
//                if (graphicClass instanceof CreateSport) ((CreateSport) graphicClass).setVisible(false);
//                if (graphicClass instanceof CreateClub) ((CreateClub) graphicClass).setVisible(false);
//            }
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
