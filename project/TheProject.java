public class TheProject {
    public static void menu() { // Danny's function
        System.out.println("Select something");

        TheProject.handleInput("CPU_Info");
    }

    public static void handleInput(String UserInput) { // Hugh's function
        System.out.println("Here's some CPU info");
    }

    public static void main(String[] args) {
        TheProject.menu();
    }
}
