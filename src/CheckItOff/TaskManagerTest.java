package CheckItOff;

import java.time.LocalDate;
import student.TestCase;

/**
 * // -------------------------------------------------------------------------
 * /** tests the TestManager class
 * 
 * @author sydneyyang
 * @version Oct 15, 2026
 */
public class TaskManagerTest
    extends TestCase
{
    private TaskManager manager;
    private Assignment assignment;
    private Exam exam;
    private LocalDate today;

    /**
     * Sets up test objects
     */
    public void setUp()
    {
        manager = new TaskManager();
        today = LocalDate.of(2026, 10, 14);

        assignment = new Assignment(
            "CS 2114",
            "Project 2",
            today.plusDays(1),
            3.0,
            10.0);

        exam = new Exam("STAT 3005", "Midterm", today.plusDays(5), 4);
    }


    /**
     * Tests the constructor
     */
    public void testTaskManager()
    {
        assertEquals(0, manager.getTaskCount());

        TaskManager other = new TaskManager();
        manager.addTask(assignment);

        assertEquals(0, other.getTaskCount());
    }


    /**
     * Tests adding tasks and duplicates
     */
    public void testAddTask()
    {
        assertTrue(manager.addTask(assignment));
        assertEquals(1, manager.getTaskCount());

        assertFalse(manager.addTask(assignment));
        assertEquals(1, manager.getTaskCount());

        try
        {
            manager.addTask(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

    }


    /**
     * Tests removing tasks.
     */
    public void testRemoveTask()
    {
        manager.addTask(assignment);

        assertTrue(manager.removeTask(assignment));
        assertEquals(0, manager.getTaskCount());

        assertFalse(manager.removeTask(assignment));

        try
        {
            manager.removeTask(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

    }


    /**
     * Tests checking whether tasks exist
     */
    public void testContainsTask()
    {
        manager.addTask(assignment);

        assertTrue(manager.containsTask(assignment));
        assertFalse(manager.containsTask(exam));

        try
        {
            manager.containsTask(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }
    }


    /**
     * Tests the number of tasks
     */
    public void testGetTaskCount()
    {
        assertEquals(0, manager.getTaskCount());

        manager.addTask(assignment);
        manager.addTask(exam);

        assertEquals(2, manager.getTaskCount());

        manager.removeTask(assignment);
        assertEquals(1, manager.getTaskCount());
    }


    /**
     * Tests urgency categories and invalid dates
     */
    public void testGenerateUrgencyReport()
    {
        assertTrue(
            manager.generateUrgencyReport(today)
                .contains("Nothing is tracked"));

        manager.addTask(assignment);
        manager.addTask(exam);

        String report = manager.generateUrgencyReport(today);

        assertTrue(report.contains("Due in 48 hours"));
        assertTrue(report.contains("This Week"));
        assertTrue(report.contains("Project 2"));
        assertTrue(report.contains("Midterm"));

        try
        {
            manager.generateUrgencyReport(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }
    }


    /**
     * Tests urgency boundary dates
     */
    public void testUrgencyBoundaries()
    {
        manager.addTask(
            new Assignment("CS", "Late", today.minusDays(1), 2.0, 10.0));

        manager.addTask(new Assignment("CS", "Today", today, 2.0, 10.0));

        manager.addTask(
            new Assignment("CS", "Week", today.plusDays(7), 2.0, 10.0));

        manager.addTask(
            new Assignment("CS", "Future", today.plusDays(8), 2.0, 10.0));

        String report = manager.generateUrgencyReport(today);

        assertTrue(report.contains("Overdue:\n"));
        assertTrue(report.contains("Late"));
        assertTrue(report.contains("Today"));
        assertTrue(report.contains("Week"));
        assertTrue(report.contains("Upcoming:\n"));
        assertTrue(report.contains("Future"));
    }


    /**
     * Tests the course breakdown
     */
    public void testGenerateCourseReport()
    {
        assertTrue(
            manager.generateCourseReport().contains("Nothing is tracked"));

        manager.addTask(assignment);
        manager.addTask(exam);

        String report = manager.generateCourseReport();

        assertTrue(report.contains("CS 2114"));
        assertTrue(report.contains("STAT 3005"));
        assertTrue(report.contains("1 assignments"));
        assertTrue(report.contains("1 exams"));
    }


    /**
     * Tests listing all tasks
     */
    public void testGetAllTasks()
    {
        assertTrue(manager.getAllTasks().contains("Nothing is tracked"));

        manager.addTask(assignment);
        manager.addTask(exam);

        String result = manager.getAllTasks();

        assertTrue(result.contains("Project 2"));
        assertTrue(result.contains("Midterm"));
    }
    public void testCompleteTask()
    {
        TaskManager manager = new TaskManager();

        Task task = new Assignment(
            "CS 2114", "Project 2",
            LocalDate.of(2026, 10, 28), 5, 20);

        manager.addTask(task);

        assertTrue(manager.completeTask(task));
        assertTrue(task.isCompleted());
        assertEquals(1, manager.getTaskCount());
    }


    public void testMarkPending()
    {
        TaskManager manager = new TaskManager();

        Task task = new Exam(
            "STAT 3104", "Midterm",
            LocalDate.of(2026, 11, 2), 5);

        manager.addTask(task);
        manager.completeTask(task);

        assertTrue(manager.markPending(task));
        assertFalse(task.isCompleted());
    }


    public void testCompletionReport()
    {
        TaskManager manager = new TaskManager();

        Task task1 = new Assignment(
            "CS 2114", "Project 2",
            LocalDate.of(2026, 10, 28), 5, 20);

        Task task2 = new Exam(
            "STAT 3104", "Midterm",
            LocalDate.of(2026, 11, 2), 5);

        manager.addTask(task1);
        manager.addTask(task2);

        manager.completeTask(task1);

        String report = manager.generateCompletionReport();

        assertTrue(report.contains("Pending: 1"));
        assertTrue(report.contains("Completed: 1"));
        assertTrue(report.contains("Project 2"));
        assertTrue(report.contains("Midterm"));
    }
}
