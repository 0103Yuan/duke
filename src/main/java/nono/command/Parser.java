package nono.command;

import nono.exception.UserInputException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Command parse(String instruction) throws UserInputException {
        instruction = instruction.trim();

        if (instruction.equals("list")) {
            return new Command(Command.Type.LIST);
        } else if (instruction.equals("bye")) {
            return new Command(Command.Type.BYE);
        } else if (instruction.startsWith("mark")) {
            return parseMarkCommand(instruction, true);
        } else if (instruction.startsWith("unmark")) {
            return parseMarkCommand(instruction, false);
        } else if (instruction.startsWith("todo")) {
            return parseTodoCommand(instruction);
        } else if (instruction.startsWith("deadline")) {
            return parseDeadlineCommand(instruction);
        } else if (instruction.startsWith("event")) {
            return parseEventCommand(instruction);
        } else if (instruction.startsWith("delete")) {
            return parseDeleteCommand(instruction);
        } else {
            throw new UserInputException("Sorry, I don't understand, try start with:\n" +
                    " 'todo'/'deadline'/'event' - to add a task,\n" +
                    " 'delete'                  - to delete a task,\n" +
                    " 'list'                    - to see all the tasks,\n" +
                    " 'mark'/'unmark'           - to mark/unmark a task,\n" +
                    " 'bye'                     - to end the conversation.\n");
        }
    }

    private static void validateDate(String date) throws UserInputException {
        try {
            LocalDateTime.parse(date, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new UserInputException("Sorry, date/time must be in format: yyyy-mm-dd HH:mm\n");
        }
    }

    private static Command parseMarkCommand(String instruction, boolean isMark) throws UserInputException {
        String[] words = instruction.trim().split("\\s+");
        if (words.length != 2) {
            throw new UserInputException("Sorry, please enter: " + (isMark ? "mark" : "unmark") + " task_number\n");
        }
        try {
            int taskNum = Integer.parseInt(words[1]);
            return new Command(isMark ? Command.Type.MARK : Command.Type.UNMARK, taskNum);
        } catch (NumberFormatException e) {
            throw new UserInputException("Sorry, task number must be an integer.\n");
        }
    }

    private static Command parseTodoCommand(String instruction) throws UserInputException {
        String description = instruction.substring(4).trim();
        if (description.isEmpty()) {
            throw new UserInputException("Sorry, the to do description cannot be empty: todo ...\n");
        }
        return new Command(Command.Type.TODO, description);
    }

    private static Command parseDeadlineCommand(String instruction) throws UserInputException {
        int byIdx = instruction.indexOf("/by");
        if (byIdx == -1) {
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

        validateDate(by);

        return new Command(Command.Type.DEADLINE, new String[]{description, by});
    }

    private static Command parseEventCommand(String instruction) throws UserInputException {
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

        validateDate(start);
        validateDate(end);

        return new Command(Command.Type.EVENT, new String[]{description, start, end});
    }

    private static Command parseDeleteCommand(String instruction) throws UserInputException {
        String[] words = instruction.trim().split("\\s+");
        if (words.length != 2) {
            throw new UserInputException("Sorry, please enter: delete task_number\n");
        }
        try {
            int taskNum = Integer.parseInt(words[1]);
            return new Command(Command.Type.DELETE, taskNum);
        } catch (NumberFormatException e) {
            throw new UserInputException("Sorry, task number must be an integer.\n");
        }
    }
}
