package GraphicsInterfaces.Personne;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
import ReseauSocial.Club;
import ReseauSocial.Personne;
import ReseauSocial.ReseauSocial;
import ReseauSocial.Sport;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateMember extends JDialog implements ActionListener, ListSelectionListener {

    private Client client;
    private Container createMember;

    private JButton cancel = new JButton("Annuler");
    private JButton create = new JButton("Créer");

    private Label nameLabel = new Label("Nom");
    private Label firstNameLabel = new Label("Prénom");
    private Label ageLabel = new Label("Age");
    private Label sportsLabel = new Label("Sports");
    private Label clubsLabel = new Label("Clubs");

    private TextField name = new TextField();
    private TextField firstName = new TextField();
    private TextField age = new TextField();

    private List<String> sportsNamesList;
    private JList sportsList;
    private List<String> memberSportsNamesList = new ArrayList<>();
    private JList memberSportsList = new JList(memberSportsNamesList.toArray());

    private List<String> clubsNamesList;
    private JList clubsList;
    private List<String> memberClubsNamesList = new ArrayList<>();
    private JList memberClubsList = new JList(memberClubsNamesList.toArray());

    CreateMember(PersonneManager personneManager) {
        super(personneManager, "Création du membre", true);
//        setResizable(true);

        client = new Client();

        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3] + 200
        );
        createMember = getContentPane();
        createMember.setLayout(null);

        HashMap<String, Sport> originSports = (HashMap<String, Sport>) client.request("sports");
        sportsNamesList = new ArrayList<>(originSports.keySet());
        sportsList = new JList(sportsNamesList.toArray());

        HashMap<String, Club> originClubs = (HashMap<String, Club>) client.request("clubs");
        clubsNamesList = new ArrayList<>(originClubs.keySet());
        clubsList = new JList(clubsNamesList.toArray());

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
        createMember.add(cancel);

        create.addActionListener(this);
        create.setBounds(xb + 20, yb, wb, hb);
        createMember.add(create);

        // Basic elements
        // member info fields
        nameLabel.setBounds(x, y, w, h);
        createMember.add(nameLabel);

        name.setBounds(x + w, y, w, h);
        createMember.add(name);

        firstNameLabel.setBounds(x, y * 2, w, h);
        createMember.add(firstNameLabel);

        firstName.setBounds(x + w, y * 2, w, h);
        createMember.add(firstName);

        ageLabel.setBounds(x, y * 3, w, h);
        createMember.add(ageLabel);

        age.setBounds(x + w, y * 3, w, h);
        createMember.add(age);
        // !member info fields

        // sports section
        sportsLabel.setBounds(x + 50, y * 3 + 50, w, h);
        createMember.add(sportsLabel);

        sportsList.setBounds(x, y * 4 + 50, 130, 200);
        sportsList.addListSelectionListener(this);
        createMember.add(sportsList);

        memberSportsList.setBounds(x + (xb - 150) - 30, y * 4 + 50, 130, 200);
        memberSportsList.addListSelectionListener(this);
        createMember.add(memberSportsList);
        // !sports section

        // club section
        clubsLabel.setBounds(x + 100 + xb, y * 3 + 50, w, h);
        createMember.add(clubsLabel);

        clubsList.setBounds(xb, y * 4 + 50, 130, 200);
        clubsList.addListSelectionListener(this);
        createMember.add(clubsList);

        memberClubsList.setBounds(xb + (xb - 150) - 30, y * 4 + 50, 130, 200);
        memberClubsList.addListSelectionListener(this);
        createMember.add(memberClubsList);
        // !club section
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            String memberName = name.getText().isEmpty() ? null : name.getText();
            String memberFirstName = firstName.getText().isEmpty() ? null : firstName.getText();
            int memberAge;
            if (!age.getText().isEmpty()) {
                memberAge = Integer.parseInt(age.getText());
            } else {
                memberAge = 0;
            }

            if (memberName != null && memberFirstName != null && memberAge != 0) {
                Personne personne = new Personne(memberName, memberFirstName, memberAge);


                for (String memberSport : memberSportsNamesList) {
                    Sport sport = new Sport(memberSport);
                    if (sport.getNom() != null) personne.setSport(sport);
                }

                for (String memberClub : memberClubsNamesList) {
                    Club club = new Club(memberClub);
                    if (club.getNom() != null) personne.setClub(club);
                }

                client.create(personne);
            }

        }
        // !create
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // sports
        if (!e.getValueIsAdjusting() && e.getSource().equals(sportsList)) {
            if (!memberSportsNamesList.contains(sportsList.getSelectedValue())) {
                memberSportsNamesList.add((String) sportsList.getSelectedValue());
                sportsNamesList.remove(sportsList.getSelectedValue());
            }

            sportsList.setListData(sportsNamesList.toArray());
            memberSportsList.setListData(memberSportsNamesList.toArray());
        }

        if (!e.getValueIsAdjusting() && e.getSource().equals(memberSportsList)) {
            sportsNamesList.add((String) memberSportsList.getSelectedValue());
            memberSportsNamesList.remove(memberSportsList.getSelectedValue());

            sportsList.setListData(sportsNamesList.toArray());
            memberSportsList.setListData(memberSportsNamesList.toArray());
        }
        // !sports

        // club
        if (!e.getValueIsAdjusting() && e.getSource().equals(clubsList)) {
            if (!memberClubsNamesList.contains(clubsList.getSelectedValue())) {
                memberClubsNamesList.add((String) clubsList.getSelectedValue());
                clubsNamesList.remove(clubsList.getSelectedValue());
            }

            clubsList.setListData(clubsNamesList.toArray());
            memberClubsList.setListData(memberClubsNamesList.toArray());
        }

        if (!e.getValueIsAdjusting() && e.getSource().equals(memberClubsList)) {
            clubsNamesList.add((String) memberClubsList.getSelectedValue());
            memberClubsNamesList.remove(memberClubsList.getSelectedValue());

            clubsList.setListData(clubsNamesList.toArray());
            memberClubsList.setListData(memberClubsNamesList.toArray());
        }
    }
}
