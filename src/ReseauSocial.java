public class ReseauSocial {

    public static void main(String[] args) {
        Personne personne = new Personne(1, "CALITRO", "Rael", 36);
        Sport sport1 = new Sport("cyclisme");
        Sport sport2 = new Sport("kayak");
        personne.setSport(sport1, 0);
        personne.setSport(sport2, 1);
        personne.afficher();
//        sport1.setPers(personne);
//        Club club = new Club(1, "leVélo");
//        club.afficher();
    }

}
