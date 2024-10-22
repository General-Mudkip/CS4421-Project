import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CS4421GroupProject {

    public static void menu() throws Exception { // Danny's function
        Scanner input = new Scanner(System.in);

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

            // Based on the user's choice, call the corresponding submenu.
            switch (number) {
                case 1:
                    handleInput("CPU_Info");
                    break;
                case 2:
                    handleInput("Disk_Info");
                    break;
                case 3:
                    handleInput("PCI_Info");
                    break;
                case 4:
                    handleInput("System_Info");
                    break;
                case 5:
                    handleInput("USB_Info");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return; // Exit the program
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // function to handle the submenu logic
    public static void handleInput(String UserInput) {
        Scanner input = new Scanner(System.in);

        switch (UserInput) {
            case "CPU_Info":
                System.out.println("\nCPU Information Menu:");
                System.out.println("1) View CPU Model");
                System.out.println("2) View CPU Speed");
                System.out.println("3) Back to Main Menu");
                break;
            case "Disk_Info":
                System.out.println("\nDisk Information Menu:");
                System.out.println("1) View Disk Capacity");
                System.out.println("2) View Disk Usage");
                System.out.println("3) Back to Main Menu");
                break;
            case "PCI_Info":
                System.out.println("\nPCI Information Menu:");
                System.out.println("1) View PCI Devices");
                System.out.println("2) View PCI Bus Info");
                System.out.println("3) Back to Main Menu");
                break;
            case "System_Info":
                System.out.println("\nSystem Information Menu:");
                System.out.println("1) View OS Version");
                System.out.println("2) View System Uptime");
                System.out.println("3) Back to Main Menu");
                break;
            case "USB_Info":
                System.out.println("\nUSB Information Menu:");
                System.out.println("1) View USB Devices");
                System.out.println("2) View USB Controller Info");
                System.out.println("3) Back to Main Menu");
                break;
            default:
                System.out.println("Invalid submenu option.");
        }

        System.out.print("Enter a number: ");
        int submenuChoice = input.nextInt();

        System.out.print(UserInput + submenuChoice);

        // Process submenu options
        if (submenuChoice == 3) {
            return; // Go back to the main menu
        } else {
            System.out.println("Displaying details for option " + submenuChoice);
            // You can add more specific functionality here for each option
        
        }

    public static List<String> nameVendorDevice(String vendorID, String deviceID) throws Exception {
        /*
         * This function takes two inputs for vendor ID and device ID, then returns a
         * string list containing the actual data,
         * in the form of ["Vendor Name", "Device Name"]
         * This is achieved by comparing the results to a csv database of >50,000 PCI
         * devices.
         * Created by Skye Fitzpatrick
         */

        // Read in data from the CSV file using Scanner
        Scanner readCsv = new Scanner(new File(
                "C:\\Users\\skyef\\Documents\\CS4421 - Problem Solving With Computers\\PCI-Lookup-finalfinal.csv"));

        List<List> pciData = new ArrayList(); // Initialise a 2-dimensional array for database rows and columns
        /*
         * We need to create a 2-dimensional array because of the way the csv file is
         * formatted.
         * It gives data in the form [vendorID+deviceID, vendor name, device name],
         * copied from https://pcilookup.com.
         * To analyse and get data from this, we need to use nested while loops as
         * below.
         */

        while (readCsv.hasNextLine()) { // while there is still rows to be read...
            // rowData list is within the while loop because it needs to be reset for every
            // row
            List rowData = new ArrayList(); // create an empty list for each string in data row
            Scanner separateRowData = new Scanner(readCsv.nextLine()); // read data from the next row
            separateRowData.useDelimiter(","); // separate it using the comma delimiter
            while (separateRowData.hasNext()) {
                rowData.add(separateRowData.next()); // add it to the data list
            }
            pciData.add(rowData); // add to 2 dimensional array
        }

        // initialise variables for each dataset
        ArrayList codesList = new ArrayList<String>();
        ArrayList vendorsList = new ArrayList<String>();
        ArrayList devicesList = new ArrayList<String>();

        // for loop increments for each item in pciData 2D array
        for (int i = 0; i < pciData.size(); i++) {
            codesList.add(pciData.get(i).get(0)); // first column of row i is a code
            vendorsList.add(pciData.get(i).get(1)); // second column of row i is a vendor name
            devicesList.add(pciData.get(i).get(2)); // third column of row i is a device name
        }

        String codeNeeded = vendorID.concat(deviceID); // combine the vendor and device ID into one string to form the
                                                       // "code"
        // using a try/catch block in case the code provided is not part of the database
        // list and runs an error
        try {
            int index = codesList.indexOf(codeNeeded); // find index of codeNeeded against original CSV
            ArrayList result = new ArrayList<String>();
            result.add(vendorsList.get(index)); // use same index to find necessary vendor name
            result.add(devicesList.get(index)); // use same index to fine
            return result;
        } catch (Exception e) {
            // create and return an "error list" if the code doesn't run as expected
            List<String> error = new ArrayList<String>();
            error.add("Unknown Vendor!");
            error.add("Unknown Device!");
            return error;
        }
    }

    public static void main(String[] args) throws Exception {
        TheProject.menu();
    }
}
