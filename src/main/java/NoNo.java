import java.util.Arrays;
import java.util.Scanner;

public class NoNo {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NoNo");
        System.out.println("What can I do for you?\n");

        Task[] taskList = new Task[100];
        int taskNum = 0;

        Scanner in = new Scanner(System.in);
        String instruction = in.nextLine();

        while (!instruction.equals("bye")) {
            try {
                if (instruction.startsWith("mark")) {
                    String[] words = instruction.trim().split("\\s+");
                    if (words.length != 2) {
                        throw new UserInputException("Sorry, please enter: mark task_number\n");
                    }
                    int numDone;
                    try {
                        numDone = Integer.parseInt(words[1]) - 1; // convert string to num
                    } catch (NumberFormatException error) {
                        throw new UserInputException("Sorry, task number must be an integer.\n");
                    }
                    if (numDone < 0 || numDone >= taskNum) {
                        throw new UserInputException("Sorry, invalid task number, enter list to check current task list.\n");
                    } else {
                        taskList[numDone].markAsDone(true);
                        System.out.println("Nice! The task is marked as done:");
                        System.out.println("[" + taskList[numDone].getStatusIcon() + "] " + taskList[numDone] + "\n");
                    }
                } else if (instruction.startsWith("unmark")) {
                    String[] words = instruction.trim().split("\\s+");;
                    if (words.length != 2) {
                        throw new UserInputException("Sorry, please enter: unmark task_number\n");
                    }
                    int numDone;
                    try {
                        numDone = Integer.parseInt(words[1]) - 1;
                    } catch (NumberFormatException error) {
                        throw new UserInputException("Sorry, task number must be an integer.\n");
                    }
                    if (numDone < 0 || numDone >= taskNum) {
                        throw new UserInputException("Sorry, invalid task number, enter list to check current task list.\n");
                    } else {
                        taskList[numDone].markAsDone(false);
                        System.out.println("OK! The task is marked as not done yet:");
                        System.out.println("[" + taskList[numDone].getStatusIcon() + "] " + taskList[numDone] + "\n");
                    }
                } else if (instruction.equals("list")) {
                    System.out.println("to do list: ");
                    if (taskNum == 0) {
                        System.out.println("no task added yet\n");
                    } else {
                        for (int j = 0; j < taskNum; j++) {
                            System.out.println((j + 1) + ". " + "[" + taskList[j].getStatusIcon() + "]" + taskList[j]);
                        }
                        System.out.println();
                    }
                } else if (instruction.startsWith("todo")) {  // e.g. todo borrow book
                    String description = instruction.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new UserInputException("Sorry, the to do description cannot be empty: todo ...\n");
                    }
                    Task t = new ToDo(description);
                    taskList[taskNum] = t;
                    System.out.println("new task added: " + t + "\n");
                    taskNum++;
                } else if (instruction.startsWith("deadline")) { // e.g. deadline return book /by Sunday
                    int byIdx = instruction.indexOf("/by");
                    if(byIdx == -1) {
                        throw new UserInputException("Sorry, deadline must contain: /by\n");
                    }
                    String description = instruction.substring(8, byIdx).trim();
                    String by = instruction.substring(byIdx + 3).trim();
                    if (description.isEmpty()) {
                        throw new UserInputException("Sorry, deadline description cannot be empty: deadline ...\n");
                    }
                    if (by.isEmpty()) {
                        throw new UserInputException("Sorry, deadline cannot be empty: /by ...\n");
                    }
                    Task t = new Deadline(description, by);
                    taskList[taskNum] = t;
                    System.out.println("new task added: " + t + "\n");
                    taskNum++;
                } else if (instruction.startsWith("event")) {  // e.g. event project meeting /from Mon 2pm /to 4pm
                    int fromIdx = instruction.indexOf("/from");
                    int toIdx = instruction.indexOf("/to");
                    if (fromIdx == -1 || toIdx == -1) {
                        throw new UserInputException("Sorry, event must contain: /from and /to\n");
                    }
                    String description = instruction.substring(5, fromIdx).trim();
                    String start = instruction.substring(fromIdx + 5, toIdx).trim();
                    String end = instruction.substring(toIdx + 3).trim();
                    if (description.isEmpty()) {
                        throw new UserInputException("Sorry, event description cannot be empty: event ...\n");
                    }
                    if (start.isEmpty()) {
                        throw new UserInputException("Sorry, start time cannot be empty: /from ...\n");
                    }
                    if (end.isEmpty()) {
                        throw new UserInputException("Sorry, end time cannot be empty: /to ...\n");
                    }
                    Task t = new Event(description, start, end);
                    taskList[taskNum] = t;
                    System.out.println("new task added: " + t + "\n");
                    taskNum++;
                } else {
                    throw new UserInputException("Sorry, I don't understand, try start with todo/deadline/event to add a task, list to see all the tasks, mark/unmark a task or bye to end the conversation.\n");
                }
            } catch (UserInputException error) {
                    System.out.println(error.getMessage());
            }
            instruction = in.nextLine(); // next instruction
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
