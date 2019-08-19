import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        ReseauSocial reseauSocial = new ReseauSocial();

        reseauSocial.createPerson(new Personne("CALITRO", "Rael", 36));
        reseauSocial.createPerson(new Personne("DUPONT", "Jean", 60));

        reseauSocial.createSport(new Sport("cyclisme"));
        reseauSocial.createSport(new Sport("alpinisme"));
        reseauSocial.createSport(new Sport("athlétisme"));

        reseauSocial.setPersonSport("CALITRO", "cyclisme");
        reseauSocial.setPersonSport("CALITRO", "alpinisme");
        reseauSocial.setPersonSport("CALITRO", "athlétisme");

        reseauSocial.setPersonSport("Dupont", "athlétisme");
        reseauSocial.setPersonSport("Dupont", "alpinisme");

        (new ReseauSocial()).afficher();

//        Vector<Personne> personnes = new Vector<Personne>();
//        Vector<Sport> sports = new Vector<Sport>();

//        personnes.add(new Personne("CALITRO", "Rael", 36));
//        personnes.add(new Personne("DUPONT", "Jean", 25));
//
//        sports.add(new Sport("cyclisme"));
//        sports.add(new Sport("kayak"));
//        sports.add(new Sport("alpinisme"));
//
//        personnes.elementAt(0).setSport(sports.elementAt(0));
//        personnes.elementAt(0).setSport(sports.elementAt(1));
//        personnes.elementAt(0).setSport(sports.elementAt(2));
//
//        personnes.elementAt(1).setSport(sports.elementAt(1));
//        personnes.elementAt(1).setSport(sports.elementAt(0));
//
//        for (Personne personne : personnes) {
//            personne.afficher();
//        }
    }

}
