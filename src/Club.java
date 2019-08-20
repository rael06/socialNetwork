import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Hashtable;
import java.util.Set;

public class Club implements Serializable {

    private String nom;
    private Hashtable<String, Personne> pratiquants = new Hashtable<>();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Club(String _nom) {
        nom = _nom;
    }

    public void addPratiquant(Personne personne) {
        pratiquants.put(personne.getNom(), personne);
    }

    public void afficher() {
        System.out.println("--------------------------------------------");
        System.out.println("Les membres du club " + ConsoleColor.textColor(ConsoleColor.PURPLE, nom) + " sont : ");

        Map<String, Sport> uniquesSports = new Hashtable<>();

        for (Map.Entry<String, Personne> personne : pratiquants.entrySet()) {
            personne.getValue().afficher();

            Hashtable<String, Sport> sports = personne.getValue().getSports();

            Set<String> sportsKeys = sports.keySet();
            for (String key : sportsKeys) {
                uniquesSports.putIfAbsent(key, sports.get(key));
            }

//            Set sportSet = sports.keySet();
//            for (Object object : sportSet) {
//                String key = (String) object;
//                uniquesSports.putIfAbsent(key, sports.get(key));
//            }

//            for (Iterator iterator = sportSet.iterator(); iterator.hasNext(); ) {
//                Object o = iterator.next();
//                String key = (String) o;
//                uniquesSports.putIfAbsent(key, sports.get(key));
//            }

//            while (iterator.hasNext()) {
//                String key = (String) iterator.next();
//                uniquesSports.putIfAbsent(key, sports.get(key));
//            }

//            for (Map.Entry<String, Sport> sportsEntry : sports.entrySet()) {
//                uniquesSports.putIfAbsent(sportsEntry.getKey(), sportsEntry.getValue());
//            }
        }

        System.out.println("                  ---");
        System.out.println("Les sports pratiqués dans ce club " +  (uniquesSports.size() == 1 ? "est " : "sont ") + ": ");

        Set<String> uniquesSportsKeys = uniquesSports.keySet();
        for (String key : uniquesSportsKeys) {
            System.out.println(ConsoleColor.textColor(ConsoleColor.GREEN, uniquesSports.get(key).getNom()));
        }
//        for (Map.Entry<String, Sport> sportEntry : uniquesSports.entrySet()) {
//            System.out.println(ConsoleColor.textColor(ConsoleColor.GREEN, sportEntry.getKey()));
//        }
    }

    @Override
    public String toString() {
        return nom;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.writeObject(nom);
        out.flush();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        nom = (String) in.readObject();
    }
}