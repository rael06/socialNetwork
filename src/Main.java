import java.io.*;
import java.lang.reflect.Type;

public class Main {

    public static void main(String[] args) {

        ReseauSocial reseauSocial = new ReseauSocial();

        StreamFile.write("reseauSocial.txt", reseauSocial);

        reseauSocial.afficher();

        ReseauSocial result = (ReseauSocial) StreamFile.read("reseauSocial.txt");
        result.afficher();
    }

}
