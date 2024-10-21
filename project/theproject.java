public class theproject {
    public static void menu() { // Danny's function
        System.out.println("Select something");

        theproject.handleInput("CPU_Info");
    }

    public static void handleInput(String UserInput) { // Hugh's function
        System.out.println("Here's some CPU info");
    }

    public static void main(String[] args) {
        theproject.menu();
    }
}
