package GraphicsInterfaces.Club;

import ClientServerRelation.Client;
//import GraphicsInterfaces.ClientRelation;
import GraphicsInterfaces.ReseauSocialManager;
import ReseauSocial.Club;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClub extends JDialog implements ActionListener {

    private Client client = new Client();

    private Container createClub;

    private JButton cancel = new JButton("Annuler");
    private JButton create = new JButton("Créer");

    private Label nameLabel = new Label("Nom");

    private TextField name = new TextField();

    CreateClub(ClubManager clubManager) {
//        super().super(clubManager, "Création du club", true);
        super(clubManager, "Création du club", true);
//        setResizable(true);
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3]
        );
        createClub = getContentPane();
        createClub.setLayout(null);
        addElements();

        this.setVisible(true);
    }

    private void addElements() {
        // First element basic bounds
        int x = 20, y = 30, w = 150, h = 20;

        // Button basic bounds
        int xb = this.getWidth() / 2, yb = this.getHeight() - 100, wb = 80, hb = 20;

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

        name.setBounds(x + w, y, w, h);
        createClub.add(name);
        // !sport info fields
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            String clubName = name.getText();
            Club club = new Club(clubName);
            client.create(club);
        }
        // !create
    }
}

