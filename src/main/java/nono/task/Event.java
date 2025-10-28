package nono.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, INPUT_FORMAT);
        this.end = LocalDateTime.parse(end, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + start.format(OUTPUT_FORMAT)
                + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + start.format(INPUT_FORMAT)
                + " | " + end.format(INPUT_FORMAT);
    }
}
