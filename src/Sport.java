import java.util.*;

public class Sport {

    private String nom;
    private Personne pers;
    private Vector<Personne> pratiquants;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Personne getPers() {
        return pers;
    }

    public void setPers(Personne pers) {
        this.pers = pers;
    }

    public Sport(String _nom) {
        nom = _nom;
        pratiquants = new Vector<Personne>();
    }

    public void afficher() {
        System.out.println("Le " + ConsoleColor.textColor(ConsoleColor.GREEN, nom));
        if (pratiquants.size() == 0) {
            System.out.println("n'est pas pratiqué !");
        } else {
            System.out.println("est pratiqué par : ");
            for (Personne pratiquant : pratiquants) {
                System.out.println(ConsoleColor.textColor(ConsoleColor.BLUE, pratiquant.toString()));
            }
        }
    }

    public void addPratiquant(Personne _pers) {
        pratiquants.add(_pers);
    }

    @Override
    public String toString() {
        return nom;
    }

}
