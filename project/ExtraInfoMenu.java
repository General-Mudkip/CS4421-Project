import java.util.Scanner;

public class ExtraInfoMenu {
    public static void menu() {
        Scanner input = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("Extra Information Main Menu");
            System.out.printf("%-20s %s%n", "1) CPU Info", "2) Disk Info");
            System.out.printf("%-20s %s%n", "3) PCI Info", "4) System Info");
            System.out.printf("%-20s %s%n", "5) USB Info", "6) Exit");

            System.out.print("Enter a number: ");
            int number = input.nextInt();

            switch (number) {
                case 1:
                    displayCPUInfo(input);
                    break;
                case 2:
                    displayDiskInfo(input);
                    break;
                case 3:
                    displayPCIInfo(input);
                    break;
                case 4:
                    displayMemoryInfo(input);
                    break;
                case 5:
                    displayUSBInfo(input);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    input.close();  // Close scanner before exiting
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // CPU information
    public static void displayCPUInfo(Scanner input) {
        clearScreen();
        System.out.println("\nCPU Information:");
        System.out.println("A CPU socket: is the physical interface on a motherboard " +
                "where a CPU is installed, allowing it to connect with other components in the computer.");
        System.out.println("Cores per socket: refers to the number of processing " +
                "cores in a single socket on a motherboard.");
        System.out.println("An L1 data cache: is a small, fast memory cache located directly" +
                " on the CPU, used to store frequently accessed data for quick retrieval by the processor.");
        System.out.println("An L1 instruction cache: is a small, fast memory cache on the CPU that" +
                " stores the most frequently used instructions, enabling quicker access for the processor.");
        System.out.println("An L2 cache: is a larger, slightly slower memory cache on the CPU, used to " +
                "store data and instructions not found in the L1 cache, improving processing efficiency.");
        System.out.println("An L3 cache: is a larger, shared memory cache on the CPU that holds data and" +
                " instructions for multiple cores, helping to reduce latency and improve performance across those cores.");
        System.out.println("CPU idle time: is the duration when the CPU is not actively processing tasks," +
                " indicating that it is waiting for instructions or resources to execute.");
        System.out.println("CPU user time: is the amount of time the CPU spends executing user-level applications" +
                " and processes, excluding system-level tasks performed by the operating system.");
        System.out.println("CPU system time: is the duration the CPU spends executing system-level processes" +
                " and kernel operations, which are managed by the operating system.");
        System.out.println("CPU clock speed: is the frequency at which a processor executes instructions," +
                " typically measured in gigahertz (GHz), indicating how many cycles per second it can perform.");
        returnToMenuPrompt(input);
    }

    // disk information
    public static void displayDiskInfo(Scanner input) {
        clearScreen();
        System.out.println("\nDisk Information:");
        System.out.println("A disk is a storage device used to store and retrieve digital data");
        System.out.println("The different disk types include Hard Disk Drives (HDDs), Solid State Drives (SSDs)," +
                " Hybrid Drives (SSHDs), and External Hard Drives.");


        returnToMenuPrompt(input);
    }

    // PCI information
    public static void displayPCIInfo(Scanner input) {
        clearScreen();
        System.out.println("\nPCI Information:");
        System.out.println("A PCI bus: connects peripheral devices to a" +
                " computer's motherboard for data transfer.");
        System.out.println("A PCI device: is a hardware component that connects to the PCI bus of a computer" +
                " to add functionality, like graphics cards or network cards.");


        returnToMenuPrompt(input);
    }

    // Memory information
    public static void displayMemoryInfo(Scanner input) {
        clearScreen();
        System.out.println("\nMemory Information:");
        System.out.println("Memory is hardware that stores data and instructions in a computer.");
        System.out.println("The types of memory include RAM, ROM, cache, and flash memory.");
        System.out.println("Memory can be categorized into volatile (like RAM) and non-volatile (like SSDs)," +
                " with each type serving different functions in data storage and processing.");


        returnToMenuPrompt(input);
    }

    // USB information
    public static void displayUSBInfo(Scanner input) {
        clearScreen();
        System.out.println("\nUSB Information:");
        System.out.println("USB (Universal Serial Bus) is a standard interface used" +
                " for connecting peripherals to computers and transferring data or providing power.");


        returnToMenuPrompt(input);
    }

    // method to prompt the user to return to the main menu
    public static void returnToMenuPrompt(Scanner input) {
        clearScreen();
        System.out.print("Press Enter to return to the main menu...");
        System.out.println("");
        input.nextLine();
    }

    public static void main(String[] args) {
        menu();
    }
}
