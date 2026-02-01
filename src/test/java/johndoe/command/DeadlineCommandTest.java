package johndoe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import johndoe.exception.JohnDoeException;

public class DeadlineCommandTest {
    @Test
    public void constructDeadline_noName_exceptionThrown() {
        try {
            new DeadlineCommand("/by 1pm");
        } catch (JohnDoeException e) {
            String errorMessage = "  Please provide a task name.\n"
                    + "  Enter 'deadline' for more help.\n\n> ";
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
