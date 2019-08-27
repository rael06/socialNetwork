package GraphicsInterfaces.Sport;

import ClientServerRelation.Client;
import GraphicsInterfaces.Personne.CreateMember;
import GraphicsInterfaces.ReseauSocialManager;
import GraphicsInterfaces.ValuedButton;
import ReseauSocial.Club;
import ReseauSocial.Personne;
import ReseauSocial.Sport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SportManager extends JDialog implements ActionListener {

    private HashMap<String, Sport> sports;

    private Client client = new Client();

    private Container sportManagerContainer;
    private JButton creer = new JButton("Créer");
    private JLabel sportsLabel = new JLabel("Sports");

    void setSports() {
        sports = (HashMap<String, Sport>) client.request("sports", "sports");
    }

    public SportManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des sports", true);
        setSports();
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2] - 100,
                ReseauSocialManager.ORIG_BOUNDS[3] + 500
        );

        sportManagerContainer = getContentPane();
        sportManagerContainer.setLayout(null);

        creer.addActionListener(this);
        creer.setBounds(0, 0, 90, 30);

        sportsLabel.setBounds(230, 20, 100, 30);

        windowMaker();

        this.setVisible(true);
    }

    void windowMaker() {
        sportManagerContainer.add(creer);
        sportManagerContainer.add(sportsLabel);
        displaySports();
    }

    private void displaySports() {
        JLabel sportName;
        JButton update, delete;
        int j = 1;

        for (Map.Entry<String, Sport> sport : sports.entrySet()) {
            j++;
            int x = 20, y = 30, w = 200, h = 30;
            update = new ValuedButton("Modifier", sport.getValue());
            update.setActionCommand("update");
            update.addActionListener(this);

            delete = new ValuedButton("Supprimer", sport.getValue());
            delete.setActionCommand("delete");
            delete.addActionListener(this);

            sportName = new JLabel(sport.getValue().getNom());

            sportName.setBounds(x, y * j, w, h);
            update.setBounds(x + w, y * j, w - 60, h);
            delete.setBounds(x + w * 2, y * j, w - 60, h);

            sportManagerContainer.add(sportName);
            sportManagerContainer.add(update);
            sportManagerContainer.add(delete);
//            personneManagerContainer.add(new JScrollPane(personneManagerContainer));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(creer)) new CreateSport(this);

        if (e.getSource() instanceof ValuedButton) {

            Sport sport = (Sport) ((ValuedButton) e.getSource()).getValue();

            if (((ValuedButton) e.getSource()).getActionCommand().equals("delete")) {
                client.request("delete", sport);
                if (client.getSuccess()) refresh();

            } else new CreateSport(this, sport);
        }
    }

    private void refresh() {
        setSports();
        getContentPane().removeAll();
        getContentPane().repaint();
        windowMaker();
    }
}
