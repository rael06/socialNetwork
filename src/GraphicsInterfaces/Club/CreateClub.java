package GraphicsInterfaces.Club;

import ClientServerRelation.Client;
import ReseauSocial.Club;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClub extends JDialog implements ActionListener {

    private Client client = new Client();
    private Container createClub;
    private ClubManager clubManager;
    private Club club = null;

    private JButton cancel = new JButton("Annuler");
    private JButton create = new JButton("Valider");

    private Label nameLabel = new Label("Nom");

    private TextField name = new TextField();

    CreateClub(ClubManager clubManager) {
        super(clubManager, "Création du club", true);
        this.clubManager = clubManager;
        create();
        addElements();

        this.setVisible(true);
    }

    CreateClub(ClubManager clubManager, Club club) {
        super(clubManager, "Modification du club", true);
        this.clubManager = clubManager;
        this.club = club;
        create();
        update(club);
        addElements();

        this.setVisible(true);
    }

    private void addElements() {
        // First element basic bounds
        int x = 20, y = 30, w = 50, h = 20;

        // Button basic bounds
        int xb = this.getWidth() / 2, yb = this.getHeight() - 80, wb = 80, hb = 20;

        // Buttons
        cancel.addActionListener(this);
        cancel.setBounds(xb - wb - 20, yb, wb, hb);
        createClub.add(cancel);

        create.addActionListener(this);
        create.setBounds(xb + 20, yb, wb, hb);
        createClub.add(create);

        // Basic elements
        // sport info fields
        nameLabel.setBounds(x, y, w, h);
        createClub.add(nameLabel);

        name.setBounds(x + w, y, w + 150, h);
        createClub.add(name);
        // !sport info fields
    }

    private void create() {
        client = new Client();
        setBounds(50, 100, 300, 200);
        createClub = getContentPane();
        createClub.setLayout(null);
    }

    private void update(Club club) {
        name = new TextField(club.getNom());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            int clubId = club == null ? 0 : club.getId();
            String clubName = name.getText();
            Club club = new Club(clubId, clubName);
            client.request("create", club);
            if (client.getSuccess()) {
                this.setVisible(false);
                refresh();
            }
        }
        // !create
    }

    private void refresh() {
        clubManager.setClubs();
        clubManager.getContentPane().removeAll();
        clubManager.getContentPane().repaint();
        clubManager.windowMaker();
    }
}

