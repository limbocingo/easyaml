import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



/**
 * Parse a YAML file.
 *
 * @version 0.0.1
 */
public class Parser {
    public File file;

    public Parser(File file) {
        this.file = file;
    }

    /**
     * Get all the information of and YAML file.
     * 
     * @return All the fields of the YAML file in a HashMap. 
     * @throws IOException If something happens opening the file.
     * @throws ParseException
     */
    public void content() throws IOException, ParseException {
        String[] crud = this.file.read().split("\n");
        
        List<List<Object>> result = new ArrayList<>();
        
        Boolean inValue;
        Boolean inString; 

        char[] characters;

        for (String line : crud) {
            inValue = false;
            inString = false;

            characters = line.toCharArray();
            
            result.add(new ArrayList<>());

            for (int i = 0; i < characters.length; i++) {
                if (inValue && !inString && characters[i] == ':')
                    throw new ParseException("You can't add more than one colon, you can add it if is in string only.", i);
                
                if (!inValue && characters[i] == ' ')
                    continue;
                
                if (i == 0 && characters[i] == ':')
                    throw new ParseException("You don't gived any key.", i);
                
                if (characters[i] == ':' && !inValue) {
                    inValue = true;
                    continue;
                }

                if (characters[i] == '"' && inString)
                    inString = false;
                
                if (characters[i] == '"' && !inString)
                    inString = true;

                
            }
        }
    }
}
