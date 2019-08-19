import java.util.Vector;


public class ReseauSocial {
    private Vector<Personne> personnes = new Vector<Personne>();
    private Vector<Sport> sports = new Vector<Sport>();
    private Vector<Club> clubs = new Vector<Club>();

    public ReseauSocial() {

        //create persons
        createPerson(new Personne("CALITRO", "Rael", 36));
        createPerson(new Personne("DUPONT", "Jean", 60));

        //create sports
        createSport(new Sport("cyclisme"));
        createSport(new Sport("alpinisme"));
        createSport(new Sport("athlétisme"));

        //create clubs
        createClub(new Club("jaimeLeSport"));
        createClub(new Club("leVélo"));

        //set sports to persons
        setPersonSport("CALITRO", "cyclisme");
        setPersonSport("CALITRO", "alpinisme");
        setPersonSport("CALITRO", "athlétisme");
        setPersonSport("Dupont", "athlétisme");
        setPersonSport("Dupont", "alpinisme");

        //set clubs to persons
        setPersonClub("calitro", "jaimelesport");
        setPersonClub("Dupont", "jaimelesport");
        setPersonClub("Dupont", "leVélo");
    }

    public void createPerson(Personne personne) {
        personnes.add(personne);
    }

    public void createSport(Sport sport) {
        sports.add(sport);
    }

    public void createClub(Club club) {
        clubs.add(club);
    }

    public void setPersonClub(String personName, String clubName) {

        Personne personFound = null;
        Club clubFound = null;

        for (Personne personne : personnes) {
            if (personne.getNom().toLowerCase().equals(personName.toLowerCase())) {
                personFound = personne;
                break;
            }
        }

        for (Club club : clubs) {
            if (club.getNom().toLowerCase().equals(clubName.toLowerCase())) {
                clubFound = club;
                break;
            }
        }

        if (personFound != null) personFound.setClub(clubFound);

        /*personnes.elementAt(0).setSport(sports.elementAt(0));
        personnes.elementAt(0).setSport(sports.elementAt(1));
        personnes.elementAt(0).setSport(sports.elementAt(2));

        personnes.elementAt(1).setSport(sports.elementAt(1));
        personnes.elementAt(1).setSport(sports.elementAt(0));*/
    }

    public void setPersonSport(String personName, String sportName) {

        Personne personFound = null;
        Sport sportFound = null;

        for (Personne personne : personnes) {
            if (personne.getNom().toLowerCase().equals(personName.toLowerCase())) {
                personFound = personne;
                break;
            }
        }

        for (Sport sport : sports) {
            if (sport.getNom().toLowerCase().equals(sportName.toLowerCase())) {
                sportFound = sport;
                break;
            }
        }

        if (personFound != null) personFound.setSport(sportFound);
    }

    public void afficher() {
        for (Personne personne : personnes) {
            personne.afficher();
        }
    }


}
