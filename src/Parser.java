import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Parse a YAML file.
 *
 * @version 0.0.1
 */
public class Parser {
    private File file;

    public Parser(String path) {
        this.file = new File(path);
    }

    /**
     * Get all the information of and YAML file.
     * <p>
     * return All the fields of the YAML file in a HashMap.
     * @throws IOException If something happens opening the file.
     */
    public void content() throws IOException {
        List<String> lines = this.file.lines();

        for (String line : lines) {
            StringBuilder key = new StringBuilder();
            StringBuilder value = new StringBuilder();

            boolean inValue = false;
            boolean inString = false;

            for (char character : line.toCharArray()) {
                if (character != ':')
                    key.append(character);
                else {
                    inValue = true;
                    continue;
                }

                

                if (inString)
                    value.append(character);

                if (inValue && character == '"')
                    inString = true;


            }
            System.out.println(key);
            break;
        }
    }
}
