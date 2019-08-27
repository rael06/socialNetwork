package GraphicsInterfaces.Club;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
import GraphicsInterfaces.Sport.CreateSport;
import GraphicsInterfaces.ValuedButton;
import ReseauSocial.Club;
import ReseauSocial.Sport;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ClubManager extends JDialog implements ActionListener {

    private HashMap<String, Club> clubs;

    private Client client = new Client();

    private Container clubManagerContainer;
    private JButton creer = new JButton("Créer");
    private JLabel clubsLabel = new JLabel("Clubs");

    void setClubs() {
        clubs = (HashMap<String, Club>) client.request("clubs", "clubs");
    }

    public ClubManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des clubs", true);
        setClubs();
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3]
        );

        clubManagerContainer = getContentPane();
        clubManagerContainer.setLayout(null);

        creer.addActionListener(this);
        creer.setBounds(0, 0, 90, 30);

        windowMaker();

        this.setVisible(true);
    }

    void windowMaker() {
        clubManagerContainer.add(creer);
        clubManagerContainer.add(clubsLabel);
        displayClubs();
    }

    private void displayClubs() {
        JLabel clubName;
        JButton update, delete;
        int j = 1;

        for (Map.Entry<String, Club> club : clubs.entrySet()) {
            j++;
            int x = 20, y = 30, w = 200, h = 30;
            update = new ValuedButton("Modifier", club.getValue());
            update.setActionCommand("update");
            update.addActionListener(this);

            delete = new ValuedButton("Supprimer", club.getValue());
            delete.setActionCommand("delete");
            delete.addActionListener(this);

            clubName = new JLabel(club.getValue().getNom());

            clubName.setBounds(x, y * j, w, h);
            update.setBounds(x + w, y * j, w - 60, h);
            delete.setBounds(x + w * 2, y * j, w - 60, h);

            clubManagerContainer.add(clubName);
            clubManagerContainer.add(update);
            clubManagerContainer.add(delete);
//            clubManagerContainer.add(new JScrollPane(clubManagerContainer));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(creer)) new CreateClub(this);

        if (e.getSource() instanceof ValuedButton) {

            Club club = (Club) ((ValuedButton) e.getSource()).getValue();

            if (((ValuedButton) e.getSource()).getActionCommand().equals("delete")) {
                client.request("delete", club);
                if (client.getSuccess()) refresh();

            } else new CreateClub(this, club);
        }
    }

    private void refresh() {
        setClubs();
        getContentPane().removeAll();
        getContentPane().repaint();
        windowMaker();
    }
}
