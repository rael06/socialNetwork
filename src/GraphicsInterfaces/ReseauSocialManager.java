package GraphicsInterfaces;

import GraphicsInterfaces.Club.ClubManager;
import GraphicsInterfaces.Personne.PersonneManager;
import GraphicsInterfaces.Sport.SportManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ReseauSocialManager extends JDialog implements ActionListener, WindowListener {
    public static final int[] ORIG_BOUNDS = {50, 100, 700, 300};
    private Frame fenetre;

    protected MenuBar barre_de_menu;
    protected Menu reseauSocial;
    protected MenuItem personne;
    protected MenuItem sport;
    protected MenuItem club;

    public ReseauSocialManager() {
        fenetre = new Frame("Réseau social");
        fenetre.setBounds(50, 100, 500, 300);
        fenetre.setVisible(true);
        fenetre.addWindowListener(this);

        barre_de_menu = new MenuBar();

        reseauSocial = new Menu("Réseau social");

        personne = new MenuItem("Personne");
        sport = new MenuItem("sport");
        club = new MenuItem("club");

        barre_de_menu.add(reseauSocial);

        reseauSocial.add(personne);
        reseauSocial.add(sport);
        reseauSocial.add(club);

        personne.addActionListener(this);
        sport.addActionListener(this);
        club.addActionListener(this);

        fenetre.setMenuBar(barre_de_menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(personne)) new PersonneManager(this);
        if (e.getSource().equals(sport)) new SportManager(this);
        if (e.getSource().equals(club)) new ClubManager();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
