import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskBrew {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskBrew app = new TaskBrew();
        System.out.println( AnsiColors.GREEN+ Strings.WELCOME_MESSAGE  + AnsiColors.RESET);
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
                    System.out.println(Strings.GOODBYE_MESSAGE);
                    break;
                default:
                    System.out.println(AnsiColors.RED +Strings.INVALID_CHOICE+ AnsiColors.RESET);
            }
        }
    }

    private void printMenu() {
        System.out.println(Strings.MENU_HEADER);
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

     while (date==null){
    System.out.print(Strings.ENTER_TASK_DUE_DATE);
    String dueDate = scanner.nextLine();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try{
        date = LocalDate.parse(dueDate, formatter);
    }
    catch (DateTimeParseException e) {
        System.out.println(AnsiColors.RED+ Strings.INVALID_DATE_FORMAT+ AnsiColors.RESET);
    }

}




        Task task = new Task(title, description, date);
        tasks.add(task);

        System.out.println(AnsiColors.GREEN+ Strings.TASK_ADDED+ AnsiColors.RESET);
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(AnsiColors.YELLOW+ Strings.NO_TASKS_AVAILABLE + AnsiColors.RESET);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }

    private void markTaskAsComplete() {
        if (tasks.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + Strings.NO_TASKS_AVAILABLE + AnsiColors.RESET);

        }

        listTasks();
        System.out.print(Strings.ENTER_TASK_NUM_T0_COMPLETE);
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            Task task = tasks.get(taskNumber - 1);
            task.markComplete();
            System.out.println(AnsiColors.GREEN + Strings.TASK_MARKED_AS_COMPLETE + AnsiColors.RESET);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Strings.INVALID_TASK_NUMBER);
        }
    }

    private void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println(AnsiColors.YELLOW + Strings.NO_TASKS_AVAILABLE + AnsiColors.RESET);

        }

        listTasks();
        System.out.print(Strings.ENTER_TASK_NUM_T0_COMPLETE);
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            tasks.remove(taskNumber - 1);
            System.out.println(AnsiColors.BLUE + Strings.TASK_REMOVED + AnsiColors.RESET);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Strings.INVALID_TASK_NUMBER);
        }
    }
}