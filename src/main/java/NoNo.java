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

        while (!instruction.equals("bye")){
            if (instruction.startsWith("mark")) {
                String[] words = instruction.split(" ");
                if (words.length == 2) {
                    int numDone = Integer.parseInt(words[1]) - 1; // convert string to num
                    if (numDone < 0 || numDone >= taskNum ) {
                        System.out.println("Invalid task number\n");
                        instruction = in.nextLine(); // next instruction
                        continue;
                    } else {
                        taskList[numDone].markAsDone(true);
                        System.out.println("Nice! The task is marked as done:");
                        System.out.println("[" + taskList[numDone].getStatusIcon() + "] " + taskList[numDone] + "\n");
                    }
                }
            } else if (instruction.startsWith("unmark")) {
                String[] words = instruction.split(" ");
                if (words.length == 2) {
                    int numDone = Integer.parseInt(words[1]) - 1;
                    if (numDone < 0 || numDone >= taskNum ) {
                        System.out.println("Invalid task number\n");
                        instruction = in.nextLine(); // next instruction
                        continue;
                    } else {
                        taskList[numDone].markAsDone(false);
                        System.out.println("OK! The task is marked as not done yet:");
                        System.out.println("[" + taskList[numDone].getStatusIcon() + "] " + taskList[numDone] + "\n");
                    }
                }
            } else if (instruction.equals("list")) {
                System.out.println("to do list: ");
                if (taskNum == 0) {
                    System.out.println("no task added yet\n");
                } else {
                    for (int j = 0; j < taskNum; j++) {
                        System.out.println((j+1) + ". " + "[" + taskList[j].getStatusIcon() + "]" + taskList[j]);
                    }
                    System.out.println();
                }
            } else {
                Task t = new Task(instruction);
                taskList[taskNum] = t;
                System.out.println("new task added: " + t + "\n");
                taskNum++;
            }
            instruction = in.nextLine(); // next instruction
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
