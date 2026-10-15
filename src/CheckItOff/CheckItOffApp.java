package CheckItOff;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.PrintStream;
/**
 * // -------------------------------------------------------------------------
/**
 * Application for managing assignments and exams.
 * Allows users to add, remove, and view task reports.
 * 
 *  @author sydneyyang
 *  @version Oct 15, 2026
 */
public class CheckItOffApp
{
    private TaskManager manager;
    private Scanner input;
    private PrintStream output;
    
    /**
     * Creates a new application with an empty task manager
     */
    public CheckItOffApp() {
        this(new Scanner(System.in), System.out);
    }
    /**
     * Creates an application with input and output streams
     *
     * @param inputScanner scanner used for input
     * @param outputStream stream used for output
     */
    public CheckItOffApp(
        Scanner inputScanner,
        PrintStream outputStream) {

        manager = new TaskManager();
        input = inputScanner;
        output = outputStream;
    }

    /**
     * Runs the menu until the user chooses to quit
     */
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAdd();
                    break;

                case "2":
                    handleRemove();
                    break;

                case "3":
                    handleUrgencyReport();
                    break;

                case "4":
                    handleCourseReport();
                    break;

                case "5":
                    output.println("Goodbye!");
                    running = false;
                    break;

                default:
                    output.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    /**
     * Displays the menu options
     */
    public void displayMenu() {
        output.println();
        output.println("===== Check It Off! =====");
        output.println("1. Add Task");
        output.println("2. Remove Task");
        output.println("3. Urgency Report");
        output.println("4. Course Breakdown");
        output.println("5. Quit");
        output.print("Choose an option: ");
    }

    /**
     * Collects task information and adds it to the manager
     */
    public void handleAdd() {
        output.println("1. Assignment");
        output.println("2. Exam");

        String type = readTaskType();

        String course = readNonEmptyString("Course: ");
        String title = readNonEmptyString("Title: ");
        LocalDate dueDate = readValidDate("Due date (YYYY-MM-DD): ");

        Task task;

        if (type.equals("1")) {
            // get additional information for an assignment
            double hours = readNonNegativeDouble(
                "Estimated hours: ");

            double percentage = readGradePercentage(
                "Grade percentage (0-100): ");

            task = new Assignment(
                course, title, dueDate, hours, percentage);
        }
        else {
            // get number of study days for an exam
            int days = readPositiveInt("Study days: ");

            task = new Exam(course, title, dueDate, days);
        }

        // check if the task was added successfully
        if (manager.addTask(task)) {
            output.println("Task added successfully!");
        }
        else {
            output.println("Task already exists.");
        }
    }

    /**
     * Removes a duplicate task 
     */
    public void handleRemove() {
        if (manager.getTaskCount() == 0) {
            output.println("No tasks to remove.");
            return;
        }

        output.println(manager.getAllTasks());
        output.println("1. Assignment");
        output.println("2. Exam");

        String type = readTaskType();

        String course = readNonEmptyString("Course: ");
        String title = readNonEmptyString("Title: ");
        LocalDate dueDate = readValidDate("Due date (YYYY-MM-DD): ");

        Task task;

        // create a matching task to find and remove
        if (type.equals("1")) {
            task = new Assignment(
                course, title, dueDate, 0, 0);
        }
        else {
            task = new Exam(course, title, dueDate, 1);
        }

        if (manager.removeTask(task)) {
            output.println("Task removed successfully!");
        }
        else {
            output.println("Task not found.");
        }
    }

    /**
     * Displays tasks grouped by urgency
     */
    public void handleUrgencyReport() {
        // use today's date to determine urgency
        output.println(
            manager.generateUrgencyReport(LocalDate.now()));
    }

    /**
     * Displays the number of tasks for each course.
     */
    public void handleCourseReport() {
        output.println(manager.generateCourseReport());
    }

    /**
     * Reads an assignment or exam selection.
     *
     * @return valid task type
     */
    private String readTaskType() {
        while (true) {
            output.print("Choose task type (1 or 2): ");

            String type = input.nextLine().trim();

            if (type.equals("1") || type.equals("2")) {
                return type;
            }

            output.println("Invalid task type. Try again.");
        }
    }

    /**
     * Reads a string that cannot be empty
     *
     * @param prompt message shown to user
     * @return nonempty string
     */
    public String readNonEmptyString(String prompt) {
        while (true) {
            output.print(prompt);

            String value = input.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            output.println("Input cannot be empty.");
        }
    }

    /**
     * Reads a valid date in YYYY-MM-DD format
     *
     * @param prompt message shown to user
     * @return valid date
     */
    public LocalDate readValidDate(String prompt) {
        while (true) {
            output.print(prompt);

            String value = input.nextLine().trim();

            try {
                return LocalDate.parse(value);
            }
            catch (DateTimeParseException e) {
                output.println(
                    "Invalid date. Use YYYY-MM-DD.");
            }
        }
    }

    /**
     * Reads a number greater than or equal to zero
     *
     * @param prompt message shown to user
     * @return nonnegative number
     */
    public double readNonNegativeDouble(String prompt) {
        while (true) {
            output.print(prompt);

            String value = input.nextLine().trim();

            try {
                double number = Double.parseDouble(value);

                if (Double.isFinite(number) && number >= 0) {
                    return number;
                }
            }
            catch (NumberFormatException e) {
                // invalid input will be requested again
            }

            output.println(
                "Enter a valid nonnegative number.");
        }
    }

    /**
     * Reads a grade percentage between 0 and 100
     *
     * @param prompt message shown to user
     * @return valid grade percentage
     */
    public double readGradePercentage(String prompt) {
        while (true) {
            output.print(prompt);

            String value = input.nextLine().trim();

            try {
                double number = Double.parseDouble(value);

                if (Double.isFinite(number)
                    && number >= 0 && number <= 100) {
                    return number;
                }
            }
            catch (NumberFormatException e) {
                // invalid input will be requested again
            }

            output.println(
                "Enter a percentage between 0 and 100.");
        }
    }

    /**
     * Reads a number for exam study days
     *
     * @param prompt message shown to user
     * @return positive integer
     */
    public int readPositiveInt(String prompt) {
        while (true) {
            output.print(prompt);

            String value = input.nextLine().trim();

            try {
                int number = Integer.parseInt(value);

                if (number > 0) {
                    return number;
                }
            }
            catch (NumberFormatException e) {
                // invalid input will be requested again
            }

            output.println("Enter a positive whole number.");
        }
    }

    /**
     * Starts the Check It Off application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        CheckItOffApp app = new CheckItOffApp();
        app.run();
    }
}
