public class Profil {
    protected static int id = 0;

    public int getId() {
        return id;
    }

    public Profil() {
        id++;
    }

    public void afficher() { }
}
