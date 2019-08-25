package ReseauSocial;

import java.io.*;
import java.util.Map;
import java.util.HashMap;


public class ReseauSocial implements Serializable {

    private HashMap<String, Personne> personnes = new HashMap<>();
    private HashMap<String, Sport> sports = new HashMap<>();
    private HashMap<String, Club> clubs = new HashMap<>();

    public HashMap<String, Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(HashMap<String, Personne> personnes) {
        this.personnes = personnes;
    }

    public HashMap<String, Sport> getSports() {
        return sports;
    }

    public void setSports(HashMap<String, Sport> sports) {
        this.sports = sports;
    }

    public HashMap<String, Club> getClubs() {
        return clubs;
    }

    public void setClubs(HashMap<String, Club> clubs) {
        this.clubs = clubs;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(personnes);
        out.writeObject(sports);
        out.writeObject(clubs);
        out.flush();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        personnes = (HashMap<String, Personne>) in.readObject();
        sports = (HashMap<String, Sport>) in.readObject();
        clubs = (HashMap<String, Club>) in.readObject();
    }

    public ReseauSocial() {

        //create persons
        createPerson(new Personne("CALITRO", "Rael", 36));
        createPerson(new Personne("DUPONT", "Jean", 60));
        createPerson(new Personne("MICHU", "Geneviève", 51));

        //create sports
        createSport(new Sport("cyclisme"));
        createSport(new Sport("alpinisme"));
        createSport(new Sport("athlétisme"));
        createSport(new Sport("boxe"));
        createSport(new Sport("tennis"));

        //create clubs
        createClub(new Club("J'aime Le Sport"));
        createClub(new Club("Youpi Sport"));

        //set sports to persons
        setPersonSport("CALITRO", "cyclisme");
        setPersonSport("CALITRO", "alpinisme");
        setPersonSport("CALITRO", "athlétisme");
        setPersonSport("Dupont", "athlétisme");
        setPersonSport("Dupont", "alpinisme");
        setPersonSport("Michu", "alpinisme");
        setPersonSport("Michu", "tennis");
        setPersonSport("Michu", "boxe");

        //set clubs to persons
        setPersonClub("calitro", "J'aime Le Sport");
        setPersonClub("Dupont", "J'aime Le Sport");
        setPersonClub("Dupont", "Youpi Sport");
        setPersonClub("Michu", "Youpi Sport");
    }

    private void createPerson(Personne personne) {
        personnes.put(personne.getNom(), personne);
    }

    private void createSport(Sport sport) {
        sports.put(sport.getNom(), sport);
    }

    private void createClub(Club club) {
        clubs.put(club.getNom(), club);
    }

    private void setPersonClub(String personName, String clubName) {
        Personne personFound = null;
        Club clubFound = null;

        for (Map.Entry<String, Personne> personne : personnes.entrySet()) {
            if (personne.getValue().getNom().toLowerCase().equals(personName.toLowerCase())) {
                personFound = personne.getValue();
                break;
            }
        }

        for (Map.Entry<String, Club> club : clubs.entrySet()) {
            if (club.getValue().getNom().toLowerCase().equals(clubName.toLowerCase())) {
                clubFound = club.getValue();
                break;
            }
        }

        if (personFound != null) personFound.setClub(clubFound);
    }

    private void setPersonSport(String personName, String sportName) {

        Personne personFound = null;
        Sport sportFound = null;

        for (Map.Entry<String, Personne> personne : personnes.entrySet()) {
            if (personne.getValue().getNom().toLowerCase().equals(personName.toLowerCase())) {
                personFound = personne.getValue();
                break;
            }
        }

        for (Map.Entry<String, Sport> sport : sports.entrySet()) {
            if (sport.getValue().getNom().toLowerCase().equals(sportName.toLowerCase())) {
                sportFound = sport.getValue();
                break;
            }
        }

        if (personFound != null) personFound.setSport(sportFound);
    }

    public void afficher() {
        for (Map.Entry<String, Club> club : clubs.entrySet()) {
            club.getValue().afficher();
        }
    }
}
