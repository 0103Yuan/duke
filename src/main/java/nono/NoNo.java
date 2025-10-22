package nono;

public class NoNo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public NoNo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                isExit = executeCommand(command);
            } catch (UserInputException error) {
                ui.showError(error.getMessage());
            }
        }
        ui.showGoodbye();
    }

    private boolean executeCommand(Command command) throws UserInputException {
        switch (command.getType()) {
            case BYE:
                storage.save(tasks.getAllTasks());
                return true;

            case LIST:
                ui.showTaskList(tasks);
                break;

            case MARK:
                tasks.markTask(command.getTaskIndex() - 1, true);
                ui.showMarkResult(tasks.getTask(command.getTaskIndex() - 1), true);
                storage.save(tasks.getAllTasks());
                break;

            case UNMARK:
                tasks.markTask(command.getTaskIndex() - 1, false);
                ui.showMarkResult(tasks.getTask(command.getTaskIndex() - 1), false);
                storage.save(tasks.getAllTasks());
                break;

            case TODO:
                Task todo = new ToDo(command.getDescription());
                tasks.addTask(todo);
                ui.showAddResult(todo);
                storage.save(tasks.getAllTasks());
                break;

            case DEADLINE:
                String[] deadlineDetails = command.getDetails();
                Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                tasks.addTask(deadline);
                ui.showAddResult(deadline);
                storage.save(tasks.getAllTasks());
                break;

            case EVENT:
                String[] eventDetails = command.getDetails();
                Task event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                tasks.addTask(event);
                ui.showAddResult(event);
                storage.save(tasks.getAllTasks());
                break;

            case DELETE:
                Task deletedTask = tasks.deleteTask(command.getTaskIndex() - 1);
                ui.showDeleteResult(deletedTask, tasks.size());
                storage.save(tasks.getAllTasks());
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        new NoNo("./data/nono.txt").run();
    }
}