package GraphicsInterfaces.Sport;

import ClientServerRelation.Client;
import GraphicsInterfaces.*;
import GraphicsInterfaces.Personne.PersonneManager;
import ReseauSocial.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CreateSport extends JDialog implements ActionListener, ListSelectionListener {

    private Container createSport;

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

    private Hashtable<String, Sport> originSports = (new ReseauSocial()).getSports();
    private List<String> sportsNamesList = new ArrayList<>(originSports.keySet());
    private JList sportsList = new JList(sportsNamesList.toArray());
    private List<String> memberSportsNamesList = new ArrayList<>();
    private JList memberSportsList = new JList(memberSportsNamesList.toArray());

    private Hashtable<String, Club> originClubs = (new ReseauSocial()).getClubs();
    private List<String> clubsNamesList = new ArrayList<>(originClubs.keySet());
    private JList clubsList = new JList(clubsNamesList.toArray());
    private List<String> memberClubsNamesList = new ArrayList<>();
    private JList memberClubsList = new JList(memberClubsNamesList.toArray());

    CreateSport(SportManager sportManager) {
        super(sportManager, "Création du sport", true);
//        setResizable(true);
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3] + 200
        );
        createSport = getContentPane();
        createSport.setLayout(null);
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
        createSport.add(cancel);

        create.addActionListener(this);
        create.setBounds(xb + 20, yb, wb, hb);
        createSport.add(create);

        // Basic elements
        // sport info fields
        nameLabel.setBounds(x, y, w, h);
        createSport.add(nameLabel);

        name.setBounds(x + w, y, w, h);
        createSport.add(name);
        // !sport info fields
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancel)) this.setVisible(false);

        // create
        if (e.getSource().equals(create)) {
            String sportName = name.getText();
            Sport sport = new Sport(sportName);

            new Client(sport);

        }
        ;
        // !create
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // sports
        if (!e.getValueIsAdjusting() && e.getSource().equals(sportsList)) {
            if (!memberSportsNamesList.contains((String) sportsList.getSelectedValue())) {
                memberSportsNamesList.add((String) sportsList.getSelectedValue());
                sportsNamesList.remove((String) sportsList.getSelectedValue());
            }

            sportsList.setListData(sportsNamesList.toArray());
            memberSportsList.setListData(memberSportsNamesList.toArray());
        }

        if (!e.getValueIsAdjusting() && e.getSource().equals(memberSportsList)) {
            sportsNamesList.add((String) memberSportsList.getSelectedValue());
            memberSportsNamesList.remove((String) memberSportsList.getSelectedValue());

            sportsList.setListData(sportsNamesList.toArray());
            memberSportsList.setListData(memberSportsNamesList.toArray());
        }
        // !sports

        // club
        if (!e.getValueIsAdjusting() && e.getSource().equals(clubsList)) {
            if (!memberClubsNamesList.contains((String) clubsList.getSelectedValue())) {
                memberClubsNamesList.add((String) clubsList.getSelectedValue());
                clubsNamesList.remove((String) clubsList.getSelectedValue());
            }

            clubsList.setListData(clubsNamesList.toArray());
            memberClubsList.setListData(memberClubsNamesList.toArray());
        }

        if (!e.getValueIsAdjusting() && e.getSource().equals(memberClubsList)) {
            clubsNamesList.add((String) memberClubsList.getSelectedValue());
            memberClubsNamesList.remove((String) memberClubsList.getSelectedValue());

            clubsList.setListData(clubsNamesList.toArray());
            memberClubsList.setListData(memberClubsNamesList.toArray());
        }
    }
}

