public class Profil {
    protected int id;

    public Profil(int _id) {
        id = _id;
    }

    public void afficher() {
        System.out.println("Numéro : " + Integer.toString(id));
    }
}
