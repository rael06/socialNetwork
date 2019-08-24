package ReseauSocial;

import java.io.*;

public class StreamFile {

    public static void write(String fileString, Object object) {
        try {
            FileOutputStream fichier = new FileOutputStream(fileString);
            ObjectOutputStream oos = new ObjectOutputStream(fichier);
            oos.writeObject(object);
            oos.flush();
            oos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

//    public static Object read(String fileString) {
//        try {
//            FileInputStream fichier = new FileInputStream(fileString);
//            ObjectInputStream ois = new ObjectInputStream(fichier);
//            Object object = ois.readObject();
//            ois.close();
//            return object;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static <T> T read(T recepteur, String fileString) {
        try {
            FileInputStream fichier = new FileInputStream(fileString);
            ObjectInputStream ois = new ObjectInputStream(fichier);
            recepteur = (T) ois.readObject();
            ois.close();
            return recepteur;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
