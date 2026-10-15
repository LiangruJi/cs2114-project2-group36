package CheckItOff;

import java.time.LocalDate;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests Task through its Assignment and Exam subclasses.
 *
 * @author Nishitha Kancharla
 * @version 2026.10.14
 */
public class TaskTest
    extends TestCase
{
    // ~ Fields ................................................................

    private Task assignment;
    private Task exam;
    private LocalDate dueDate;

    // ~ Setup .................................................................

    /**
     * Creates tasks before each test.
     */
    @Override
    public void setUp()
    {
        dueDate = LocalDate.of(2026, 10, 20);

        assignment = new Assignment(
            "CS 2114", "Project 2", dueDate, 3.0, 10.0);

        exam = new Exam(
            "MATH 2214", "Exam 1", dueDate, 4);
    }

    // ~ Public Methods ........................................................

    /**
     * Tests that the constructor saves all shared information.
     */
    public void testConstructor()
    {
        assertEquals("CS 2114", assignment.getCourse());
        assertEquals("Project 2", assignment.getTitle());
        assertEquals(dueDate, assignment.getDueDate());
    }


    /**
     * Tests the course getter for both task types.
     */
    public void testGetCourse()
    {
        assertEquals("CS 2114", assignment.getCourse());
        assertEquals("MATH 2214", exam.getCourse());
    }


    /**
     * Tests the title getter for both task types.
     */
    public void testGetTitle()
    {
        assertEquals("Project 2", assignment.getTitle());
        assertEquals("Exam 1", exam.getTitle());
    }


    /**
     * Tests the due date getter for both task types.
     */
    public void testGetDueDate()
    {
        assertEquals(dueDate, assignment.getDueDate());
        assertEquals(dueDate, exam.getDueDate());
    }


    /**
     * Tests that past due dates are accepted.
     */
    public void testPastDueDate()
    {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        Task pastTask = new Assignment(
            "CS 2114", "Old Homework", pastDate, 1.0, 2.0);

        assertEquals(pastDate, pastTask.getDueDate());
        assertTrue(pastTask.toString().contains(pastDate.toString()));
    }


    /**
     * Tests that a null course is rejected.
     */
    public void testNullCourse()
    {
        try
        {
            new Assignment(null, "Project 2", dueDate, 3.0, 10.0);
            fail("A null course should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid course", exception.getMessage());
        }
    }


    /**
     * Tests that an empty course is rejected.
     */
    public void testEmptyCourse()
    {
        try
        {
            new Assignment("", "Project 2", dueDate, 3.0, 10.0);
            fail("An empty course should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid course", exception.getMessage());
        }
    }


    /**
     * Tests that a whitespace-only course is rejected.
     */
    public void testBlankCourse()
    {
        try
        {
            new Assignment(" \t ", "Project 2", dueDate, 3.0, 10.0);
            fail("A blank course should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid course", exception.getMessage());
        }
    }


    /**
     * Tests that a null title is rejected.
     */
    public void testNullTitle()
    {
        try
        {
            new Exam("MATH 2214", null, dueDate, 4);
            fail("A null title should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid title", exception.getMessage());
        }
    }


    /**
     * Tests that an empty title is rejected.
     */
    public void testEmptyTitle()
    {
        try
        {
            new Exam("MATH 2214", "", dueDate, 4);
            fail("An empty title should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid title", exception.getMessage());
        }
    }


    /**
     * Tests that a whitespace-only title is rejected.
     */
    public void testBlankTitle()
    {
        try
        {
            new Exam("MATH 2214", " \t ", dueDate, 4);
            fail("A blank title should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid title", exception.getMessage());
        }
    }


    /**
     * Tests that a null due date is rejected.
     */
    public void testNullDueDate()
    {
        try
        {
            new Assignment("CS 2114", "Project 2", null, 3.0, 10.0);
            fail("A null due date should be rejected");
        }
        catch (IllegalArgumentException exception)
        {
            assertEquals("Invalid due date", exception.getMessage());
        }
    }


    /**
     * Tests that a task equals itself.
     */
    public void testEqualsSelf()
    {
        assertTrue(assignment.equals(assignment));
    }


    /**
     * Tests equality in both directions for matching assignments.
     * Assignment-specific fields do not determine task identity.
     */
    public void testEqualsMatchingAssignments()
    {
        Task matching = new Assignment(
            "CS 2114", "Project 2", dueDate, 6.0, 20.0);

        assertTrue(assignment.equals(matching));
        assertTrue(matching.equals(assignment));
    }


    /**
     * Tests that matching exams are equal despite different study days.
     */
    public void testEqualsMatchingExams()
    {
        Task matching = new Exam(
            "MATH 2214", "Exam 1", dueDate, 7);

        assertTrue(exam.equals(matching));
        assertTrue(matching.equals(exam));
    }


    /**
     * Tests that equality is transitive.
     */
    public void testEqualsTransitive()
    {
        Task second = new Assignment(
            "CS 2114", "Project 2", dueDate, 4.0, 15.0);
        Task third = new Assignment(
            "CS 2114", "Project 2", dueDate, 5.0, 20.0);

        assertTrue(assignment.equals(second));
        assertTrue(second.equals(third));
        assertTrue(assignment.equals(third));
    }


    /**
     * Tests comparison with null.
     */
    public void testEqualsNull()
    {
        assertFalse(assignment.equals(null));
    }


    /**
     * Tests comparison with an object that is not a task.
     */
    public void testEqualsOtherObject()
    {
        assertFalse(assignment.equals("Project 2"));
    }


    /**
     * Tests that an assignment and exam are different task types.
     */
    public void testEqualsDifferentTaskTypes()
    {
        Task sameDetails = new Exam(
            "CS 2114", "Project 2", dueDate, 4);

        assertFalse(assignment.equals(sameDetails));
        assertFalse(sameDetails.equals(assignment));
    }


    /**
     * Tests that different courses produce unequal tasks.
     */
    public void testEqualsDifferentCourse()
    {
        Task different = new Assignment(
            "CS 1114", "Project 2", dueDate, 3.0, 10.0);

        assertFalse(assignment.equals(different));
    }


    /**
     * Tests that different titles produce unequal tasks.
     */
    public void testEqualsDifferentTitle()
    {
        Task different = new Assignment(
            "CS 2114", "Project 3", dueDate, 3.0, 10.0);

        assertFalse(assignment.equals(different));
    }


    /**
     * Tests that different due dates produce unequal tasks.
     */
    public void testEqualsDifferentDueDate()
    {
        Task different = new Assignment(
            "CS 2114", "Project 2", dueDate.plusDays(1), 3.0, 10.0);

        assertFalse(assignment.equals(different));
    }


    /**
     * Tests that equal tasks have equal hash codes.
     */
    public void testHashCodeEqualTasks()
    {
        Task matchingAssignment = new Assignment(
            "CS 2114", "Project 2", dueDate, 6.0, 20.0);
        Task matchingExam = new Exam(
            "MATH 2214", "Exam 1", dueDate, 7);

        assertEquals(
            assignment.hashCode(), matchingAssignment.hashCode());
        assertEquals(exam.hashCode(), matchingExam.hashCode());
    }


    /**
     * Tests that repeated hash code calls are consistent.
     */
    public void testHashCodeConsistent()
    {
        int original = assignment.hashCode();

        assertEquals(original, assignment.hashCode());
        assertEquals(original, assignment.hashCode());
    }


    /**
     * Tests the shared description through Assignment.
     */
    public void testToStringAssignment()
    {
        assertEquals(
            "Course: CS 2114, Title: Project 2, Due: 2026-10-20"
                + ", Hours: 3.0, Grade: 10.0%",
            assignment.toString());
    }


    /**
     * Tests the shared description through Exam.
     */
    public void testToStringExam()
    {
        assertEquals(
            "Course: MATH 2214, Title: Exam 1, Due: 2026-10-20"
                + ", Study Days: 4",
            exam.toString());
    }
}
