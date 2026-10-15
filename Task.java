package CheckItOff;

import java.time.LocalDate;
import java.util.Objects;

// -------------------------------------------------------------------------
/**
 * Stores the information shared by assignments and exams.
 *
 * @author Nishitha Kancharla
 * @version 2026.10.14
 */
public abstract class Task
{
    // ~ Fields ................................................................

    private final String course;
    private final String title;
    private final LocalDate dueDate;

    // ~ Constructors ..........................................................

    /**
     * Creates a task with a course, title, and due date.
     * Past due dates are allowed.
     *
     * @param course
     *            the course name
     * @param title
     *            the task title
     * @param dueDate
     *            the task's due date
     * @throws IllegalArgumentException
     *             if the course or title is null or blank,
     *             or the due date is null
     */
    public Task(String course, String title, LocalDate dueDate)
    {
        if (course == null || course.trim().isEmpty())
        {
            throw new IllegalArgumentException("Invalid course");
        }

        if (title == null || title.trim().isEmpty())
        {
            throw new IllegalArgumentException("Invalid title");
        }

        if (dueDate == null)
        {
            throw new IllegalArgumentException("Invalid due date");
        }

        this.course = course;
        this.title = title;
        this.dueDate = dueDate;
    }

    // ~ Public Methods ........................................................

    /**
     * Gets the course name.
     *
     * @return the course
     */
    public String getCourse()
    {
        return course;
    }


    /**
     * Gets the task title.
     *
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }


    /**
     * Gets the due date.
     *
     * @return the due date
     */
    public LocalDate getDueDate()
    {
        return dueDate;
    }


    /**
     * Compares tasks using their type, course, title, and due date.
     *
     * @param obj
     *            the object to compare
     * @return true if both objects represent the same task
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }

        Task other = (Task)obj;

        return course.equals(other.course)
            && title.equals(other.title)
            && dueDate.equals(other.dueDate);
    }


    /**
     * Generates a hash code using the fields that determine equality.
     *
     * @return the task's hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getClass(), course, title, dueDate);
    }


    /**
     * Returns the shared task information as text.
     *
     * @return the task description
     */
    @Override
    public String toString()
    {
        return "Course: " + course + ", Title: " + title + ", Due: "
            + dueDate;
    }
}
