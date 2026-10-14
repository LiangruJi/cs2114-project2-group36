package CheckItOff;

import java.time.LocalDate;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Assignment class.
 *
 * @author Liangru Ji
 * @version 2026.10.14
 */
public class AssignmentTest
    extends TestCase
{
    private Assignment assignment;
    private LocalDate dueDate;

    /**
     * Creates an assignment before each test.
     */
    public void setUp()
    {
        dueDate = LocalDate.of(2026, 10, 30);
        assignment = new Assignment("CS 2114", "Project 2", dueDate, 3.0, 10.0);
    }


    /**
     * Tests the Assignment getters.
     */
    public void testGetters()
    {
        assertEquals(3.0, assignment.getEstimatedHours(), 0.001);
        assertEquals(10.0, assignment.getGradePercentage(), 0.001);
    }


    /**
     * Tests allowed boundary values.
     */
    public void testBoundaryValues()
    {
        Assignment boundary =
            new Assignment("CS 2114", "Practice", dueDate, 0.0, 100.0);

        assertEquals(0.0, boundary.getEstimatedHours(), 0.001);
        assertEquals(100.0, boundary.getGradePercentage(), 0.001);
    }


    /**
     * Tests that negative estimated hours are rejected.
     */
    public void testInvalidEstimatedHours()
    {
        try
        {
            new Assignment("CS 2114", "Project 2", dueDate, -1.0, 10.0);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }
    }


    /**
     * Tests that an invalid grade percentage is rejected.
     */
    public void testInvalidGradePercentage()
    {
        try
        {
            new Assignment("CS 2114", "Project 2", dueDate, 3.0, 101.0);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }
    }


    /**
     * Tests the assignment's text representation.
     */
    public void testToString()
    {
        String result = assignment.toString();

        assertTrue(result.contains("3.0"));
        assertTrue(result.contains("10.0"));
    }
}
