/*
    Imports
 */
import java.io.*;
import java.util.List;

/**
 * Utility for make easier the connection
 * with a file for writing it and reading.
 *
 * @version 0.0.1
 */
public class File {
    java.io.File file;

    public File(String PATH) {
        this.file = new java.io.File(PATH);
    }

    /**
     * Check if the file exists.
     *
     * @return If the file exists or not.
     */
    public boolean exists() {
        return this.file.exists();
    }

    /**
     * Create the file.
     *
     * @throws IOException If the connection with the file or when managing the file happens an error.
     */
    public void create() throws IOException {
        this.file.createNewFile();
    }

    /**
     * Get a List of the lines.
     *
     * @return List of the lines of the file.
     * @throws IOException If the connection with the file or when managing the file happens an error.
     */
    public List<String> lines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        List<String> lines = reader.lines().toList();

        reader.close();

        return lines;
    }

    /**
     * Get all the content from a file.
     *
     * @return The content of the file.
     * @throws IOException If the connection with the file or when managing the file happens an error.
     */
    public String read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        List<String> lines = reader.lines().toList();

        StringBuilder content = new StringBuilder();

        int i = 0;

        while (i < lines.size() - 1) {
            content.append(lines.get(i)).append('\n');
            i++;
        }
        content.append(lines.get(i));

        reader.close();

        return content.toString();
    }

    /**
     * Write any lines you want in to a file.
     *
     * @param lines The lines you want to write to the file.
     * @throws IOException If the connection with the file or when managing the happens an error.
     */
    public void write(List<String> lines) throws IOException {
        FileWriter writer = new FileWriter(this.file);

        int i=0;

        while (i < (lines.size() - 1)) {
            writer.write(lines.get(i) + "\n");
            i++;
        }
        writer.write(lines.get(i));

        writer.flush();
        writer.close();
    }
}
