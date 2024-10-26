import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CS4421GroupProject {

    public static void menu() throws Exception {
        Scanner input = new Scanner(System.in);



        while (true) {
            System.out.println("Main Menu");
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
        System.out.printf("%-25s %s%n", "1) View CPU Model", "2) View Cores per Socket");
        System.out.printf("%-25s %s%n", "3) View Socket Count", "4) View L1D Cache Size");
        System.out.printf("%-25s %s%n", "5) View L1I Cache Size", "6) View L2 Cache Size");
        System.out.printf("%-25s %s%n", "7) View L3 Cache Size", "8) View User Time");
        System.out.printf("%-25s %s%n", "9) View Idle Time", "10) View System Time");
        System.out.println("11) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying CPU Model...");
                break;
            case 2:
                System.out.println("Displaying Cores per Socket...");
                break;
            case 3:
                System.out.println("Displaying Socket Count...");
                break;
            case 4:
                System.out.println("Displaying L1D Cache Size...");
                break;
            case 5:
                System.out.println("Displaying L1I Cache Size...");
                break;
            case 6:
                System.out.println("Displaying L2 Cache Size...");
                break;
            case 7:
                System.out.println("Displaying L3 Cache Size...");
                break;
            case 8:
                System.out.println("Displaying User Time...");
                break;
            case 9:
                System.out.println("Displaying Idle Time...");
                break;
            case 10:
                System.out.println("Displaying System Time...");
                break;
            case 11:
                return;
            default:
                System.out.println("Invalid choice. Returning to CPU Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // Disk submenu
    public static void handleDiskInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nDisk Information Menu:");
        System.out.printf("%-25s %s%n", "1) View Disk Count", "2) View Disk Model");
        System.out.printf("%-25s %s%n", "3) View Disk Capacity", "4) View Disk Usage");
        System.out.println("5) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying Disk Count...");
                break;
            case 2:
                System.out.println("Displaying Disk Model...");
                break;
            case 3:
                System.out.println("Displaying Disk Capacity...");
                break;
            case 4:
                System.out.println("Displaying Disk Usage...");
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Returning to Disk Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // PCI submenu
    public static void handlePCIInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nPCI Information Menu:");
        System.out.printf("%-25s %s%n", "1) View PCI Bus Count", "2) View Device Count");
        System.out.printf("%-25s %s%n", "3) View Functions Present", "4) View Vendor ID");
        System.out.printf("%-25s %s%n", "5) View Product ID", "6) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying PCI Bus Count...");
                break;
            case 2:
                System.out.println("Displaying Device Count...");
                break;
            case 3:
                System.out.println("Displaying Functions Present...");
                break;
            case 4:
                System.out.println("Displaying Vendor ID...");
                break;
            case 5:
                System.out.println("Displaying Product ID...");
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. Returning to PCI Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // System submenu
    public static void handleSystemInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nSystem Information Menu:");
        System.out.printf("%-25s %s%n", "1) View OS Version", "2) View System Uptime");
        System.out.println("3) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying OS Version...");
                break;
            case 2:
                System.out.println("Displaying System Uptime...");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Returning to System Info Menu...");
        }

        waitForUserInputToContinue();
    }

    // USB submenu
    public static void handleUSBInfo() {
        Scanner input = new Scanner(System.in);

        System.out.println("\nUSB Information Menu:");
        System.out.printf("%-25s %s%n", "1) View USB Bus Count", "2) View USB Device Count");
        System.out.printf("%-25s %s%n", "3) View USB Vendor ID", "4) View USB Product ID");
        System.out.println("5) Back to Main Menu");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Displaying USB Bus Count...");
                break;
            case 2:
                System.out.println("Displaying USB Device Count...");
                break;
            case 3:
                System.out.println("Displaying USB Vendor ID...");
                break;
            case 4:
                System.out.println("Displaying USB Product ID...");
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Returning to USB Info Menu...");
        }

        waitForUserInputToContinue();
    }

    public static void waitForUserInputToContinue() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPress Enter to return to Main Menu...");
        input.nextLine();
    }

    public static List<String> pciNamer(String vendorID, String deviceID) throws Exception {
        /*
         * This function takes two inputs for vendor ID and device ID, then returns a
         * string list
         * containing the actual data, in the form of ["Vendor Name", "Device Name"]
         * This is achieved by comparing the results to a csv database of >50,000 PCI
         * devices.
         * Created by Skye Fitzpatrick
         */

        /*
         * TO CALL pciNamer
         * numbers to be replaced with output from Mark's pciInfo code
         * String vendorName = TheProject.pciNamer("8086", "0180").get(0);
         * String deviceName = TheProject.pciNamer("8086", "0180").get(1);
         * System.out.printf("Vendor: %s%nDevice: %s", vendorName, deviceName);
         */

        // Read in data from the CSV file using Scanner
        Scanner readCsv = new Scanner(new File("./PCI-Lookup.csv"));

        /*
         * We need to create a 2-dimensional array because of the way the csv file is
         * formatted.
         * The CSV contains data in the form [vendorID+deviceID, vendor name, device
         * name], copied from https://pcilookup.com.
         * To analyse and get data from this, we need to use nested while loops as
         * below.
         */
        ArrayList<ArrayList<String>> pciData = new ArrayList<ArrayList<String>>();

        while (readCsv.hasNextLine()) { // while there are still rows to be read...
            ArrayList<String> rowData = new ArrayList<>(); // create an empty list for each string in data row
            Scanner separateRowData = new Scanner(readCsv.nextLine()); // read data from the next row
            separateRowData.useDelimiter(","); // separate it using the comma delimiter
            while (separateRowData.hasNext()) {
                rowData.add(separateRowData.next()); // add it to the data list
            }
            pciData.add(rowData); // add to 2 dimensional array
        }

        // initialise variables for each dataset
        ArrayList<String> codesList = new ArrayList<>();
        ArrayList<String> vendorsList = new ArrayList<>();
        ArrayList<String> devicesList = new ArrayList<>();

        // for loop increments for each item in pciData 2D array
        for (ArrayList<String> rowData : pciData) {
            codesList.add(rowData.get(0)); // first column of row is a code
            vendorsList.add(rowData.get(1)); // second column of row is a vendor name
            devicesList.add(rowData.get(2)); // third column of row is a device name
        }

        String codeNeeded = vendorID.concat(deviceID); // combine the vendor and device ID into one string to form the
        // "code" using a try/catch block in case the code provided is not part of the database list and runs an error
        try {
            int index = codesList.indexOf(codeNeeded); // find index of codeNeeded against original CSV
            ArrayList<String> result = new ArrayList<>();
            result.add(vendorsList.get(index)); // use same index to find necessary vendor name
            result.add(devicesList.get(index)); // use same index to fine
            return result;
        } catch (Exception e) {
            // create and return an "error list" if the code doesn't run as expected
            List<String> error = new ArrayList<>();
            error.add("Unknown Vendor!");
            error.add("Unknown Device!");
            return error;
        }
    }

    public static void main(String[] args) throws Exception {

        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        FetchMarkInfo.cpu();
        FetchMarkInfo.pci();
        FetchMarkInfo.usb();
        FetchMarkInfo.disk();
        FetchMarkInfo.memory();

        System.out.print("\n\n\n");
        CS4421GroupProject.menu();

    }
}

    /* Bence's problem xx
    public static void getCPUOverTime() throws InterruptedException {
        // cpuInfo cpu = new cpuInfo();
        double startTime = System.currentTimeMillis();

        Graph graph = new Graph("Random Walk", "Pos", "Time", "Walk", new double[2], new double[2]);
        graph.displayGraph();

        ArrayList<ArrayList<Double>> returnValue = new ArrayList<>(2);
        returnValue.add(new ArrayList<>());
        returnValue.add(new ArrayList<>());

        int pos = 0;
        int max = 0;
        Random rand = new Random();
        int step;

        for (int counter = 0; counter < 100000; counter++) {
            double currentTime = (System.currentTimeMillis() - startTime) / 1000;

            boolean bool = rand.nextBoolean();
            if (bool)
                step = 1;
            else
                step = -1;
            pos += step;
            max = Math.max(pos, max);

            returnValue.get(0).add(currentTime);
            returnValue.get(1).add((double) pos);

            if (returnValue.get(0).size() > 1000) {
                returnValue.get(0).removeFirst();
                returnValue.get(1).removeFirst();
            }

            Thread.sleep(1);

            double[] returnX = returnValue.get(0).stream().mapToDouble(i -> i).toArray();
            double[] returnY = returnValue.get(1).stream().mapToDouble(i -> i).toArray();

            graph.updateGraph(returnX, returnY);
        }
    }
    */