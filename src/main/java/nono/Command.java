package nono;

public class Command {
    public enum Type {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE
    }

    private Type type;
    private String description;
    private String[] details;
    private int taskIndex;

    public Command(Type type) {
        this.type = type;
    }

    public Command(Type type, String description) {
        this.type = type;
        this.description = description;
    }

    public Command(Type type, int taskIndex) {
        this.type = type;
        this.taskIndex = taskIndex;
    }

    public Command(Type type, String[] details) {
        this.type = type;
        this.details = details;
    }

    // Getters
    public Type getType() { return type; }
    public String getDescription() { return description; }
    public String[] getDetails() { return details; }
    public int getTaskIndex() { return taskIndex; }
}
