import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {

        ReseauSocial reseauSocial = new ReseauSocial();

        try {
            FileOutputStream fichier = new FileOutputStream("personne.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(reseauSocial);
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        reseauSocial.afficher();

        try {
            FileInputStream fichier = new FileInputStream("personne.txt");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            ReseauSocial reseau = (ReseauSocial) ois.readObject();
            reseau.afficher();
//            ois.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
