package johndoe.command;

import johndoe.command.DeadlineCommand;
import johndoe.exception.JohnDoeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    @Test
    public void testNoTaskName() {
        try {
            new DeadlineCommand("/by 1pm");
        } catch (JohnDoeException e) {
            String errorMessage = "  Please provide a task name.\n"
                    + "  Enter 'deadline' for more help.\n\n> ";
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
