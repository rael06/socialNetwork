public class Main {

    public static void main(String[] args) {

        ReseauSocial reseauSocial = new ReseauSocial();

        StreamFile.write("reseauSocial.txt", reseauSocial);

//        reseauSocial.afficher();

        ReseauSocial result = StreamFile.read(new ReseauSocial(), "reseauSocial.txt");
//        ReseauSocial result = (ReseauSocial) StreamFile.read("reseauSocial.txt");

        if (result != null) {
            result.afficher();
        }
    }

}
