public class Logger {
    public static void log(String color, String message) {
        System.out.println(color + message + AnsiColors.RESET);
    }

    public static void logInfo(String message) {
        log(AnsiColors.BLUE, message);
    }

    public static void logSuccess(String message) {
        log(AnsiColors.GREEN, message);
    }

    public static void logWarning(String message) {
        log(AnsiColors.YELLOW, message);
    }

    public static void logError(String message) {
        log(AnsiColors.RED, message);
    }
}
