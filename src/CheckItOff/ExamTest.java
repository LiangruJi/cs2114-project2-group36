package CheckItOff;

import java.time.LocalDate;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Exam class.
 *
 * @author Liangru Ji
 * @version 2026.10.14
 */
public class ExamTest
    extends TestCase
{
    // ~ Fields ................................................................

    private Exam exam;
    private LocalDate examDate;

    // ~ Constructors ..........................................................

    // ~ Public Methods ........................................................

    /**
     * Creates an exam before each test.
     */
    public void setUp()
    {
        examDate = LocalDate.of(2026, 10, 30);
        exam = new Exam("CS 2114", "Midterm", examDate, 4);
    }


    /**
     * Tests the study days getter.
     */
    public void testGetStudyDays()
    {
        assertEquals(4, exam.getStudyDays());
    }


    /**
     * Tests the minimum allowed number of study days.
     */
    public void testMinimumStudyDays()
    {
        Exam oneDayExam = new Exam("CS 2114", "Quiz", examDate, 1);

        assertEquals(1, oneDayExam.getStudyDays());
    }


    /**
     * Tests that zero study days are rejected.
     */
    public void testZeroStudyDays()
    {
        try
        {
            new Exam("CS 2114", "Midterm", examDate, 0);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }
    }


    /**
     * Tests that negative study days are rejected.
     */
    public void testNegativeStudyDays()
    {
        try
        {
            new Exam("CS 2114", "Midterm", examDate, -1);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }
    }


    /**
     * Tests the exam's text representation.
     */
    public void testToString()
    {
        String result = exam.toString();

        assertTrue(result.contains("4"));
    }
}
