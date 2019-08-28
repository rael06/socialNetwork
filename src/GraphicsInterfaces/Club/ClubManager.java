package GraphicsInterfaces.Club;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
import GraphicsInterfaces.ValuedButton;
import ReseauSocial.Club;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ClubManager extends JDialog implements ActionListener {

    private HashMap<String, Club> clubs;

    private Client client = new Client();

    private Container managerContainer;
    private JScrollPane scroll = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel container = new JPanel();
    private JButton creer = new JButton("Créer");
    private JLabel clubsLabel = new JLabel("CLUBS");

    void setClubs() {
        clubs = (HashMap<String, Club>) client.request("clubs", "clubs");
    }

    public ClubManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des clubs", true);
        setClubs();
        setBounds(50, 100, 623, 500);

        managerContainer = getContentPane();
        managerContainer.setLayout(null);

        creer.addActionListener(this);
        creer.setBounds(250, 0, 100, 30);

        clubsLabel.setBounds(280, 30, 100, 30);

        windowMaker();

        this.setVisible(true);
    }

    void windowMaker() {
        managerContainer.add(creer);
        managerContainer.add(clubsLabel);
        displayClubs();
    }

    private void displayClubs() {

        container.removeAll();
        container.setLayout(null);

        JLabel clubName;
        JButton update, delete;
        int j = 0;

        for (Map.Entry<String, Club> club : clubs.entrySet()) {
            JPanel lineContainer = new JPanel();

            int x = 20, y = 30, w = 200, h = 30;
            update = new ValuedButton("Modifier", club.getValue());
            update.setActionCommand("update");
            update.addActionListener(this);

            delete = new ValuedButton("Supprimer", club.getValue());
            delete.setActionCommand("delete");
            delete.addActionListener(this);

            clubName = new JLabel(String.valueOf(club.getValue().getNom().charAt(0)).toUpperCase() +
                    club.getValue().getNom().substring(1));

            lineContainer.setLayout(null);
            lineContainer.setBounds(20, h * j, 600, 30);

            clubName.setBounds(x, 0, w, h);
            update.setBounds(x + w, 0, w - 60, h);
            delete.setBounds(x + w * 2, 0, w - 60, h);
            update.setForeground(Color.blue);
            delete.setForeground(Color.red);

            lineContainer.add(clubName);
            lineContainer.add(update);
            lineContainer.add(delete);

            container.add(lineContainer);
            j++;
        }
        container.setPreferredSize(new Dimension(600, j * 30));
        scroll.setViewportView(container);
        int scrollHeight = j * 30 <= 350 ? j * 30 : 350;
        scroll.setBounds(0, 70, 608, scrollHeight + 3);
        managerContainer.add(scroll);
        scroll.revalidate();
    }

    private void refresh() {
        setClubs();
        getContentPane().removeAll();
        getContentPane().repaint();
        windowMaker();
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
}
