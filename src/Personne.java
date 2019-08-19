import java.util.Vector;

public class Personne extends Profil {
    private String nom;
    private String prenom;
    private int age;
    private Vector<Sport> sports;
    private Vector<Club> clubs;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSport(Sport sport) {
        sports.add(sport);
        sport.addPratiquant(this);
    }

    public void setClub(Club club) {
        clubs.add(club);
        club.addPratiquant(this);
    }

    public Personne(String _nom, String _prenom, int _age) {
        super();
        nom = _nom;
        prenom = _prenom;
        age = _age;
        sports = new Vector<Sport>();
        clubs = new Vector<Club>();
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println(ConsoleColor.textColor(ConsoleColor.BLUE, prenom + " " + nom) +
                " a " + Integer.toString(age) +
                " an" + (age > 1 ? "s" : "") +
                " et pratique " + (sports.size() == 1 ? "le sport " : "les sports ") + ": ");

        for (int i = 0; i < sports.size(); i++) {
            System.out.println(ConsoleColor.textColor(ConsoleColor.GREEN, sports.elementAt(i).toString()));
        }
        if (clubs.size() != 0) {
            System.out.println("dans " + (clubs.size() == 1 ? "le club " : "les clubs ") + ": ");
        } else System.out.println("n'a pas de club !");

        for (int i = 0; i< clubs.size(); i++) {
            System.out.println(ConsoleColor.textColor(ConsoleColor.PURPLE, clubs.elementAt(i).toString()));
        }
    }

}
