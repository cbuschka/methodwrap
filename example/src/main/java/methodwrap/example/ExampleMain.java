package methodwrap.example;

public class ExampleMain {
    public static void main(String[] args) {
        System.err.println("ExampleMain.main");
        new HelloSayer().sayHello();
        System.err.println("ExampleMain.main end");
    }
}
