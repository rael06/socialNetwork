package GraphicsInterfaces.Personne;

import ClientServerRelation.Client;
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

public class PersonneManager extends JDialog implements ActionListener {

    private Container managerContainer;
    private JScrollPane scroll = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel container = new JPanel();
    private JButton creer = new JButton("Créer");
    private JLabel nom = new JLabel("NOM");
    private JLabel prenom = new JLabel("PRÉNOM");
    private JLabel age = new JLabel("AGE");
    private JLabel sports = new JLabel("SPORTS");
    private JLabel clubs = new JLabel("CLUBS");

    private Client client = new Client();

    private HashMap<String, Personne> members;

    void setMembers() {
        members = (HashMap<String, Personne>) client.request("personnes", "personnes");
    }

    public PersonneManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des membres", true);
        setMembers();
        setBounds(50, 100, 1423, 600);

        managerContainer = getContentPane();
        managerContainer.setLayout(null);
        creer.addActionListener(this);
        creer.setBounds(661, 0, 100, 25);

        windowMaker();
        this.setVisible(true);
    }

    void windowMaker() {
        managerContainer.add(creer);
        displayTitles();
        displayMembers();
    }

    private void displayTitles() {
        managerContainer.add(nom);
        managerContainer.add(prenom);
        managerContainer.add(age);
        managerContainer.add(sports);
        managerContainer.add(clubs);

        for (int i = 0; i < managerContainer.getComponentCount(); i++) {
            if (i > 0) {
                managerContainer.getComponent(i)
                        .setBounds(20 + 200 * (i - 1), 30, 200, 30);
            }
        }
    }

    private void displayMembers() {

        container.removeAll();
        container.setLayout(null);

        JLabel memberName, memberFirstName, memberAge, memberSports, memberClubs;
        JButton update, delete;
        int j = 0;

        for (Map.Entry<String, Personne> member : members.entrySet()) {
            JPanel lineContainer = new JPanel();
            int x = 20, y = 30, w = 200, h = 30;
            update = new ValuedButton("Modifier", member.getValue());
            update.setActionCommand("update");
            update.addActionListener(this);

            delete = new ValuedButton("Supprimer", member.getValue());
            delete.setActionCommand("delete");
            delete.addActionListener(this);


            memberName = new JLabel(member.getValue().getNom());
            memberFirstName = new JLabel(member.getValue().getPrenom());
            memberAge = new JLabel(String.valueOf(member.getValue().getAge()));

            StringBuilder memberSportsString = new StringBuilder();
            int i = 0;
            for (Map.Entry<String, Sport> sport : member.getValue().getSports().entrySet()) {
                i++;
                if (i == 1) {
                    memberSportsString.append(String.valueOf(sport.getValue().getNom().charAt(0)).toUpperCase())
                            .append(sport.getValue().getNom().substring(1))
                            .append(", ");
                    continue;
                }
                if (i < member.getValue().getSports().entrySet().size()) {
                    memberSportsString.append(sport.getValue().getNom()).append(", ");
                } else {
                    memberSportsString.append(sport.getValue().getNom());
                }
            }
            memberSports = new JLabel(String.valueOf(memberSportsString));

            StringBuilder memberClubsString = new StringBuilder();
            i = 0;
            for (Map.Entry<String, Club> club : member.getValue().getClubs().entrySet()) {
                i++;
                if (i == 1) {
                    memberClubsString.append(String.valueOf(club.getValue().getNom().charAt(0)).toUpperCase())
                            .append(club.getValue().getNom().substring(1))
                            .append(", ");
                    continue;
                }
                if (i < member.getValue().getClubs().entrySet().size()) {
                    memberClubsString.append(club.getValue().getNom()).append(", ");
                } else {
                    memberClubsString.append(club.getValue().getNom());
                }
            }
            memberClubs = new JLabel(String.valueOf(memberClubsString));

            lineContainer.setLayout(null);
            lineContainer.setBounds(x, h * j, 200 * 7, 30);

            memberName.setBounds(0, 0, w, h);
            memberFirstName.setBounds(w, 0, w, h);
            memberAge.setBounds(w * 2, 0, w, h);
            memberSports.setBounds(w * 3, 0, w, h);
            memberClubs.setBounds(w * 4, 0, w, h);
            update.setBounds(w * 5, 0, w - 30, h);
            delete.setBounds(w * 6, 0, w - 30, h);
            update.setForeground(Color.blue);
            delete.setForeground(Color.red);

            lineContainer.add(memberName);
            lineContainer.add(memberFirstName);
            lineContainer.add(memberAge);
            lineContainer.add(memberSports);
            lineContainer.add(memberClubs);
            lineContainer.add(update);
            lineContainer.add(delete);
            container.add(lineContainer);
            j++;
        }
        container.setPreferredSize(new Dimension(1400, j * 30));
        scroll.setViewportView(container);
        int scrollHeight = j * 30 <= 500 ? j * 30 : 500;
        scroll.setBounds(0, 70, 1408, scrollHeight + 3);
        managerContainer.add(scroll);
        scroll.revalidate();
    }

    private void refresh() {
        setMembers();
        getContentPane().removeAll();
        getContentPane().repaint();
        windowMaker();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(creer)) new CreateMember(this);

        if (e.getSource() instanceof ValuedButton) {
            Personne member = (Personne) ((ValuedButton) e.getSource()).getValue();
            if (((ValuedButton) e.getSource()).getActionCommand().equals("delete")) {
                client.request("delete", member);
                if (client.getSuccess()) refresh();
            } else new CreateMember(this, member);
        }
    }
}
