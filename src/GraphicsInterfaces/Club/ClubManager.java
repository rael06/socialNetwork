package GraphicsInterfaces.Club;

import GraphicsInterfaces.ReseauSocialManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClubManager extends JDialog implements ActionListener {

    private JButton creer;

    public ClubManager(ReseauSocialManager reseauSocialManager) {
        super(reseauSocialManager, "Interface de gestion des clubs", true);
        creer = new JButton("Créer");
        creer.addActionListener(this);

//        setResizable(true);
        setBounds(
                ReseauSocialManager.ORIG_BOUNDS[0],
                ReseauSocialManager.ORIG_BOUNDS[1],
                ReseauSocialManager.ORIG_BOUNDS[2],
                ReseauSocialManager.ORIG_BOUNDS[3]
        );

        Container clubManager = getContentPane();
        clubManager.setLayout(null);
        clubManager.add(creer);
        clubManager.getComponent(0).setBounds(0, 0, 90, 30);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(creer)) new CreateClub(this);
    }
}
