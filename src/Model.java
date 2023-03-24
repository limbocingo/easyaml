/*
    Imports
 */
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *      Create a YAML file easier.
 * </p>
 *
 * <p>Example:</p>
 * <pre>
     *      public class ExampleConfigModel extends YAMLFile {
 *          {@code String TOKEN;}
 *          {@code String URL;}
 *      }
 *
 *      {@code // You get the value of the token that is in the}
 *      {@code // configuration file.}
 *      new ExampleConfigModel().get("TOKEN"));
 * </pre>
 */
public class Model {
    /*
        Resources variables
     */
    public File file;
    private Field[] fields;

    public Model() {
        try {
            this.file = new File(
                    "src" + java.io.File.separatorChar + java.io.File.separatorChar + "resources" + java.io.File.separatorChar
                            + this.getClass().getName().toLowerCase() + ".yml"
            );

            this.fields = this.getClass().getDeclaredFields();

            if (this.file.exists()) {
                this.writeFile();
                this.checkFile();
            }
            else
                this.file.create();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Get a value of any field you want from
     * your configuration file.
     *
     * @param key The key of the field you want to get the value.
     * @throws IOException If something went wrong opening the file.
     * @return The value from the field you selected.
     */
    public Object get(String key) throws IOException {
        return this.readFile().get(key);
    }

    /**
     * Read all the fields that have the configuration file.
     *
     * @return Map<String, Object> Key and value of each config parameter in the file.
     */
    private Map<String, Object> readFile() throws IOException {
        return (Map<String, Object>) new Yaml().load(this.file.read());
    }

    /**
     * Write all the fields of the model in the config file.
     *
     * @throws IOException If something went wrong opening the file.
     * @throws IllegalAccessException The field isn't accesible.
     */
    private void writeFile() throws IllegalAccessException, IOException {
        List<String> YAMLFields = new ArrayList<>();

        for (Field field : this.fields) {
            field.setAccessible(true);
            YAMLFields.add(field.getName() + ": " + field.get(this));
        }

        this.file.write(YAMLFields);
    }

    /**
     * Check if all the fields of the file
     * matches with the YAMLFileModel.
     *
     * @throws IOException If something went wrong opening the file.
     */
    private void checkFile() throws IOException {
        for (Field field : this.fields) {
            field.setAccessible(true);

            if (this.get(field.getName()) == null)
                continue;

            if (!this.get(field.getName()).getClass().equals(field.getType()))
                throw new IllegalStateException("The type field '" + field.getName() + "' has the wrong type.");
        }
    }
}
