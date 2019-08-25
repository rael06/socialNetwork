package ReseauSocial;

import Utils.ConsoleColor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Personne extends Profil implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private HashMap<String, Sport> sports = new HashMap<>();
    private HashMap<String, Club> clubs = new HashMap<>();

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

    public HashMap<String, Sport> getSports() {
        return sports;
    }

    public void setSport(Sport sport) {
        sports.put(sport.getNom(), sport);
        sport.addPratiquant(this);
    }

    public HashMap<String, Club> getClubs() {
        return clubs;
    }

    public void setClub(Club club) {
        clubs.put(club.getNom(), club);
        club.addAdherent(this);
    }

    public Personne(String _nom, String _prenom, int _age) {
        super();
        id = Profil.getId();
        nom = _nom.toUpperCase();
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
                ConsoleColor.textColor(ConsoleColor.BLUE, prenom + " " + nom) +
                " a " + ConsoleColor.textColor(ConsoleColor.YELLOW_BOLD, (age + "")) +
                " an" + (age > 1 ? "s," : ","));
        System.out.println("exerce le" + (sports.size() > 1 ? "s sports suivants" : " sport suivant") + " : ");
        for (Map.Entry sport : sports.entrySet()) {
            System.out.println(ConsoleColor.textColor(ConsoleColor.GREEN, sport.getKey().toString()));
        }
        System.out.println("et est dans le" + (clubs.size() > 1 ? "s clubs suivants" : " club suivant") + " : ");
        for (Map.Entry club : clubs.entrySet()) {
            System.out.println(ConsoleColor.textColor(ConsoleColor.PURPLE, club.getKey().toString()));
        }
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

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        nom = (String) in.readObject();
        prenom = (String) in.readObject();
        age = in.readInt();
        id = in.readInt();
        sports = (HashMap<String, Sport>) in.readObject();
        clubs = (HashMap<String, Club>) in.readObject();
    }
}
