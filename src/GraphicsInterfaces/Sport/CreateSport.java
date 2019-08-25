package GraphicsInterfaces.Sport;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
import ReseauSocial.Sport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSport extends JDialog implements ActionListener {

    private Client client;
    private Container createSport;

    private JButton cancel = new JButton("Annuler");
    private JButton create = new JButton("Créer");

    private Label nameLabel = new Label("Nom");

    private TextField name = new TextField();

    CreateSport(SportManager sportManager) {
        super(sportManager, "Création du sport", true);
//        setResizable(true);
        client =  new Client();
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3]
        );
        createSport = getContentPane();
        createSport.setLayout(null);
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
        createSport.add(cancel);

        create.addActionListener(this);
        create.setBounds(xb + 20, yb, wb, hb);
        createSport.add(create);

        // Basic elements
        // sport info fields
        nameLabel.setBounds(x, y, w, h);
        createSport.add(nameLabel);

        name.setBounds(x + w, y, w, h);
        createSport.add(name);
        // !sport info fields
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            String sportName = name.getText();
            Sport sport = new Sport(sportName);
            client.contact(sport);
        }
        // !create
    }
}

