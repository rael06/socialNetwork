package GraphicsInterfaces;

import ClientServerRelation.Client;

import javax.swing.*;

abstract class ClientRelation extends JDialog {
    private Client client;

    public ClientRelation() {
        client = new Client();
    }
}
