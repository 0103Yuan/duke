import java.util.Scanner;

public class NoNo {
    public static void main(String[] args) {
        System.out.println("Hello! I'm NoNo");
        System.out.println("What can I do for you?\n");

        String instruction;
        Scanner in = new Scanner(System.in);
        instruction = in.nextLine();
        while (!instruction.equals("bye")){
            System.out.println(instruction + "\n");
            instruction = in.nextLine(); // update instruction
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
