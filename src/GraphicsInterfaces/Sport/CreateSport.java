package GraphicsInterfaces.Sport;

import ClientServerRelation.Client;
import ReseauSocial.Sport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSport extends JDialog implements ActionListener {

    private Client client = new Client();
    private Container createSport;
    private SportManager sportManager;
    private Sport sport = null;

    private JButton cancel = new JButton("Annuler");
    private JButton create = new JButton("Valider");

    private Label nameLabel = new Label("Nom");

    private TextField name = new TextField();

    CreateSport(SportManager sportManager) {
        super(sportManager, "Création du sport", true);
        this.sportManager = sportManager;
        create();
        addElements();

        this.setVisible(true);
    }

    CreateSport(SportManager sportManager, Sport sport) {
        super(sportManager, "Modification du sport", true);
        this.sportManager = sportManager;
        this.sport = sport;
        create();
        update(sport);
        addElements();

        this.setVisible(true);
    }

    private void create() {
        client = new Client();
        setBounds(50, 100, 300, 200);
        createSport = getContentPane();
        createSport.setLayout(null);
    }

    private void update(Sport sport) {
        name = new TextField(sport.getNom());
    }

    private void addElements() {
        // First element basic bounds
        int x = 20, y = 30, w = 50, h = 20;

        // Button basic bounds
        int xb = this.getWidth() / 2, yb = this.getHeight() - 80, wb = 80, hb = 20;

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

        name.setBounds(x + w, y, w + 150, h);
        createSport.add(name);
        // !sport info fields
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            int sportId = sport == null ? 0 : sport.getId();
            String sportName = name.getText();
            Sport sport = new Sport(sportId, sportName);
            client.request("create", sport);
            if (client.getSuccess()) {
                this.setVisible(false);
                refresh();
            }
        }
        // !create
    }

    private void refresh() {
        sportManager.setSports();
        sportManager.getContentPane().removeAll();
        sportManager.getContentPane().repaint();
        sportManager.windowMaker();
    }
}

