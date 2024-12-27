import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskBrew {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskBrew app = new TaskBrew();
        Logger.logSuccess(Strings.WELCOME_MESSAGE);
        app.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print(Strings.ENTER_CHOICE);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    listTasks();
                    break;
                case "3":
                    markTaskAsComplete();
                    break;
                case "4":
                    removeTask();
                    break;
                case "5":
                    running = false;
                    Logger.logSuccess(Strings.GOODBYE_MESSAGE);
                    break;
                default:
                    Logger.logError(Strings.INVALID_CHOICE);
            }
        }
    }

    private void printMenu() {
        Logger.logInfo(Strings.MENU_HEADER);
        System.out.println(Strings.MENU_ADD_TASK);
        System.out.println(Strings.MENU_LIST_TASKS);
        System.out.println(Strings.MENU_MARK_TASK_COMPLETE);
        System.out.println(Strings.MENU_REMOVE_TASK);
        System.out.println(Strings.MENU_EXIT);
    }

    private void addTask() {
        System.out.print(Strings.ENTER_TASK_TITLE);
        String title = scanner.nextLine();

        System.out.print(Strings.ENTER_TASK_DESCRIPTION);
        String description = scanner.nextLine();

        LocalDate date = null;
        while (date == null) {
            System.out.print(Strings.ENTER_TASK_DUE_DATE);
            String dueDate = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                date = LocalDate.parse(dueDate, formatter);
            } catch (DateTimeParseException e) {
                Logger.logError(Strings.INVALID_DATE_FORMAT);
            }
        }

        Task task = new Task(title, description, date);
        tasks.add(task);

        Logger.logSuccess(Strings.TASK_ADDED);
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            Logger.logWarning(Strings.NO_TASKS_AVAILABLE);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }

    private void markTaskAsComplete() {
        if (tasks.isEmpty()) {
            Logger.logWarning(Strings.NO_TASKS_AVAILABLE);
            return;
        }

        listTasks();
        System.out.print(Strings.ENTER_TASK_NUM_T0_COMPLETE);
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            Task task = tasks.get(taskNumber - 1);
            task.markComplete();
            Logger.logSuccess(Strings.TASK_MARKED_AS_COMPLETE);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Logger.logError(Strings.INVALID_TASK_NUMBER);
        }
    }

    private void removeTask() {
        if (tasks.isEmpty()) {
            Logger.logWarning(Strings.NO_TASKS_AVAILABLE);
            return;
        }

        listTasks();
        System.out.print(Strings.ENTER_TASK_NUM_T0_COMPLETE);
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            tasks.remove(taskNumber - 1);
            Logger.logSuccess(Strings.TASK_REMOVED);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Logger.logError(Strings.INVALID_TASK_NUMBER);
        }
    }
}
