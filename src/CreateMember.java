import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CreateMember extends JDialog implements ActionListener {

    private Container createMember;

    private Hashtable<String, Sport> sports = (new ReseauSocial()).getSports();

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

    private Set sportsKeys = sports.keySet();
    private JList<Sport> sportsList = new JList(sportsKeys.toArray());

    CreateMember(PersonneManager personneManager) {
        super(personneManager, "Création du membre", true);
//        setResizable(true);
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3] + 200
        );
        createMember = getContentPane();
        createMember.setLayout(null);
        addElements();

        this.setVisible(true);
    }

    private void addElements() {

        System.out.println(this.getWidth() / 2);

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

        sportsLabel.setBounds(xb - w - 10, y * 3 + 50, w, h);
        createMember.add(sportsLabel);

        sportsList.setBounds(x, y * 4 + 50, xb - 150, 200);
        createMember.add(sportsList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) this.setVisible(false);
    }
}
