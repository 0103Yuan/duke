package nono;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws UserInputException {
        if (index < 0 || index >= tasks.size()) {
            throw new UserInputException("Sorry, invalid task number.");
        }
        return tasks.remove(index);
    }

    public Task getTask(int index) throws UserInputException {
        if (index < 0 || index >= tasks.size()) {
            throw new UserInputException("Sorry, invalid task number.");
        }
        return tasks.get(index);
    }

    public void markTask(int index, boolean status) throws UserInputException {
        if (index < 0 || index >= tasks.size()) {
            throw new UserInputException("Sorry, invalid task number.");
        }
        tasks.get(index).markAsDone(status);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}