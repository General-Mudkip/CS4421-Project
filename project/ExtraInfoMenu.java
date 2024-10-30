import java.util.Scanner;

public class ExtraInfoMenu {

    // CPU information
    public static void displayCPUInfo() {
        CS4421GroupProject.clearScreen();
        System.out.println("\nCPU Information:");
        System.out.println("A CPU socket: is the physical interface on a motherboard " +
                "where a CPU is installed, allowing it to connect with other components in the computer.");
        System.out.println("Cores per socket: refers to the number of processing " +
                "cores in a single socket on a motherboard.\n");
        System.out.println("An L1 data cache: is a small, fast memory cache located directly" +
                " on the CPU, used to store frequently accessed data for quick retrieval by the processor.");
        System.out.println("An L1 instruction cache: is a small, fast memory cache on the CPU that" +
                " stores the most frequently used instructions, enabling quicker access for the processor.");
        System.out.println("An L2 cache: is a larger, slightly slower memory cache on the CPU, used to " +
                "store data and instructions not found in the L1 cache, improving processing efficiency.");
        System.out.println("An L3 cache: is a larger, shared memory cache on the CPU that holds data and" +
                " instructions for multiple cores, helping to reduce latency and improve performance across those cores.\n");
        System.out.println("CPU idle time: is the duration when the CPU is not actively processing tasks," +
                " indicating that it is waiting for instructions or resources to execute.");
        System.out.println("CPU user time: is the amount of time the CPU spends executing user-level applications" +
                " and processes, excluding system-level tasks performed by the operating system.");
        System.out.println("CPU system time: is the duration the CPU spends executing system-level processes" +
                " and kernel operations, which are managed by the operating system.");
        System.out.println("CPU clock speed: is the frequency at which a processor executes instructions," +
                " typically measured in gigahertz (GHz), indicating how many cycles per second it can perform.");
        returnToMenuPrompt();
    }

    // disk information
    public static void displayDiskInfo() {
        CS4421GroupProject.clearScreen();
        System.out.println("\nDisk Information:");
        System.out.println("A disk is a storage device used to store and retrieve digital data");
        System.out.println("Hard Disk Drives (HDDs): Mechanical storage devices that use spinning platters" +
                " and read/write heads, offering high capacity at affordable prices.");
        System.out.println("Solid State Drives (SSDs): Flash-based storage with no moving parts," +
                " providing faster read/write speeds and durability compared to HDDs.");
        System.out.println("Hybrid Drives (SSHDs): Combine HDD and SSD technology, using flash " +
                "memory to store frequently accessed data for faster performance.");

        returnToMenuPrompt();
    }

    // PCI information
    public static void displayPCIInfo() {
        CS4421GroupProject.clearScreen();
        System.out.println("\nPCI Information:");
        System.out.println("A PCI bus: connects peripheral devices to a" +
                " computer's motherboard for data transfer.");
        System.out.println("A PCI device: is a hardware component that connects to the PCI bus of a computer" +
                " to add functionality, like graphics cards or network cards.");
        System.out.println("Slot Types: PCI slots vary in size and lane configuration," +
                " with larger slots (x16) typically used for high-performance devices like graphics cards.");
        System.out.println("Backwards Compatibility: PCIe slots are generally backward-compatible," +
                " allowing older cards to work in newer slots, though at the older standard’s speed.");
        returnToMenuPrompt();
    }

    // System information
    public static void displayMemoryInfo() {
        CS4421GroupProject.clearScreen();
        System.out.println("\nMemory Information:");
        System.out.println("Memory is hardware that stores data and instructions in a computer.");
        System.out.println("The types of memory include RAM, ROM, cache, and flash memory.");
        System.out.println("Memory can be categorized into volatile (like RAM) and non-volatile (like SSDs)," +
                " with each type serving different functions in data storage and processing.");
        System.out.println("Virtual Memory: A portion of storage drive space used to simulate additional RAM" +
                " when physical RAM is fully utilized.");
        returnToMenuPrompt();
    }

    // USB information
    public static void displayUSBInfo() {
        CS4421GroupProject.clearScreen();
        System.out.println("\nUSB Information:");
        System.out.println("USB (Universal Serial Bus) is a standard interface used" +
                " for connecting peripherals to computers and transferring data or providing power.");
        System.out.println("Types and Speeds: USB versions include USB 1.0 (1.5–12 Mbps)," +
                " USB 2.0 (480 Mbps), USB 3.0 (up to 5 Gbps), USB 3.1 (10 Gbps), and USB4 (up to 40 Gbps).");
        System.out.println("Connectors: Common types are USB-A, USB-B, USB-C, and Micro-USB," +
                " each suited for different devices and ports.");
        System.out.println("Power Delivery: USB can charge devices, with USB-C supporting" +
                " up to 100W for faster charging.");
        returnToMenuPrompt();
    }

    // method to prompt the user to return to the main menu
    public static void returnToMenuPrompt() {
        System.out.println("\nPress Enter to return to the main menu...");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        CS4421GroupProject.clearScreen();
    }
}
