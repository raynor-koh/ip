package bob.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/** Tests conversion from command keywords to command types. */
class CommandTypeTest {

    @Test
    void fromKeyword_knownKeywords_returnsMatchingCommandTypes() {
        for (CommandType commandType : CommandType.values()) {
            assertEquals(commandType, CommandType.fromKeyword(commandType.getKeyword()));
        }
    }

    @Test
    void fromKeyword_mixedCaseKeyword_returnsMatchingCommandType() {
        assertEquals(CommandType.DEADLINE, CommandType.fromKeyword("DeAdLiNe"));
    }

    @Test
    void fromKeyword_unknownKeyword_returnsNull() {
        assertNull(CommandType.fromKeyword("find"));
    }
}
