public class ConsoleColor {

    public static String textColor (String ansiColor, String text) {
        return ansiColor + text + Constants.ANSI_RESET;
    }
}
