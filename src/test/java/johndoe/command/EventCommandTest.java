package johndoe.command;

import johndoe.command.EventCommand;
import johndoe.ui.JohnDoeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
    @Test
    public void testSwapFromToParameters() {
        try {
            new EventCommand("lecture /to 1pm /from 2pm");
        } catch (JohnDoeException e) {
            String errorMessage = "  '/from' must be before '/to'.\n"
                    + "  Enter 'event' for more help.\n\n> ";
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
