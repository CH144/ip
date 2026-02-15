package johndoe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import johndoe.exception.JohnDoeException;

public class DeadlineCommandTest {
    private static final String HELP_SUFFIX = "  Enter 'deadline' for more help.\n\n";

    @Test
    public void constructDeadline_byKeywordIsSubstring_exceptionThrown() {
        try {
            new DeadlineCommand("sleep/by 01/01/2026 0030");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  There should be spaces before and after '/by'.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructDeadline_byKeywordRepeated_exceptionThrown() {
        try {
            new DeadlineCommand("sleep /by /by 01/01/2026 0030");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Exactly one '/by' must be used.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructDeadline_noName_exceptionThrown() {
        try {
            new DeadlineCommand("/by 01/01/2026 0030");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Please provide a task name.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructDeadline_noDeadline_exceptionThrown() {
        try {
            new DeadlineCommand("sleep /by");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Please provide a deadline.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructDeadline_invalidDateTimeFormat_exceptionThrown() {
        try {
            new DeadlineCommand("sleep /by 01-01-2026 00:30");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Invalid date & time format.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
