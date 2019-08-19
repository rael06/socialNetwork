public class Personne extends Profil {
    private String nom;
    private String prenom;
    private int age;

    public Personne(int _id,
                    String _nom,
                    String _prenom,
                    int _age) {
        super(_id);
        nom = _nom;
        prenom = _prenom;
        age = _age;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println(prenom + " " + nom + " a " + Integer.toString(age) + " an" + (age > 1 ? "s" : ""));
    }
}
