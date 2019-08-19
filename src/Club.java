public class Club extends Profil {

    private String nom;

    public Club(int _id, String _nom) {
        super(_id);
        nom = _nom;
    }

    public void afficher() {
        System.out.println("Le nom du club est : " + nom);
    }
}
