import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;

/*
 * This function takes two inputs for vendor ID and device ID, then returns a string list
 * containing the actual data, in the form of ["Vendor Name", "Device Name"]
 * This is achieved by comparing the results to a csv database of >50,000 PCI devices.
 * Created by Skye Fitzpatrick
 */


public class FetchPCIData {

    public static ArrayList<String> pciNamer(String vendorID, String deviceID) throws Exception {
        // Initialise error strings, these will be modified unless data is not in the csv
        String vendorName = "Vendor unknown";
        String deviceName = "Device unknown";

        // Read in data from the CSV file using Scanner
        Scanner readCsv = new Scanner(new File("/home/skye-fitzpatrick/Desktop/PCI-Lookup.csv"));

        /* The CSV contains data in the form [vendorID, vendor name, deviceID, device name],
         * and was copied from https://pcilookup.com.
         * To analyse and get data from this, we need to use nested while loops as below.
         */
        while (readCsv.hasNextLine()) { // while there are still rows to be read...
            Scanner readRow = new Scanner(readCsv.nextLine()); // read data from the next row
            readRow.useDelimiter(","); // separate it using the comma delimiter
            while (readRow.hasNext()) {
                String vID = readRow.next();
                if (!Objects.equals(vID, vendorID)) {
                    readRow.next();
                    readRow.next();
                    readRow.next();
                    continue;
                }
                vendorName = readRow.next();
                String dID = readRow.next();
                if (!Objects.equals(dID, deviceID)) {
                    readRow.next();
                    continue;
                }
                deviceName = readRow.next();
                break;
            }
        }
        ArrayList<String> result = new ArrayList<>();
        result.add(vendorName);
        result.add(deviceName);
        return result;
    }
}