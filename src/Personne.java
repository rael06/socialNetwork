import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

public class Personne extends Profil implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private Hashtable<String, Sport> sports = new Hashtable<>();
    private Hashtable<String, Club> clubs = new Hashtable<>();

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

    public Hashtable<String, Sport> getSports() {
        return sports;
    }

    public void setSport(Sport sport) {
        sports.put(sport.getNom(), sport);
        sport.addPratiquant(this);
    }

    public Hashtable<String, Club> getClubs() {
        return clubs;
    }

    public void setClub(Club club) {
        clubs.put(club.getNom(), club);
        club.addPratiquant(this);
    }

    public Personne(String _nom, String _prenom, int _age) {
        super();
        id = Profil.getId();
        nom = _nom;
        prenom = (_prenom.charAt(0) + "").toUpperCase() + _prenom.substring(1);
        age = _age;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

    @Override
    public void afficher() {
        System.out.println("Profil numéro : " + ConsoleColor.textColor(ConsoleColor.RED, Integer.toString(id)) + ", " +
                ConsoleColor.textColor(ConsoleColor.BLUE, prenom + " " + nom.toUpperCase()) +
                " a " + Integer.toString(age) +
                " an" + (age > 1 ? "s" : ""));
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(nom);
        out.writeObject(prenom);
        out.writeInt(age);
        out.writeInt(id);
        out.writeObject(sports);
        out.writeObject(clubs);
        out.flush();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        nom = (String) in.readObject();
        prenom = (String) in.readObject();
        age = in.readInt();
        id = in.readInt();
        sports = (Hashtable<String, Sport>) in.readObject();
        clubs = (Hashtable<String, Club>) in.readObject();
    }
}
