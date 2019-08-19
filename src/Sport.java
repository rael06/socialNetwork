public class Sport {

    private String nom;

    public Personne getPers() {
        return pers;
    }

    public void setPers(Personne pers) {
        this.pers = pers;
    }

    private Personne pers;

    public Sport(String _nom) {
        nom = _nom;
    }

    public Sport(String _nom, Personne _pers) {
        this(_nom);
        pers = _pers;
    }

    public void afficher () {
        System.out.println("Le " +
                ConsoleColor.textColor(Constants.ANSI_GREEN,nom) +
                " est pratiqué par : " +
                ConsoleColor.textColor(Constants.ANSI_BLUE, pers.toString()));
    }

    @Override
    public String toString() {
        return nom;
    }
}
