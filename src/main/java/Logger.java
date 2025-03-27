import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String FILE_NAME = "logs.txt";

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[DATE: " + timestamp + "] - " + message + "\n");
        } catch (IOException e) {
            System.out.println("Erreur lors de l’écriture dans le fichier de log : " + e.getMessage());
        }
    }
}
