public class Profil {
    protected static int id = 0;

    public Profil() {
        id++;
    }

    public void afficher() {
        System.out.println("Numéro : " + Integer.toString(id));
    }
}
