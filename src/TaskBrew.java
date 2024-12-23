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
        app.run();
    }

    public void run() {
        System.out.println(Constants.WELCOME_MESSAGE);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print(Constants.ENTER_CHOICE);
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
                    System.out.println(Constants.GOODBYE_MESSAGE);
                    break;
                default:
                    System.out.println(Constants.INVALID_CHOICE);
            }
        }
    }

    private void printMenu() {
        System.out.println(Constants.MENU_HEADER);
        System.out.println(Constants.MENU_ADD_TASK);
        System.out.println(Constants.MENU_LIST_TASKS);
        System.out.println(Constants.MENU_MARK_TASK_COMPLETE);
        System.out.println(Constants.MENU_REMOVE_TASK);
        System.out.println(Constants.MENU_EXIT);
    }

    private void addTask() {
        System.out.print(Constants.ENTER_TASK_TITLE);
        String title = scanner.nextLine();

        System.out.print(Constants.ENTER_TASK_DESCRIPTION);
        String description = scanner.nextLine();

        LocalDate date = null;

     while (date==null){
    System.out.print(Constants.ENTER_TASK_DUE_DATE);
    String dueDate = scanner.nextLine();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try{
        date = LocalDate.parse(dueDate, formatter);
    }
    catch (DateTimeParseException e) {
        System.out.println(Constants.INVALID_DATE_FORMAT);
    }

}




        Task task = new Task(title, description, date);
        tasks.add(task);

        System.out.println(Constants.TASK_ADDED);
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(Constants.NO_TASKS_AVAILABLE);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }

    private void markTaskAsComplete() {
        if (tasks.isEmpty()) {
            System.out.println(Constants.NO_TASKS_AVAILABLE);
            return;
        }

        listTasks();
        System.out.print(Constants.ENTER_TASK_NUM_T0_COMPLETE);
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            Task task = tasks.get(taskNumber - 1);
            task.markComplete();
            System.out.println(Constants.TASK_MARKED_AS_COMPLETE);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Constants.INVALID_TASK_NUMBER);
        }
    }

    private void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println(Constants.NO_TASKS_AVAILABLE);
            return;
        }

        listTasks();
        System.out.print(Constants.ENTER_TASK_NUM_T0_COMPLETE);
        try {
            int taskNumber = Integer.parseInt(scanner.nextLine());
            tasks.remove(taskNumber - 1);
            System.out.println(Constants.TASK_REMOVED);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Constants.INVALID_TASK_NUMBER);
        }
    }
}