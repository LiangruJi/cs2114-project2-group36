package CheckItOff;

import java.time.LocalDate;

// -------------------------------------------------------------------------
/**
 * Represents an exam with a planned number of study days.
 *
 * @author Liangru Ji
 * @version 2026.10.14
 */
public class Exam
    extends Task
{
    // ~ Fields ................................................................

    private int studyDays;

    // ~ Constructors ..........................................................

    /**
     * Creates a new exam.
     *
     * @param course
     *            the course name
     * @param title
     *            the exam title
     * @param dueDate
     *            the exam date
     * @param studyDays
     *            the planned number of study days
     */
    public Exam(String course, String title, LocalDate dueDate, int studyDays)
    {
        super(course, title, dueDate);

        if (studyDays < 1)
        {
            throw new IllegalArgumentException("Invalid study days");
        }

        this.studyDays = studyDays;
    }

    // ~ Public Methods ........................................................


    /**
     * Gets the planned number of study days.
     *
     * @return the number of study days
     */
    public int getStudyDays()
    {
        return studyDays;
    }


    /**
     * Returns the exam information.
     *
     * @return the exam as text
     */
    @Override
    public String toString()
    {
        return super.toString() + ", Study Days: " + studyDays;
    }
}
