package github.workflow.kata;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    /**
     * Test
     */
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}