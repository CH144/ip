package johndoe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import johndoe.exception.JohnDoeException;

public class EventCommandTest {
    private static final String HELP_SUFFIX = "  Enter 'event' for more help.\n\n";

    @Test
    public void constructEvent_keywordsAreSubstring_exceptionThrown() {
        try {
            new EventCommand("meeting/from 01/01/2026 0000 /to 01/01/2026 0100");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  There should be spaces before and after '/from' and '/to'.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructEvent_keywordsRepeated_exceptionThrown() {
        try {
            new EventCommand("meeting /from /from 01/01/2026 0000 /to 01/01/2026 0100");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Exactly one '/from' and one '/to' must be used.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructEvent_swapFromTo_exceptionThrown() {
        try {
            new EventCommand("meeting /to 01/01/2026 0100 /from 01/01/2026 0000");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  '/from' must be before '/to'.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructEvent_noName_exceptionThrown() {
        try {
            new EventCommand("/from 01/01/2026 0000 /to 01/01/2026 0100");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Please provide a task name.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructEvent_noStart_exceptionThrown() {
        try {
            new EventCommand("meeting /from /to 01/01/2026 0100");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Please provide a start time.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructEvent_noEnd_exceptionThrown() {
        try {
            new EventCommand("meeting /from 01/01/2026 0000 /to");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Please provide an end time.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void constructEvent_invalidDateTimeFormat_exceptionThrown() {
        try {
            new EventCommand("meeting /from 01-01-2026 00:00 /to 01/01/2026 0100");
        } catch (JohnDoeException e) {
            String errorMessage =
                    "  Invalid date & time format.\n" + HELP_SUFFIX;
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
