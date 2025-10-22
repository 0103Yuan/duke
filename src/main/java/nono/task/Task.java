package nono.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(boolean status){
        isDone = status;
    }

    public String toString() {  // change format
        return this.description;
    }

    public abstract String toDataString();
}