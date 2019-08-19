public class Personne extends Profil {
    private String nom;
    private String prenom;
    private int age;
    private int nbSport = 2;
    private Sport[] sports;

    public Sport[] getSports() {
        return sports;
    }

    public void setSport(Sport sport, int i) {
        sports[i] = sport;
    }

    public Personne(int _id,
                    String _nom,
                    String _prenom,
                    int _age) {
        super(_id);
        nom = _nom;
        prenom = _prenom;
        age = _age;
        sports = new Sport[nbSport];
    }

    public Personne(int _id,
                    String _nom,
                    String _prenom,
                    int _age,
                    Sport[] _sports) {
        this(_id, _nom, _prenom, _age);
        sports = _sports;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println(ConsoleColor.textColor(Constants.ANSI_BLUE, prenom + " " + nom) +
                " a " + Integer.toString(age) +
                " an" + (age > 1 ? "s" : "") +
                " et pratique : ");
        for (int i = 0; i < sports.length; i++) {
            System.out.println(ConsoleColor.textColor(Constants.ANSI_GREEN, sports[i].toString()));
        }

    }
}
