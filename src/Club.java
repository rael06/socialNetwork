import java.util.Vector;

public class Club {

    private String nom;
    private Vector<Personne> pratiquants;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Club(String _nom) {
        nom = _nom;
        pratiquants = new Vector<Personne>();
    }

    public void addPratiquant(Personne personne) {
        pratiquants.add(personne);
    }

    public void afficher() {
        System.out.println("Le nom du club est : " + nom);
    }

    @Override
    public String toString() {
        return nom;
    }
}
