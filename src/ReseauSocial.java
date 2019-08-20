import java.util.Map;
import java.util.Hashtable;


public class ReseauSocial {
    private Hashtable<String, Personne> personnes = new Hashtable<>();
    private Hashtable<String, Sport> sports = new Hashtable<>();
    private Hashtable<String, Club> clubs = new Hashtable<>();

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

    public void createPerson(Personne personne) {
        personnes.put(personne.getNom(), personne);
    }

    public void createSport(Sport sport) {
        sports.put(sport.getNom(), sport);
    }

    public void createClub(Club club) {
        clubs.put(club.getNom(), club);
    }

    public void setPersonClub(String personName, String clubName) {

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

        /*
        personnes.elementAt(0).setSport(sports.elementAt(0));
        personnes.elementAt(0).setSport(sports.elementAt(1));
        personnes.elementAt(0).setSport(sports.elementAt(2));

        personnes.elementAt(1).setSport(sports.elementAt(1));
        personnes.elementAt(1).setSport(sports.elementAt(0));
        */
    }

    public void setPersonSport(String personName, String sportName) {

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
        for (Map.Entry<String, Club>club: clubs.entrySet()) {
            club.getValue().afficher();
        }
    }


}
