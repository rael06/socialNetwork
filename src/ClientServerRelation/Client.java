package ClientServerRelation;

import Constants.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private String host;
    private int port;
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public Client() {
        host = Constants.HOST;
        port = Constants.PORT;
    }

    public Object request(String command, Object object) {
        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            Map<String, Object> request = new HashMap<>();
            request.put(command, object);
            oos.writeObject(request);
            oos.flush();
            Object o;
            try {
                o = ois.readObject();
                if (o instanceof Boolean) success = (Boolean) o;
            } catch (Exception e) {
                o = null;
                e.printStackTrace();
            }
            if (o != null) return o;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
