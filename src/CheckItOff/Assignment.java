package CheckItOff;

import java.time.LocalDate;

// -------------------------------------------------------------------------
/**
 * Represents an assignment with estimated hours and grade percentage.
 *
 * @author Liangru Ji
 * @version 2026.10.14
 */
public class Assignment
    extends Task
{
    // ~ Fields ................................................................

    private double estimatedHours;
    private double gradePercentage;

    // ~ Constructors ..........................................................

    /**
     * Creates a new assignment.
     *
     * @param course
     *            the course name
     * @param title
     *            the assignment title
     * @param dueDate
     *            the due date
     * @param estimatedHours
     *            the estimated completion hours
     * @param gradePercentage
     *            the percentage of the course grade
     */
    public Assignment(
        String course,
        String title,
        LocalDate dueDate,
        double estimatedHours,
        double gradePercentage)
    {
        super(course, title, dueDate);

        if (estimatedHours < 0 || !Double.isFinite(estimatedHours))
        {
            throw new IllegalArgumentException("Invalid estimated hours");
        }

        if (gradePercentage < 0 || gradePercentage > 100
            || !Double.isFinite(gradePercentage))
        {
            throw new IllegalArgumentException("Invalid grade percentage");
        }

        this.estimatedHours = estimatedHours;
        this.gradePercentage = gradePercentage;
    }

    // ~ Public Methods ........................................................


    /**
     * Gets the estimated completion hours.
     *
     * @return the estimated hours
     */
    public double getEstimatedHours()
    {
        return estimatedHours;
    }


    /**
     * Gets the grade percentage.
     *
     * @return the grade percentage
     */
    public double getGradePercentage()
    {
        return gradePercentage;
    }


    /**
     * Returns the assignment information.
     *
     * @return the assignment as text
     */
    @Override
    public String toString()
    {
        return super.toString() + ", Hours: " + estimatedHours + ", Grade: "
            + gradePercentage + "%";
    }
}
