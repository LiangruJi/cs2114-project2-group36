package CheckItOff;
import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * // -------------------------------------------------------------------------
/**
 *  Test for CheckItOff class
 * 
 *  @author sydneyyang
 *  @version Oct 15, 2026 
 */
public class CheckItOffAppTest extends TestCase {

    private CheckItOffApp app;
    private ByteArrayOutputStream output;

    /**
     * Creates a new app before each test
     */
    public void setUp() {
        setInput("");
    }

    /**
     * Sets up user input
     *
     * @param text input for the application
     */
    private void setInput(String text) {
        output = new ByteArrayOutputStream();

        app = new CheckItOffApp(
            new Scanner(text),
            new PrintStream(output));
    }

    /**
     * Tests that the menu displays all options
     */
    public void testDisplayMenu() {
        app.displayMenu();

        String result = output.toString();

        assertTrue(result.contains("Add Task"));
        assertTrue(result.contains("Remove Task"));
        assertTrue(result.contains("Urgency Report"));
        assertTrue(result.contains("Course Breakdown"));
        assertTrue(result.contains("Quit"));
    }

    /**
     * Tests quitting the application
     */
    public void testRunQuit() {
        setInput("5\n");

        app.run();

        assertTrue(output.toString().contains("Goodbye!"));
    }

    /**
     * Tests handling an invalid menu selection
     */
    public void testInvalidMenu() {
        setInput("9\n5\n");

        app.run();

        assertTrue(output.toString().contains("Invalid option"));
        assertTrue(output.toString().contains("Goodbye!"));
    }

    /**
     * Tests adding an assignment
     */
    public void testAddAssignment() {
        setInput(
            "1\nCS 2114\nProject 2\n2026-10-20\n3.5\n20\n");

        app.handleAdd();

        assertTrue(output.toString().contains(
            "Task added successfully"));
    }

    /**
     * Tests adding an exam
     */
    public void testAddExam() {
        setInput(
            "2\nSTAT 3005\nMidterm\n2026-10-25\n5\n");

        app.handleAdd();

        assertTrue(output.toString().contains(
            "Task added successfully"));
    }

    /**
     * Tests rejecting an invalid task type
     */
    public void testInvalidTaskType() {
        setInput(
            "3\n1\nCS 2114\nHomework\n2026-10-20\n2\n10\n");

        app.handleAdd();

        assertTrue(output.toString().contains(
            "Invalid task type"));
        assertTrue(output.toString().contains(
            "Task added successfully"));
    }

    /**
     * Tests preventing duplicate assignments
     */
    public void testDuplicateTask() {
        setInput(
            "1\nCS 2114\nHomework\n2026-10-20\n2\n10\n"
            + "1\nCS 2114\nHomework\n2026-10-20\n2\n10\n");

        app.handleAdd();
        app.handleAdd();

        assertTrue(output.toString().contains(
            "Task already exists"));
    }

    /**
     * Tests removing an existing assignment
     */
    public void testRemoveTask() {
        setInput(
            "1\nCS 2114\nHomework\n2026-10-20\n2\n10\n"
            + "1\nCS 2114\nHomework\n2026-10-20\n");

        app.handleAdd();
        app.handleRemove();

        assertTrue(output.toString().contains(
            "Task removed successfully"));
    }

    /**
     * Tests removing from an empty manager
     */
    public void testRemoveEmpty() {
        app.handleRemove();

        assertTrue(output.toString().contains(
            "No tasks to remove"));
    }

    /**
     * Tests displaying an urgency report
     */
    public void testUrgencyReport() {
        app.handleUrgencyReport();

        assertTrue(output.toString().length() > 0);
    }

    /**
     * Tests displaying a course report
     */
    public void testCourseReport() {
        app.handleCourseReport();

        assertTrue(output.toString().length() > 0);
    }

    /**
     * Tests rejecting an empty course or title
     */
    public void testReadNonEmptyString() {
        setInput("\n   \nCS 2114\n");

        String result = app.readNonEmptyString("Course: ");

        assertEquals("CS 2114", result);
        assertTrue(output.toString().contains(
            "Input cannot be empty"));
    }

    /**
     * Tests a valid date
     */
    public void testReadValidDate() {
        setInput("2026-10-20\n");

        LocalDate result = app.readValidDate("Date: ");

        assertEquals(LocalDate.of(2026, 10, 20), result);
    }

    /**
     * Tests rejecting invalid dates
     */
    public void testInvalidDate() {
        setInput("2026-02-30\n2026-10-20\n");

        LocalDate result = app.readValidDate("Date: ");

        assertEquals(LocalDate.of(2026, 10, 20), result);
        assertTrue(output.toString().contains("Invalid date"));
    }

    /**
     * Tests reading a nonnegative decimal
     */
    public void testReadNonNegativeDouble() {
        setInput("-2\nabc\n3.5\n");

        double result =
            app.readNonNegativeDouble("Hours: ");

        assertEquals(3.5, result, 0.001);
    }

    /**
     * Tests accepting zero estimated hours
     */
    public void testZeroHours() {
        setInput("0\n");

        assertEquals(
            0.0, app.readNonNegativeDouble("Hours: "), 0.001);
    }

    /**
     * Tests rejecting invalid grade percentages
     */
    public void testGradePercentage() {
        setInput("110\n-5\n25\n");

        double result =
            app.readGradePercentage("Grade: ");

        assertEquals(25.0, result, 0.001);
    }

    /**
     * Tests rejecting invalid study days
     */
    public void testPositiveInt() {
        setInput("-1\n0\nabc\n5\n");

        int result = app.readPositiveInt("Days: ");

        assertEquals(5, result);
    }

    /**
     * Tests adding a task through the main menu
     */
    public void testRunAddTask() {
        setInput(
            "1\n"
            + "1\n"
            + "CS 2114\n"
            + "Project\n"
            + "2026-10-20\n"
            + "3\n"
            + "15\n"
            + "5\n");

        app.run();

        assertTrue(output.toString().contains(
            "Task added successfully"));
        assertTrue(output.toString().contains("Goodbye!"));
    }
}
