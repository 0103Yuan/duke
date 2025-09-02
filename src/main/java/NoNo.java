import java.util.Arrays;
import java.util.Scanner;

public class NoNo {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NoNo");
        System.out.println("What can I do for you?\n");

        String instruction;
        String[] taskList = new String[100];
        Scanner in = new Scanner(System.in);
        instruction = in.nextLine();
        int i = 0;
        while (!instruction.equals("bye")){
            if (instruction.equals("list")) {
                System.out.println("to do list: ");
                if (i == 0) {
                    System.out.println("no task added yet\n");
                } else {
                    for (int j = 0; j < i; j++) {
                        System.out.println((j+1) + ". " + taskList[j] + "\n");
                    }
                }
            } else {
                taskList[i] = instruction;
                System.out.println("new task added: " + instruction + "\n");
                i++;
            }
            instruction = in.nextLine(); // update instruction
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
