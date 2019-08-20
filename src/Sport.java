import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Hashtable;

public class Sport implements Serializable {

    private String nom;
    private Personne pers;
    private Map<String, Personne> pratiquants = new Hashtable<>();

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
    }

    public void afficher() {
        System.out.println("Le " + ConsoleColor.textColor(ConsoleColor.GREEN, nom));
        if (pratiquants.size() == 0) {
            System.out.println("n'est pas pratiqué !");
        } else {
            System.out.println("est pratiqué par : ");
            for (Map.Entry<String, Personne> pratiquant : pratiquants.entrySet()) {
                System.out.println(ConsoleColor.textColor(ConsoleColor.BLUE, pratiquant.getValue().toString()));
            }
        }
    }

    public void addPratiquant(Personne _pers) {
        pratiquants.put(_pers.getNom(), _pers);
    }

    @Override
    public String toString() {
        return nom;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(nom);
        out.writeObject(pers);
        out.writeObject(pratiquants);
        out.flush();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        nom = (String) in.readObject();
        pratiquants = (Hashtable<String, Personne>) in.readObject();
    }
}
