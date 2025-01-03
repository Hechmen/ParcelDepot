import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogTest {
    @Test
    void testSingletonInstance() {
        Log log1 = Log.getInstance();
        Log log2 = Log.getInstance();

        assertSame(log1, log2, "Both instances should be the same");
    }

    @Test
    void testLogAddition() {
        Log log = Log.getInstance();
        log.clearLog(); // Clear logs to ensure a clean state
        log.addLog("Test log entry");

        assertTrue(log.getLogs().contains("Test log entry"));
    }
    @Test
    void testSingletonLog() {
        Log log1 = Log.getInstance();
        Log log2 = Log.getInstance();

        assertSame(log1, log2, "Log instances should be the same");
        log1.clearLog();
        log1.addLog("Test entry");
        assertTrue(log2.getLogs().contains("Test entry"), "Log entries should be shared between instances");
    }
    @Test
    void testLogBehavior() {
        Log log = Log.getInstance();
        log.clearLog();
        log.addLog("Test message");

        assertTrue(log.getLogs().contains("Test message"), "Log should contain the added message");
    }

}
