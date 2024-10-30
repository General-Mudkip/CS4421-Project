import java.util.Scanner;

public class OldMenuDesign {
/*This was our original menu design. We used multiple switch statements
to create a main menu and submenus for each title. CPU info, Disk info etc.
*/
    public static void menu() throws Exception {
        Scanner input = new Scanner(System.in);


        while (true) {
            System.out.println("Extra Information Main Menu");
            System.out.printf("%-20s %s%n", "1) CPU Info", "2) Disk Info");
            System.out.printf("%-20s %s%n", "3) PCI Info", "4) System Info");
            System.out.printf("%-20s %s%n", "5) USB Info", "6) Exit");

            System.out.print("Enter a number: ");
            int number = input.nextInt();

            switch (number) {
                case 1:
                    handleCPUInfo();
                    break;
                case 2:
                    handleDiskInfo();
                    break;
                case 3:
                    handlePCIInfo();
                    break;
                case 4:
                    handleSystemInfo();
                    break;
                case 5:
                    handleUSBInfo();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.print("Do you want to return to the main menu? (y/n): ");
            char returnToMenu = input.next().charAt(0);

            if (returnToMenu == 'n' || returnToMenu == 'N') {
                System.out.println("Exiting...");
                return;
            }
        }
    }

    // CPU submenu
    public static void handleCPUInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nCPU Information Menu:");

        waitForUserInputToContinue();
    }

    // Disk submenu
    public static void handleDiskInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nDisk Information Menu:");


        waitForUserInputToContinue();
    }

    // PCI submenu
    public static void handlePCIInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPCI Information Menu:");


        waitForUserInputToContinue();
    }

    // System submenu
    public static void handleSystemInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nSystem Information Menu:");


        waitForUserInputToContinue();
    }

    // USB submenu
    public static void handleUSBInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nUSB Information Menu:");


        waitForUserInputToContinue();
    }

    public static void waitForUserInputToContinue() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to Main Menu...");
        input.nextLine();
    }

    public static void main(String[] args) throws Exception {
        OldMenuDesign.menu();
    }
}
