package GraphicsInterfaces.Personne;

import ClientServerRelation.Client;
import GraphicsInterfaces.ReseauSocialManager;
import ReseauSocial.Club;
import ReseauSocial.Personne;
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
import java.util.Map;

public class CreateMember extends JDialog implements ActionListener, ListSelectionListener {

    private Client client = new Client();

    private PersonneManager personneManager;

    private Container createMember;

    private Personne member = null;

    private JButton cancel = new JButton("Annuler");
    private JButton create = new JButton("Valider");

    private Label nameLabel = new Label("Nom");
    private Label firstNameLabel = new Label("Prénom");
    private Label ageLabel = new Label("Age");
    private Label sportsLabel = new Label("Sports");
    private Label clubsLabel = new Label("Clubs");
    private TextArea errorMessageTextArea = new TextArea();

    private TextField name = new TextField();
    private TextField firstName = new TextField();
    private TextField age = new TextField();

    private List<String> sportsNamesList;
    private JList sportsList;
    private JScrollPane scrollSportsList = new JScrollPane(null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    private List<String> memberSportsNamesList = new ArrayList<>();
    private JList memberSportsList = new JList(memberSportsNamesList.toArray());
    private JScrollPane scrollMemberSportsList = new JScrollPane(null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    private List<String> clubsNamesList;
    private JList clubsList;
    private JScrollPane scrollClubsList = new JScrollPane(null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    private List<String> memberClubsNamesList = new ArrayList<>();
    private JList memberClubsList = new JList(memberClubsNamesList.toArray());
    private JScrollPane scrollMemberClubsList = new JScrollPane(null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    CreateMember(PersonneManager personneManager) {
        super(personneManager, "Création du membre", true);
        this.personneManager = personneManager;
        create();
        addElements();
        this.setVisible(true);
    }

    CreateMember(PersonneManager personneManager, Personne member) {
        super(personneManager, "Modification du membre", true);
        this.personneManager = personneManager;
        this.member = member;
        create();
        update(member);
        addElements();
        this.setVisible(true);
    }

    private void create() {
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3] + 200
        );
        createMember = getContentPane();
        createMember.setLayout(null);

        HashMap<String, Sport> originSports = (HashMap<String, Sport>) client.request("sports", "sports");
        sportsNamesList = new ArrayList<>(originSports.keySet());
        sportsList = new JList(sportsNamesList.toArray());

        HashMap<String, Club> originClubs = (HashMap<String, Club>) client.request("clubs", "clubs");
        clubsNamesList = new ArrayList<>(originClubs.keySet());
        clubsList = new JList(clubsNamesList.toArray());
    }

    private void update(Personne member) {
        name = new TextField(member.getNom());
        firstName = new TextField(member.getPrenom());
        age = new TextField(String.valueOf(member.getAge()));

        for (Map.Entry<String, Sport> sport : member.getSports().entrySet()) {
            memberSportsNamesList.add(sport.getValue().getNom());
            sportsNamesList.remove(sport.getValue().getNom());
            sportsList = new JList(sportsNamesList.toArray());
            memberSportsList = new JList(memberSportsNamesList.toArray());
        }

        for (Map.Entry<String, Club> club : member.getClubs().entrySet()) {
            memberClubsNamesList.add(club.getValue().getNom());
            clubsNamesList.remove(club.getValue().getNom());
            clubsList = new JList(clubsNamesList.toArray());
            memberClubsList = new JList(memberClubsNamesList.toArray());
        }
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

        errorMessageTextArea.setBounds(xb + 50, 25, 250, 105);
        errorMessageTextArea.setForeground(Color.red);
        errorMessageTextArea.setVisible(false);
        createMember.add(errorMessageTextArea);

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

        sportsList.setPreferredSize(new Dimension(130, sportsNamesList.size()*18));
        sportsList.addListSelectionListener(this);
        scrollSportsList.setViewportView(sportsList);
        scrollSportsList.setBounds(x, y * 4 + 50, 130, 200);
        createMember.add(scrollSportsList);


        memberSportsList.setPreferredSize(new Dimension(130, memberSportsNamesList.size()*18));
        memberSportsList.addListSelectionListener(this);
        scrollMemberSportsList.setViewportView(memberSportsList);
        scrollMemberSportsList.setBounds(x + (xb - 150) - 30, y * 4 + 50, 130, 200);
        createMember.add(scrollMemberSportsList);
        // !sports section

        // club section
        clubsLabel.setBounds(x + 100 + xb, y * 3 + 50, w, h);
        createMember.add(clubsLabel);

        clubsList.setPreferredSize(new Dimension(130, memberSportsNamesList.size()*18));
        clubsList.addListSelectionListener(this);
        scrollClubsList.setViewportView(clubsList);
        scrollClubsList.setBounds(xb, y * 4 + 50, 130, 200);
        createMember.add(scrollClubsList);

        memberClubsList.setPreferredSize(new Dimension(130, memberSportsNamesList.size()*18));
        memberClubsList.addListSelectionListener(this);
        scrollMemberClubsList.setViewportView(memberClubsList);
        scrollMemberClubsList.setBounds(xb + (xb - 150) - 30, y * 4 + 50, 130, 200);
        createMember.add(scrollMemberClubsList);
        // !club section
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            String errorMessage = "";
            int memberId = member == null ? 0 : member.getId();
            String memberName = name.getText().isEmpty() ? null : name.getText();
            String memberFirstName = firstName.getText().isEmpty() ? null : firstName.getText();
            int memberAge;
            if (!age.getText().isEmpty()) {
                memberAge = Integer.parseInt(age.getText());
            } else {
                memberAge = 0;
            }

            if (memberName == null) errorMessage += "Veuillez remplir le champs 'nom' \n";
            if (memberFirstName == null) errorMessage += "Veuillez remplir le champs 'prénom' \n";
            if (memberAge <= 0) errorMessage += "L'âge doit être un nombre positif \n";
            if (memberAge >= 125) errorMessage += "Mazette, uhhhh, c'est vieux ça, record du monde !!! \n";
            if (memberSportsNamesList.isEmpty()) errorMessage += "Veuillez sélectionner au moins un 'sport' \n";
            if (memberClubsNamesList.isEmpty()) errorMessage += "Veuillez sélectionner au moins un 'club' \n";
            if (errorMessage.equals("")) {
                Personne personne = new Personne(memberId, memberName, memberFirstName, memberAge);


                for (String memberSport : memberSportsNamesList) {
                    Sport sport = new Sport(memberSport);
                    if (sport.getNom() != null) personne.setSport(sport);
                }

                for (String memberClub : memberClubsNamesList) {
                    Club club = new Club(memberClub);
                    if (club.getNom() != null) personne.setClub(club);
                }

                client.request("create", personne);
                if (client.getSuccess()) {
                    this.setVisible(false);
                    refresh();
                }
            } else {
                errorMessageTextArea.setText(errorMessage);
                errorMessageTextArea.setVisible(true);
                errorMessageTextArea.revalidate();
            }
            ;
        }
        // !create
    }

    private void refresh() {
        personneManager.setMembers();
        personneManager.getContentPane().removeAll();
        personneManager.getContentPane().repaint();
        personneManager.windowMaker();
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
            sportsList.setPreferredSize(new Dimension(130, sportsNamesList.size()*18));
            scrollSportsList.revalidate();
            memberSportsList.setPreferredSize(new Dimension(130, memberSportsNamesList.size()*18));
            scrollMemberSportsList.revalidate();
        }

        if (!e.getValueIsAdjusting() && e.getSource().equals(memberSportsList)) {
            sportsNamesList.add((String) memberSportsList.getSelectedValue());
            memberSportsNamesList.remove(memberSportsList.getSelectedValue());

            sportsList.setListData(sportsNamesList.toArray());
            memberSportsList.setListData(memberSportsNamesList.toArray());
            sportsList.setPreferredSize(new Dimension(130, sportsNamesList.size()*18));
            scrollSportsList.revalidate();
            memberSportsList.setPreferredSize(new Dimension(130, memberSportsNamesList.size()*18));
            scrollMemberSportsList.revalidate();
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
            clubsList.setPreferredSize(new Dimension(130, clubsNamesList.size()*18));
            memberClubsList.revalidate();
            memberClubsList.setPreferredSize(new Dimension(130, memberClubsNamesList.size()*18));
            scrollMemberClubsList.revalidate();
        }

        if (!e.getValueIsAdjusting() && e.getSource().equals(memberClubsList)) {
            clubsNamesList.add((String) memberClubsList.getSelectedValue());
            memberClubsNamesList.remove(memberClubsList.getSelectedValue());

            clubsList.setListData(clubsNamesList.toArray());
            memberClubsList.setListData(memberClubsNamesList.toArray());
            clubsList.setPreferredSize(new Dimension(130, clubsNamesList.size()*18));
            memberClubsList.revalidate();
            memberClubsList.setPreferredSize(new Dimension(130, memberClubsNamesList.size()*18));
            scrollMemberClubsList.revalidate();
        }
    }
}
