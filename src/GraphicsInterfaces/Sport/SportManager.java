package GraphicsInterfaces.Sport;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
import GraphicsInterfaces.ValuedButton;
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

    private Container managerContainer;
    private JScrollPane scroll = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel container = new JPanel();
    private JButton creer = new JButton("Créer");
    private JLabel sportsLabel = new JLabel("SPORTS");

    void setSports() {
        sports = (HashMap<String, Sport>) client.request("sports", "sports");
    }

    public SportManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des sports", true);
        setSports();
        setBounds(50, 100, 623, 500);

        managerContainer = getContentPane();
        managerContainer.setLayout(null);

        creer.addActionListener(this);
        creer.setBounds(250, 0, 100, 30);

        sportsLabel.setBounds(275, 30, 100, 30);

        windowMaker();

        this.setVisible(true);
    }

    void windowMaker() {
        managerContainer.add(creer);
        managerContainer.add(sportsLabel);
        displaySports();
    }

    private void displaySports() {
        container.removeAll();
        container.setLayout(null);

        JLabel sportName;
        JButton update, delete;
        int j = 0;

        for (Map.Entry<String, Sport> sport : sports.entrySet()) {
            JPanel lineContainer = new JPanel();

            int x = 20, y = 30, w = 200, h = 30;
            update = new ValuedButton("Modifier", sport.getValue());
            update.setActionCommand("update");
            update.addActionListener(this);

            delete = new ValuedButton("Supprimer", sport.getValue());
            delete.setActionCommand("delete");
            delete.addActionListener(this);

            sportName = new JLabel(String.valueOf(sport.getValue().getNom().charAt(0)).toUpperCase() +
                    sport.getValue().getNom().substring(1));

            lineContainer.setLayout(null);
            lineContainer.setBounds(x, h * j, 600, 30);

            sportName.setBounds(0, 0, w, h);
            update.setBounds(w, 0, w - 60, h);
            delete.setBounds(w * 2, 0, w - 60, h);
            update.setForeground(Color.blue);
            delete.setForeground(Color.red);

            lineContainer.add(sportName);
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
        setSports();
        getContentPane().removeAll();
        getContentPane().repaint();
        windowMaker();
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
}
