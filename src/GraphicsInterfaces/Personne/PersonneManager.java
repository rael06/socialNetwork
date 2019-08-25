package GraphicsInterfaces.Personne;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
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

    private JButton creer = new JButton("Créer");
    private JLabel nom = new JLabel("nom");
    private JLabel prenom = new JLabel("prenom");
    private JLabel age = new JLabel("age");
    private JLabel sports = new JLabel("sports");
    private JLabel clubs = new JLabel("clubs");

    private Client client = new Client();

    private HashMap<String, Personne> members;

    public void setMembers() {
        members = (HashMap<String, Personne>) client.request("personnes");
    }

    public PersonneManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des membres", true);
        setMembers();
        creer.addActionListener(this);

//        setResizable(true);
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2] + 1000,
                ReseauSocialManager.ORIG_BOUNDS[3] + 500
        );

        Container personneManagerContainer = getContentPane();
        personneManagerContainer.setLayout(null);
        personneManagerContainer.add(creer);
        personneManagerContainer.getComponent(0).setBounds(0, 0, 90, 30);

        windowMaker(personneManagerContainer);
        this.setVisible(true);
    }

    private void windowMaker(Container personneManagerContainer) {
        displayTitles(personneManagerContainer);
        displayMembers(personneManagerContainer);
    }

    private void displayMembers(Container personneManagerContainer) {
        JLabel memberName, memberFirstName, memberAge, memberSports, memberClubs;
        JButton update, delete;
        int j = 1;
        for (Map.Entry<String, Personne> member : members.entrySet()) {
            j++;
            int x = 20, y = 30, w = 200, h = 30;
            update = new JButton("Modifier");
            update.setActionCommand(member.getValue().getNom());
//            update.setActionCommand(String.valueOf(member.getValue().getId()));
            update.addActionListener(this);

            delete = new JButton("Supprimer");
            delete.setActionCommand(member.getValue().getNom());
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
                if (i < member.getValue().getSports().entrySet().size()) {
                    memberClubsString.append(club.getValue().getNom()).append(", ");
                } else {
                    memberClubsString.append(club.getValue().getNom());
                }
            }
            memberClubs = new JLabel(String.valueOf(memberClubsString));

            memberName.setBounds(x, y * j, w, h);
            memberFirstName.setBounds(x + w, y * j, w, h);
            memberAge.setBounds(x + w * 2, y * j, w, h);
            memberSports.setBounds(x + w * 3, y * j, w, h);
            memberClubs.setBounds(x + w * 4, y * j, w, h);
            update.setBounds(x + w * 5, y * j, w, h);

            personneManagerContainer.add(memberName);
            personneManagerContainer.add(memberFirstName);
            personneManagerContainer.add(memberAge);
            personneManagerContainer.add(memberSports);
            personneManagerContainer.add(memberClubs);
            personneManagerContainer.add(update);
            personneManagerContainer.add(delete);
        }
    }

    private void displayTitles(Container personneManagerContainer) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(creer)) new CreateMember(this);
        System.out.println(e.getActionCommand());
    }
}
