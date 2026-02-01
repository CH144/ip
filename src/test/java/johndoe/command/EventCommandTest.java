package johndoe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import johndoe.exception.JohnDoeException;

public class EventCommandTest {
    @Test
    public void constructEvent_swapFromTo_exceptionThrown() {
        try {
            new EventCommand("lecture /to 1pm /from 2pm");
        } catch (JohnDoeException e) {
            String errorMessage = "  '/from' must be before '/to'.\n"
                    + "  Enter 'event' for more help.\n\n> ";
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
