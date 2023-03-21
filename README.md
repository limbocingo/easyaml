# EasYAML
Create a YAML file easier in Java.
**Attention! This mini-library is currently in progress.**

# Examples
Here is how you can to create a YAML file with the library.

```java
import com.github.limbocingo.easyaml.Model;


public class Configuration extends Model {
  private String TOKEN;
}
```

Now you can initialize the `Configuration` class.

```java
new Configuration().get("TOKEN");
```

And you will get the token.
