package CheckItOff;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * // -------------------------------------------------------------------------
 * /** Manages assignments and exams using the LinkedChain
 * 
 * @author sydneyyang
 * @version Oct 15, 2026
 */
public class TaskManager
{
    private LinkedChain<Task> tasks;

    /**
     * Creates an empty task manager
     */
    public TaskManager()
    {
        tasks = new LinkedChain<Task>();
    }


    /**
     * Adds tasks if not already tracked
     * 
     * @param task
     *            to add
     * @return true if added, false if duplicate
     */
    public boolean addTask(Task task)
    {
        if (task == null)
        {
            throw new IllegalArgumentException("Task cannot be null");
        }

        if (tasks.contains(task))
        {
            return false;
        }

        tasks.add(task);
        return true;
    }


    /**
     * Removes task
     * 
     * @param task
     *            to remove
     * @return true if removed
     */
    public boolean removeTask(Task task)
    {
        if (task == null)
        {
            throw new IllegalArgumentException("Task cannot be null");
        }
        return tasks.remove(task);
    }


    /**
     * Checks whether task exists
     * 
     * @param task
     *            to find
     * @return true if the task exists
     */
    public boolean containsTask(Task task)
    {
        if (task == null)
        {
            throw new IllegalArgumentException("Task cannot be null");
        }
        return tasks.contains(task);
    }


    /**
     * Returns number of tracked tasks
     * 
     * @return number of tasks
     */
    public int getTaskCount()
    {
        return tasks.size();
    }


    /**
     * Groups tasks by urgency based on the date
     * 
     * @param today
     *            current date
     * @return urgency report
     */
    public String generateUrgencyReport(LocalDate today)
    {
        if (today == null)
        {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (tasks.isEmpty())
        {
            return "Nothing is tracked. Add an item first!";
        }
        StringBuilder overdue = new StringBuilder("Overdue:\n");
        StringBuilder soon = new StringBuilder("Due in 48 hours:\n");
        StringBuilder week = new StringBuilder("Due this week:\n");
        StringBuilder upcoming = new StringBuilder("Upcoming:\n");
        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = tasks.get(i);
            long days = ChronoUnit.DAYS.between(today, task.getDueDate());

            if (days < 0)
            {
                overdue.append(task).append("\n");
            }
            else if (days <= 1)
            {
                soon.append(task).append("\n");
            }
            else if (days <= 7)
            {
                week.append(task).append("\n");
            }
            else
            {
                upcoming.append(task).append("\n");
            }
        }

        return overdue.toString() + "\n" + soon.toString() + "\n"
            + week.toString() + "\n" + upcoming.toString();
    }


    /**
     * Counts assignments and exams for each course
     * 
     * @return course report
     */

    public String generateCourseReport()
    {
        if (tasks.isEmpty())
        {
            return "Nothing is tracked. Add an item first.";
        }

        StringBuilder report = new StringBuilder("Course Breakdown:\n");

        for (int i = 0; i < tasks.size(); i++)
        {
            Task current = tasks.get(i);
            String course = current.getCourse();

            boolean alreadyCounted = false;

            for (int j = 0; j < i; j++)
            {
                if (tasks.get(j).getCourse().equals(course))
                {
                    alreadyCounted = true;
                }
            }

            if (!alreadyCounted)
            {
                int assignments = 0;
                int exams = 0;

                for (int j = 0; j < tasks.size(); j++)
                {
                    Task task = tasks.get(j);

                    if (task.getCourse().equals(course))
                    {
                        if (task instanceof Assignment)
                        {
                            assignments++;
                        }
                        else if (task instanceof Exam)
                        {
                            exams++;
                        }
                    }
                }

                report.append(course).append(": ").append(assignments)
                    .append(" assignments, ").append(exams).append(" exams\n");
            }
        }

        return report.toString();
    }


    /**
     * Returns list of all tracked tasks
     * 
     * @return task list
     */
    public String getAllTasks()
    {
        if (tasks.isEmpty())
        {
            return "Nothing is tracked. Add an item first!";
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++)
        {
            result.append(tasks.get(i)).append("\n");
        }

        return result.toString();
    }
    /**
     * Marks a tracked task as completed.
     *
     * @param task
     *            task to complete
     * @return true if the task was found
     */
    public boolean completeTask(Task task)
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            Task current = tasks.get(i);

            if (current.equals(task))
            {
                current.setCompleted(true);
                return true;
            }
        }

        return false;
    }
    /**
     * Marks a completed task as pending.
     *
     * @param task
     *            task to update
     * @return true if the task was found
     */
    public boolean markPending(Task task)
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            Task current = tasks.get(i);

            if (current.equals(task))
            {
                current.setCompleted(false);
                return true;
            }
        }

        return false;
    }
    /**
     * Generates a report separating completed
     * and pending tasks.
     *
     * @return completion report
     */
    public String generateCompletionReport()
    {
        if (tasks.isEmpty())
        {
            return "Nothing is tracked. Add an item first.";
        }

        StringBuilder report = new StringBuilder();

        report.append("Pending Tasks:\n");

        int pending = 0;
        int completed = 0;

        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = tasks.get(i);

            if (!task.isCompleted())
            {
                report.append("- ").append(task).append("\n");
                pending++;
            }
        }

        if (pending == 0)
        {
            report.append("No pending tasks.\n");
        }

        report.append("\nCompleted Tasks:\n");

        for (int i = 0; i < tasks.size(); i++)
        {
            Task task = tasks.get(i);

            if (task.isCompleted())
            {
                report.append("- ").append(task).append("\n");
                completed++;
            }
        }

        if (completed == 0)
        {
            report.append("No completed tasks.\n");
        }

        report.append("\nPending: ").append(pending);
        report.append("\nCompleted: ").append(completed);

        return report.toString();
    }

}
