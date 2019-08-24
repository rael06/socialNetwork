package ClientServerRelation;

import Constants.Constants;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String host;
    private int port;

    public Client(Object _object) {
        host = Constants.HOST;
        port = Constants.PORT;
        send(_object);
    }

    private void send(Object object) {
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
