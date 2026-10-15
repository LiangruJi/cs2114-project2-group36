# cs2114-project2-group36
Building a task manager that tracks assignments, exams, due dates, and study priorities.
# Check It Off
Check It Off is a Java application for tracking assignments, exams, due dates, and study priorities.
## Features

## How to Run

Importing the Project into Eclipse
- Open Eclipse IDE.
- Select File > Import.
- Select Git > Projects from Git.
- Choose Clone URI and enter the repository URL: https://github.com/LiangruJi/cs2114-project2-group36.git
- Complete the cloning process.
- Select Import existing Eclipse projects.
- Select the Check It Off project and click Finish.

If the repository has already been cloned, select File > Import > General > Existing Projects into Workspace and choose the local project folder.

Compiling the Application
- Open the Check It Off project in Project Explorer.
- Select Project > Build Automatically and ensure it is enabled.
- Select Project > Clean to rebuild the project.
- Check the Problems tab for compilation errors.

Running the Application
- Open CheckItOffApp.java.
- Locate the main method.
- Right-click the file and select Run As > Java Application.
- The program should start in Eclipse's Console window.
- Follow the displayed menu instructions to interact with the task manager.

Running JUnit Tests
- Open the project in Eclipse.
- Confirm that the appropriate JUnit library is included in the Java Build Path.
- Right-click the project in Project Explorer.
- Select Run As > JUnit Test to run the available tests.
- View the results in Eclipse's JUnit tab.

A green test bar indicates that all discovered and executed tests passed. A red bar indicates that at least one test failed or produced an error. Individual test files can also be executed by right-clicking the file and selecting Run As > JUnit Test.

## System Diagram
<img width="471" height="367" alt="image" src="https://github.com/user-attachments/assets/96b110d1-298b-4ae6-a296-aa117db3304b" />

## Reflection
We planned to originally use a generic LinkedChain<T> to store tasks while keeping the logic separate from the data structure, but as we began coding we focused on developing classes and testing their individual behavior before connecting everything together for the app. Our app consists of different classes including Task, Assignment, Exam, TaskManager and the app itself, CheckItOff. For the TaskManager we implemented the manager separately from the interface so that it can be tested without requiring console input. This also makes it easier to identify whether an error comes from task management or user input. We also expand our testing plan and included cases for null input, duplicate tasks and missing tasks. These cases help us make sure that the application runs properly when the user provides unexpected inputs. Our project uses the generic LinkedChain<T> provided for the assignment and we did not make an modifications, and this is used in our project to store objects representing assignments and exams through their parent type. Our main unknown in our original scope was how to use the current date to automatically determine the urgency. We identified Java's LocalDate as a way to represent due dats and LocalDate.now() to get the current date without requiring the user to enter it. Another challenge was understanding how to use the generic linked chain with multiple tasks, and we addressed this by separating the shared task from the assignment and exam information. TaskManager works with Task class while the other classes have their own properties. From this time before confirming that the prototype is complete, we're still having trouble figuring out our branches and how to push/pull our changes into the main branch. We have to confirm that the application can start and display the main menu, users can add assignments and exams through the menu, duplicate tasks are rejected, the urgency and course report are correctly organized, and that all the tests compile and pass. 

## Next Steps
We still have to finalize the task, assignment and exam classes and confirm that the constructors, getters, and task descriptions are implemented. We still have to verify the urgency and course reports and making sure that tasks are assigned to the correct group and that they are counted separately, especially the courses with multiple assignments. We eventually have to connect the app with the TaskManager so that the users can actually add tasks and view reports. We have to ensure that there are messages when there is an invalid response. We will run the entire application and JUnit test together and check for other issues before our final presentation. We had a couple stretch goals and one that we plan to attempt is the urgent assignments and being able to help users prioritize which assignments to start first. We will probably drop the goal about projects with milestones because this requires many different classes and tests, and we want to prioritize the complete application of assignments and exams before exploring. 

## Attribution

| File | Author | Contribution |
|---|---|
| `LinkedChain.java` | CS 2114 Staff |
| `LinkedChainTest.java` | TBD |
| `Task.java` | Nishitha Kancharla |
| `TaskTest.java` | Nishitha Kancharla |
| `Assignment.java` | Liangru Ji |
| `AssignmentTest.java` | Liangru Ji |
| `Exam.java` | Liangru Ji |
| `ExamTest.java` | Liangru Ji |
| `TaskManager.java` | Sydney Yang  | Implemented the storage operations, duplicate checking, task counting and report; GenAI assisted in creating the reports (urgency and course)
| `TaskManagerTest.java` | Sydney Yang | Developed tests for adding, removing, finding and reporting tasks; GenAI assisted in cleaning up code and figuring out what tests should run 
| `CheckItOffApp.java` | TBD |
| `CheckItOffAppTest.java` | TBD |

## GenAI Note
Our group used GenAI tools as an aid while working on Project 2. It was used to help interpret assignment requirements and developing the test cases. Specifically for TaskManager, GenAI was used to assist the development of the urgency and course reports, and null and duplicate handling. 
