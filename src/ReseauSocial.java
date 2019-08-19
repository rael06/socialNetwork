public class ReseauSocial {

    public static void main(String[] args) {
        Personne personne = new Personne(1, "CALITRO", "Rael", 36);
        Sport sport = new Sport("Cyclisme");
        Club club = new Club(1, "leVélo");
        personne.afficher();
        sport.afficher();
        club.afficher();
    }

}
