import GetSysInfo.CpuInfo;
import GetSysInfo.MemInfo;
import GetSysInfo.ProcessInfo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// Bence: IDEALLY this should be broken up into multiple classes. Would do this if we had the time
public class CS4421GroupProject {

    public static void clearScreen() { System.out.print("\033\143");}

    public static void menu() throws Exception {
        Scanner input = new Scanner(System.in);

        // Bence: IDEALLY I'd extract this menu logic out into another function, but passing functions as parameters is
        // a massive pain.
        while (true) {
            clearScreen();
            System.out.println("\tSYSTEM INFORMATION MAIN MENU");
            System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n", "\t\t1) CPU Info", "\t\t2) Disk Info",
                    "\t\t3) PCI Info", "\t\t4) Memory Info", "\t\t5) USB Info","\t\t6) Process Info", "\t\t7) Exit");

            System.out.print("Enter a number: ");
            String str = input.nextLine();
            while (!(str.equals("1") | str.equals("2") | str.equals("3") | str.equals("4") | str.equals("5") | str.equals("6") | str.equals("7"))) {
                System.out.println("Invalid option. Please try again.");
                System.out.print("Enter a number: ");
                str = input.nextLine();
            }
            clearScreen();
            switch (str) {
                case "1": handleCPUInfo(); break;
                case "2": handleDiskInfo(); break;
                case "3": handlePCIInfo(); break;
                case "4": handleMemoryInfo(); break;
                case "5": handleUSBInfo(); break;
                case "6": clearScreen(); handleProcessInfo(); break;
                case "7": System.out.println("Exiting...\nThank you!! :)"); return;
            }
        }
    }

    // CPU submenu
    public static void handleCPUInfo() throws InterruptedException {
        clearScreen();
        Scanner input = new Scanner(System.in); // To be used for menu user input collection

        // Read information from the CPU via Mark's library
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Note the getModel() method returns a string AND a newline
        System.out.println("Your computer has been on for " + CpuInfo.getUptime() + " seconds.");
        System.out.println("That's about " + (int) (CpuInfo.getUptime() / 60 )+ " minutes, or " + (int) (CpuInfo.getUptime() / 3600 ) + " hours.\n\n");
        System.out.print("CPU Model: " + cpu.getModel());
        System.out.println("CPU Sockets: " + cpu.socketCount());
        System.out.println("Cores Per Socket: " + cpu.coresPerSocket());
        System.out.println("Threads: " + CpuInfo.getThreadCount()); // From /proc/
        System.out.println("\nL1 Data Cache Size: " + cpu.l1dCacheSize() + " kB");
        System.out.println("L1 Instruction Cache Size: " + cpu.l1iCacheSize() + "kB");
        System.out.println("L2 Cache Size: " + cpu.l2CacheSize() + " kB");
        System.out.println("L3 Cache Size: " + cpu.l3CacheSize() + " kB");


        // THE SUBMENU LOGIC
        System.out.println("\n\nCPU INFO SUBMENU");

        System.out.printf("%s%n%s%n%s%n%s%n", "\t\t1) CPU Clock Speed (Graph)", "\t\t2) Core Idle Time (Graph)",
                "\t\t3) Output All Core Idle Times", "\t\t4) Return to main menu");

        System.out.print("Enter a number: ");
        String str = input.nextLine();
        while (!(str.equals("1") | str.equals("2") | str.equals("3") | str.equals("4"))) {
            System.out.println("Invalid option. Please try again.");
            System.out.print("Enter a number: ");
            str = input.nextLine();
        }
        clearScreen();
        switch (str) {
            case "1":
                System.out.println("Displaying clock speed...");
                Display.graphCPUClockSpeed();
                break;
            case "2":
                System.out.println("Displaying idle times...");
                cpu.read(1);
                Display.graphIdleTime(cpu); break;
            case "3":
                for (int i = 0; i < cpu.socketCount(); i++) {
                    for (int j = 0; j < cpu.coresPerSocket(); j++) {
                        System.out.printf("CPU socket %d, Core %d:%n", i + 1, j + 1);
                        Integer idleTime = cpu.getIdleTime(j) * 10;
                        Integer userTime = cpu.getUserTime(j) * 10;
                        Integer systemTime = cpu.getSystemTime(j) * 10;
                        if (!(idleTime == 0 & userTime == 0 & systemTime == 0)) {
                            double total = idleTime + userTime + systemTime;
                            System.out.printf("\tIdle time\t%-4d milliseconds\t%f%%%n\tUser time\t%-4d milliseconds\t%f%%" +
                                            "%n\tSystem time\t%-4d milliseconds\t%f%%%n", idleTime, (idleTime / total) * 100,
                                    userTime, (userTime/ total) * 100, systemTime, (systemTime / total) * 100);
                        } else {
                            System.out.printf("\tIdle time\t%-4d milliseconds%n\tUser time\t%-4d milliseconds%n\t" +
                                    "System time\t%-4d milliseconds%n", idleTime, userTime, systemTime);
                        }
                    }
                }
                System.out.println("\n\nReturn to menu? Hit enter.");
                input.nextLine();
            case "4": System.out.println("Exiting...\nThank you!! :)"); return;
        }

        // Recursively call the function. fun!
        handleCPUInfo();
    }

    // Disk submenu
    public static void handleDiskInfo() {
        diskInfo disk = new diskInfo();
        disk.read();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("| DISK\t\t\t| USED BLOCKS\t| AVAILABLE \t| TOTAL BLOCKS\t| % USED\t|");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < disk.diskCount(); i++) { // Iterate through all the disks
            double percentUsed = ((double) disk.getUsed(i) / disk.getTotal(i))*100;
            System.out.printf("| %-16s\t| %-10s\t| %-10s\t| %-10s\t| %-6f\t|%n", disk.getName(i), disk.getUsed(i),
                    disk.getAvailable(i), disk.getTotal(i), percentUsed);
        }
        System.out.println("-----------------------------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("\n1) Return to main menu\n2) See additional information about disk\n");
        System.out.print("Enter a number: ");
        String choice = input.nextLine();
        while (!(choice.equals("1") | choice.equals("2"))) {
            System.out.println("Error, please enter only 1 or 2! ");
            System.out.print("Enter a number: ");
            choice = input.nextLine();
        }
        switch (choice) {
            case "1": clearScreen(); return;
            case "2": ExtraInfoMenu.displayDiskInfo();
        }
    }

    // PCI submenu
    public static void handlePCIInfo() throws Exception {
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
                         ArrayList<String> result = (ArrayList<String>) FetchPCIData.pciNamer(vendorID, deviceID);
                         String vendorName = result.get(0);
                        String deviceName = result.get(1);
                        System.out.printf("\t\t#%d: Vendor: %s  %s%n\t\t    Device: %s  %s%n",
                                (k + 1), vendorID, vendorName, deviceID, deviceName);
                    }
                }
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.print("\n1) Return to main menu\n2) See additional information about PCI\n");
        System.out.print("Enter a number: ");
        String choice = input.nextLine();
        while (!(choice.equals("1") | choice.equals("2"))) {
            System.out.println("Error, please enter only 1 or 2! ");
            System.out.print("Enter a number: ");
            choice = input.nextLine();
        }
        switch (choice) {
            case "1": clearScreen(); return;
            case "2": ExtraInfoMenu.displayPCIInfo();
        }
    }

    // System submenu
    public static void handleMemoryInfo() throws InterruptedException {
        clearScreen();
        Scanner input = new Scanner(System.in); // To be used for menu user input collection

        memInfo mem = new memInfo();
        mem.read();

        System.out.printf(
                "There are %f Gigabytes of memory, %f Gigabytes of which are being used%n",
                mem.getTotal() / 1000000.0,
                mem.getUsed() / 1000000.0
        );

        System.out.println("\n\nMEMORY INFO SUBMENU");

        System.out.printf("%s%n%s%n", "\t\t1) Used/Available Memory Share (Graph)", "\t\t2) Return to main menu");

        System.out.print("Enter a number: ");
        String str = input.nextLine();
        while (!(str.equals("1") | str.equals("2"))) {
            System.out.println("Invalid option. Please try again.");
            System.out.print("Enter a number: ");
            str = input.nextLine();
        }
        clearScreen();
        switch (str) {
            case "1":
                System.out.println("Displaying memory share graph...");
                Display.graphMemoryShare();
                break;
            case "2":
                System.out.println("Exiting...\nThank you!! :)");
                return;
        }

        handleMemoryInfo();
    }

    // USB submenu
    public static void handleUSBInfo() throws Exception {
        usbInfo usb = new usbInfo();
        usb.read();

        System.out.println("Number of buses: " + usb.busCount());

        for (int i = 0; i < usb.busCount(); i++) { // Iterate through each bus
            System.out.printf("Bus #%d has %d device(s):%n", i + 1, usb.deviceCount(i));
            for (int j = 0; j < usb.deviceCount(i); j++) {
                String vendorID = String.format("%04X", usb.vendorID(i, j));
                String deviceID = String.format("%04X", usb.productID(i, j));
                ArrayList<String> result = (ArrayList<String>) FetchPCIData.pciNamer(vendorID, deviceID);
                String vendorName = result.get(0);
                String deviceName = result.get(1);
                System.out.printf("\t#%d: Vendor: %s  %s%n\t    Device: %s  %s%n",
                        (j + 1), vendorID, vendorName, deviceID, deviceName);
            }
        }

        Scanner input = new Scanner(System.in);
        System.out.print("\n1) Return to main menu\n2) See additional information about usb\n");
        System.out.print("Enter a number: ");
        String choice = input.nextLine();
        while (!(choice.equals("1") | choice.equals("2"))) {
            System.out.println("Error, please enter only 1 or 2! ");
            System.out.print("Enter a number: ");
            choice = input.nextLine();
        }
        switch (choice) {
            case "1": clearScreen(); return;
            case "2": ExtraInfoMenu.displayUSBInfo();
        }
    }

    public static void handleProcessInfo() throws InterruptedException {
        Scanner input = new Scanner(System.in); // To be used for menu user input collection
        ArrayList<String> processes = ProcessInfo.getAllProcessIDs();

        System.out.println("ALL PROCESSES: \n");
        System.out.println(processes);

        System.out.println("\n\nPROCESS INFO SUBMENU");

        System.out.printf("%s%n%s%n%s%n%s%n", "\t\t1) Get process information by ID", "\t\t2) Get all process names",
                "\t\t3) Graph a process's heap size", "\t\t4) Return to the main menu"
        );

        System.out.print("Enter a number: ");
        String str = input.nextLine();
        while (!(str.equals("1") | str.equals("2") | str.equals("3") | str.equals("4"))) {
            System.out.println("Invalid option. Please try again.");
            System.out.print("Enter a number: ");
            str = input.nextLine();
        }

        switch (str) {
            case "1":
                System.out.println("Enter the process ID:");
                String enteredProcess = input.nextLine();
                while (!processes.contains(enteredProcess)) {
                    System.out.println("Invalid option. Please try again.");
                    System.out.print("Enter a number: ");
                    enteredProcess = input.nextLine();
                }

                clearScreen();
                String[] dataPoint = new String[]{"Name", "Parent ID", "State", "Thread Count", "Virtual Memory Peak", "Virtual Memory Size", "Virtual Memory Heap Size", "Virtual Memory Stack Size"};
                String[] dataValues = new String[8];

                System.out.println("|================================================|");
                String header = String.format("| %-28s | %-15s |", "Datapoint", "Value");
                System.out.println(header);
                System.out.println("|================================================|");
                dataValues[0] = ProcessInfo.getProcessName(enteredProcess);
                dataValues[1] = ProcessInfo.getParentProcesId(enteredProcess);
                dataValues[2] = ProcessInfo.getProcessState(enteredProcess);
                dataValues[3] = ProcessInfo.getProcessThreads(enteredProcess);
                dataValues[4] = ProcessInfo.getProcessVirtualMemoryPeak(enteredProcess);
                dataValues[5] = ProcessInfo.getProcessVirtualMemorySize(enteredProcess);
                dataValues[6] = ProcessInfo.getProcessVirtualMemoryHeapSize(enteredProcess);
                dataValues[7] = ProcessInfo.getProcessVirtualMemoryStackSize(enteredProcess);

                for (int i = 0; i < dataValues.length; i++) {
                    String formattedRow = String.format("| %-28s | %-15s |", dataPoint[i], dataValues[i]);
                    System.out.println(formattedRow);
                }
                System.out.println("|================================================|");

                System.out.print("\n\n");
                break;
            case "2":
                for (String PID : processes) {
                    System.out.println(PID + ": " + ProcessInfo.getProcessName(PID));
                }
                break;
            case "3":
                System.out.println("Enter the process ID:");
                enteredProcess = input.nextLine();
                while (!processes.contains(enteredProcess)) {
                    System.out.println("Invalid option. Please try again.");
                    System.out.print("Enter a number: ");
                    enteredProcess = input.nextLine();
                }
                Display.graphProcessVMSize(enteredProcess);
                break;
            case "4":
                return;
        }

        handleProcessInfo();
    }

    public static void main(String[] args) throws Exception {
        // Load in Mark's library
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();

        CS4421GroupProject.menu();
    }
}