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

    private Container personneManagerContainer;
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

        personneManagerContainer = getContentPane();
        personneManagerContainer.setLayout(null);
        creer.addActionListener(this);
        creer.setBounds(661, 0, 100, 25);

        windowMaker();
        this.setVisible(true);
    }

    void windowMaker() {
        personneManagerContainer.add(creer);
        displayTitles();
        displayMembers();
    }

    private void displayTitles() {
        personneManagerContainer.add(nom);
        personneManagerContainer.add(prenom);
        personneManagerContainer.add(age);
        personneManagerContainer.add(sports);
        personneManagerContainer.add(clubs);

        for (int i = 0; i < personneManagerContainer.getComponentCount(); i++) {
            if (i > 0) {
                personneManagerContainer.getComponent(i)
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
            JPanel memberContainer = new JPanel();
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
                if (i < member.getValue().getClubs().entrySet().size()) {
                    memberClubsString.append(club.getValue().getNom()).append(", ");
                } else {
                    memberClubsString.append(club.getValue().getNom());
                }
            }
            memberClubs = new JLabel(String.valueOf(memberClubsString));

            memberContainer.setLayout(null);
            memberContainer.setBounds(20, h * j, 200 * 7, 30);

            memberName.setBounds(0, 0, w, h);
            memberFirstName.setBounds(w, 0, w, h);
            memberAge.setBounds(w * 2, 0, w, h);
            memberSports.setBounds(w * 3, 0, w, h);
            memberClubs.setBounds(w * 4, 0, w, h);
            update.setBounds(w * 5, 0, w - 30, h);
            delete.setBounds(w * 6, 0, w - 30, h);

            memberContainer.add(memberName);
            memberContainer.add(memberFirstName);
            memberContainer.add(memberAge);
            memberContainer.add(memberSports);
            memberContainer.add(memberClubs);
            memberContainer.add(update);
            memberContainer.add(delete);
            container.add(memberContainer);
            j++;
        }
        container.setPreferredSize(new Dimension(1400, j * 30));
        scroll.setViewportView(container);
        int scrollHeight = j * 30 <= 500 ? j * 30 : 100;
        scroll.setBounds(0, 70, 1408, scrollHeight + 3);
        personneManagerContainer.add(scroll);
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

    private void refresh() {
        setMembers();
        container.removeAll();
        getContentPane().removeAll();
        getContentPane().repaint();
        windowMaker();
    }
}
