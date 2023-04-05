# EasYAML
Create a YAML file easier in Java.

# Examples
How to create a YAML file with the library.

```java
import com.github.limbocingo.easyaml.Model;


public class Configuration extends Model {
  private String TOKEN;
  // You can add a default value if you want.
  // Making something like this.
  private String DATABASE="test-db";
}
```

Now you can initialize the `Configuration` class.

```java
new Configuration().get("TOKEN");
```

And you will get the token.
