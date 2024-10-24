import java.util.Scanner;

public class TheProject {

    public static void menu() throws Exception {
        Scanner input = new Scanner(System.in);

        // Test code for pciNamer
        // String vendorName = FetchPciInfo.pciNamer("8086", "0180").get(0);
        // String deviceName = FetchPciInfo.pciNamer("8086", "0180").get(1);
        // System.out.printf("Vendor: %s%nDevice: %s%n", vendorName, deviceName);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("1) CPU Info");
            System.out.println("2) Disk Info");
            System.out.println("3) PCI Info");
            System.out.println("4) System Info");
            System.out.println("5) USB Info");
            System.out.println("6) Exit");
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
                    return; // exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            // main menu or exit
            System.out.print("Do you want to return to the main menu? (y/n): ");
            char returnToMenu = input.next().charAt(0);

            if (returnToMenu == 'n' || returnToMenu == 'N') {
                System.out.println("Exiting...");
                return; // exit the program
            }
        }
    }

    // CPU submenu
    public static void handleCPUInfo() {
        Scanner input = new Scanner(System.in);

        switch (UserInput) {
            case "CPU_Info":
                System.out.println("\nCPU Information Menu:");
                System.out.println("1) View Cores per Socket");
                System.out.println("2) View Socket Count");
                System.out.println("3) View Model");
                System.out.println("4) View L1d Cache Size");
                System.out.println("5) View L1i Cache Size");
                System.out.println("6) View L2 Cache Size");
                System.out.println("7) View L3 Cache Size");
                System.out.println("8) View User Time");
                System.out.println("9) View Idle Time");
                System.out.println("10) View System Time");
                System.out.println("11) Back to Main Menu");
                break;
            case "Disk_Info":
                System.out.println("\nDisk Information Menu:");
                System.out.println("1) View Disk Capacity");
                System.out.println("2) View Disk Model");
                System.out.println("3) View Disk Count");
                System.out.println("4) View Disk Usage");
                System.out.println("5) Back to Main Menu");
                break;
            case "PCI_Info":
                System.out.println("\nPCI Information Menu:");
                System.out.println("1) View PCI Bus Count");
                System.out.println("2) View PCI Device Count");
                System.out.println("3) View Functions Present");
                System.out.println("4) View Vendor ID");
                System.out.println("5) Product ID");
                System.out.println("6) Back to Main Menu");

                break;
            case "System_Info":
                System.out.println("\nSystem Information Menu:");
                System.out.println("1) View OS Version");
                System.out.println("2) View System Uptime");
                System.out.println("3) Back to Main Menu");
                break;
            case "USB_Info":
                System.out.println("\nUSB Information Menu:");
                System.out.println("1) View USB Device Count");
                System.out.println("2) View USB Bus Count");
                System.out.println("3) View Vendor ID");
                System.out.println("4) View Product ID");
                System.out.println("5) Back to Main Menu");
                break;
            default:
                System.out.println("Invalid submenu option.");
        }

        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying CPU Model...");
                // add functionality here
                break;
            case 2:
                System.out.println("Displaying Cores per Socket...");
                // add functionality here
                break;
            case 3:
                System.out.println("Displaying Socket Count...");
                // add functionality here
                break;
            case 4:
                System.out.println("Displaying L1D Cache Size...");
                // add functionality here
                break;
            case 5:
                System.out.println("Displaying L1I Cache Size...");
                // add functionality here
                break;
            case 6:
                System.out.println("Displaying L2 Cache Size...");
                // add functionality here
                break;
            case 7:
                System.out.println("Displaying L3 Cache Size...");
                // add functionality here
                break;
            case 8:
                System.out.println("Displaying User Time...");
                // add functionality here
                break;
            case 9:
                System.out.println("Displaying Idle Time...");
                // add functionality here
                break;
            case 10:
                System.out.println("Displaying System Time...");
                // add functionality here
                break;
            case 11:
                return; // back to main menu
            default:
                System.out.println("Invalid choice. Returning to CPU Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // Disk submenu
    public static void handleDiskInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nDisk Information Menu:");
        System.out.println("1) View Disk Count");
        System.out.println("2) View Disk Model");
        System.out.println("3) View Disk Capacity");
        System.out.println("4) View Disk Usage");
        System.out.println("5) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying Disk Count...");
                // add functionality here
                break;
            case 2:
                System.out.println("Displaying Disk Model...");
                // add functionality here
                break;
            case 3:
                System.out.println("Displaying Disk Capacity...");
                // add functionality here
                break;
            case 4:
                System.out.println("Displaying Disk Usage...");
                // add functionality here
                break;
            case 5:
                return; // back to main menu
            default:
                System.out.println("Invalid choice. Returning to Disk Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // PCI submenu
    public static void handlePCIInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPCI Information Menu:");
        System.out.println("1) View PCI Bus Count");
        System.out.println("2) View Device Count");
        System.out.println("3) View Functions Present");
        System.out.println("4) View Vendor ID");
        System.out.println("5) View Product ID");
        System.out.println("6) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying PCI Bus Count...");
                // add functionality here
                break;
            case 2:
                System.out.println("Displaying Device Count...");
                // add functionality here
                break;
            case 3:
                System.out.println("Displaying Functions Present...");
                // add functionality here
                break;
            case 4:
                System.out.println("Displaying Vendor ID...");
                // add functionality here
                break;
            case 5:
                System.out.println("Displaying Product ID...");
                // add functionality here
                break;
            case 6:
                return; // back to main menu
            default:
                System.out.println("Invalid choice. Returning to PCI Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // System submenu
    public static void handleSystemInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nSystem Information Menu:");
        System.out.println("1) View OS Version");
        System.out.println("2) View System Uptime");
        System.out.println("3) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying OS Version...");
                // add functionality here
                break;
            case 2:
                System.out.println("Displaying System Uptime...");
                // add functionality here
                break;
            case 3:
                return; // back to main menu
            default:
                System.out.println("Invalid choice. Returning to System Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // USB submenu
    public static void handleUSBInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nUSB Information Menu:");
        System.out.println("1) View USB Bus Count");
        System.out.println("2) View USB Device Count");
        System.out.println("3) View USB Vendor ID");
        System.out.println("4) View USB Product ID");
        System.out.println("5) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying USB Bus Count...");
                // add functionality here
                break;
            case 2:
                System.out.println("Displaying USB Device Count...");
                // add functionality here
                break;
            case 3:
                System.out.println("Displaying USB Vendor ID...");
                // add functionality here
                break;
            case 4:
                System.out.println("Displaying USB Product ID...");
                // add functionality here
                break;
            case 5:
                return; // back to main menu
            default:
                System.out.println("Invalid choice. Returning to USB Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // user input for main menu
    public static void waitForUserInputToContinue() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to Main Menu...");
        input.nextLine();
    }

    public static void main(String[] args) throws Exception {
        TheProject.menu();
    }
