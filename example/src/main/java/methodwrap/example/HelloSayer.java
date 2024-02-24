package methodwrap.example;

import methodwrap.LogAndRethrow;

public class HelloSayer {
    @LogAndRethrow
    public String sayHello() {
        throw new RuntimeException("huhu");
    }
}
