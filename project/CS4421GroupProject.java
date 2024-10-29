import java.util.Scanner;

public class CS4421GroupProject {

    public static void clearScreen() {
        System.out.print("\033\143");
    }

    public static void menu() throws Exception {
        Scanner input = new Scanner(System.in);
        clearScreen();

        while (true) {
            System.out.println("\tSYSTEM INFORMATION MAIN MENU");
            System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n", "\t\t1) CPU Info", "\t\t2) Disk Info",
                    "\t\t3) PCI Info", "\t\t4) Memory Info", "\t\t5) USB Info", "\t\t6) Exit");

            System.out.print("Enter a number: ");
            String str = input.nextLine();
            clearScreen();
            switch (str) {
                case "1": handleCPUInfo(); break;
                case "2": handleDiskInfo(); break;
                case "3": handlePCIInfo(); break;
                case "4": handleMemoryInfo(); break;
                case "5": handleUSBInfo(); break;
                case "6": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option. Please try again.\n");
            }
        }
    }

    // CPU submenu
    public static void handleCPUInfo() throws InterruptedException {;
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        // note the getModel() method returns a string AND a newline
        System.out.print("CPU Model: " + cpu.getModel());
        System.out.println("CPU Sockets: " + cpu.socketCount());
        System.out.println("Cores Per Socket: " + cpu.coresPerSocket());
        System.out.println("L1 Data Cache Size: " + cpu.l1dCacheSize());
        System.out.println("L1 Instruction Cache Size: " + cpu.l1iCacheSize());
        System.out.println("L2 Cache Size: " + cpu.l2CacheSize());
        System.out.println("L3 Cache Size: " + cpu.l3CacheSize());
        // Sleep for 1 second and display the idle time percentage. This assumes 10Hz so in one second we have 100
        cpu.read(1);

        for (int i = 0; i < cpu.socketCount(); i++) {
            for (int j = 0; j < cpu.coresPerSocket(); j++) {
                System.out.printf("CPU socket %d, Core %d:%n", i+1, j+1);
                System.out.printf("\tIdle time = %d milliseconds%n\tUser time = %d milliseconds%n\tSystem time = %d milliseconds%n",
                        cpu.getIdleTime(j)*10, cpu.getUserTime(j)*10, cpu.getSystemTime(j)*10);
            }
        }
        Display.GraphCPUClockSpeed();

        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to main menu...");
        input.nextLine();
        clearScreen();
    }

    // Disk submenu
    public static void handleDiskInfo() {
        diskInfo disk = new diskInfo();
        disk.read();

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("| DISK\t\t\t| USED BLOCKS\t| AVAILABLE \t| TOTAL BLOCKS\t|");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < disk.diskCount(); i++) { // Iterate through all the disks
            System.out.printf("| %-16s\t| %-10s\t| %-10s\t| %-10s\t|%n", disk.getName(i), disk.getUsed(i),
                    disk.getAvailable(i), disk.getTotal(i));
        }
        System.out.println("-----------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to main menu...");
        input.nextLine();
        clearScreen();
    }

    // PCI submenu
    public static void handlePCIInfo() {
        pciInfo pci = new pciInfo();
        pci.read();

        // lots of nested for loops to go through devices made by skye
        System.out.println("Number of buses: " + pci.busCount());
        for (int i = 0; i < pci.busCount(); i++) { // Iterate through each bus
            System.out.printf("Bus #%d has %d device(s):%n", i + 1, pci.deviceCount(i));

            for (int j = 0; j < pci.deviceCount(i); j++) {
                System.out.printf("  -->\tDevice #%d has %d function(s)%n", (j + 1), pci.functionCount(i, j));

                for (int k = 0; k < pci.functionCount(i, j); k++) {
                    if (pci.functionPresent(i, j, k) > 0) {
                        String vendorID = String.format("%04X", pci.vendorID(i, j, k));
                        String deviceID = String.format("%04X", pci.productID(i, j, k));
                        //ArrayList<String> result = (ArrayList<String>) FetchPciInfo.pciNamer(vendorID, deviceID);
                        //String vendorName = result.get(0);
                        //String deviceName = result.get(1);
                        System.out.printf("\t\t#%d:\tVendor: %s %n\t\t\tDevice: %s %n",
                                (k + 1), vendorID, deviceID);
                    }
                }
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to main menu...");
        input.nextLine();
        clearScreen();
    }

    // System submenu
    public static void handleMemoryInfo() {
        memInfo mem = new memInfo();
        mem.read();

        System.out.printf("There are %d bytes of memory, %d bytes of which are being used%n",
                mem.getTotal(), mem.getUsed());

        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to main menu...");
        input.nextLine();
        clearScreen();
    }

    // USB submenu
    public static void handleUSBInfo() {
        usbInfo usb = new usbInfo();
        usb.read();

        System.out.println("Number of buses: " + usb.busCount());

        for (int i = 0; i < usb.busCount(); i++) { // Iterate through each bus
            System.out.printf("Bus #%d has %d device(s):%n", i + 1, usb.deviceCount(i));
            for (int j = 0; j < usb.deviceCount(i); j++) {
                String vendorID = String.format("%04X", usb.vendorID(i, j));
                String deviceID = String.format("%04X", usb.productID(i, j));
                //ArrayList<String> result = (ArrayList<String>) FetchPciInfo.pciNamer(vendorID, deviceID);
                //String vendorName = result.get(0);
                //String deviceName = result.get(1);
                System.out.printf("\t#%d:\tVendor: %s %n\t\tDevice: %s %n",
                        (j + 1), vendorID, deviceID);
            }
        }

        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to main menu...");
        input.nextLine();
        clearScreen();
    }

    public static void main(String[] args) throws Exception {
        // Load in Mark's library
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();

        CS4421GroupProject.menu();
    }
}