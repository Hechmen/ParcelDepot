import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance;
    private StringBuffer logBuffer;
    private Log() {
        logBuffer = new StringBuffer();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
    public void addLog(String message) {
        logBuffer.append(message).append("\n");
    }
    public void writeLogToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(logBuffer.toString());
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

    public void clearLog() {
        logBuffer.setLength(0);
    }
    public void displayLog(){
        System.out.println(logBuffer.toString());
    }
    public String getLogs() {
        return logBuffer.toString();
    }
}
