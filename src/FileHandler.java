
import java.io.*;
import java.util.List;

public class FileHandler {

    public static void saveTasksToFile(List<Task> tasks, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Task> loadTasksFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Task>) ois.readObject();
        }
    }
}
