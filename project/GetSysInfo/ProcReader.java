package GetSysInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Utility class for the GetSysInfo classes.
 */
public class ProcReader {
    /**
     * Uses the /proc/ linux directory to access system information.
     * @param path The path to the file you want to search. (e.g "/proc/cpuinfo")
     * @param searchQuery The string you want to search for in the /proc/ file. (e.g "cpuMHz").
     * @return An empty string if nothing was found, or a string of the data. It's <b>up to you to parse it into a
     * double, int, or the relevant type.</b>
     */
    public static String readData(String path, String searchQuery) {
        try {
            // Open the file and get it ready to read
            File proc = new File(path);
            Scanner reader = new Scanner(proc);

            // hasNextLine() is a boolean that will be true while there are still lines to read.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                // The file lines have three pieces of info (usually):
                // [Name of data] [a lot of spaces] : [Data]
                // In this case we couldn't use a single space as the delimiter, since that'd
                // split the line into a ton of empty strings. The following code splits the
                // line by whitespace, regardless of the length of the whitespace.
                String[] split = data.split(":");

                split[0] = split[0].trim();
                if (split.length > 1) {
                    split[1] = split[1].trim();
                }

                // If the data
                if (split[0].equals(searchQuery)) {
                    return split[1];
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            // I dislike throwing errors inside deeply nested functions like this, but
            // it will do for a simple project like this.
            System.out.println("Error occured!.");
            e.printStackTrace();
        }

        // If nothing is found, return an empty string.
        return "";
    }

    public static Double getUptime() {
        try {
            // Open the file and get it ready to read
            File proc = new File("/proc/uptime");
            Scanner reader = new Scanner(proc);

            // hasNextLine() is a boolean that will be true while there are still lines to read.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                // The file lines have three pieces of info (usually):
                // [Name of data] [a lot of spaces] : [Data]
                // In this case we couldn't use a single space as the delimiter, since that'd
                // split the line into a ton of empty strings. The following code splits the
                // line by whitespace, regardless of the length of the whitespace.
                String[] split = data.split(" ");

                return Double.parseDouble(split[0]);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            // I dislike throwing errors inside deeply nested functions like this, but
            // it will do for a simple project like this.
            System.out.println("Error occured!.");
            e.printStackTrace();
        }

        return 0.0;
    }

    /**
     * Uses the /proc/cpuinfo file to get all of the information about all of the cores.
     * @return Two (nested) hashmaps. The parent's key is the core number, and its value is all of the information.
     *         Open /proc/cpuinfo to see all of the value it returns.
     */
    public static Map<Integer, Map<String, String>> getAllCoresInformation() {

        try {
            // Open the file and get it ready to read
            File proc = new File("/proc/cpuinfo");
            Scanner reader = new Scanner(proc);

            Map<Integer, Map<String, String>> finalHashMap = new HashMap<>();
            Map<String, String> newProcMap = new HashMap<>();

            // hasNextLine() is a boolean that will be true while there are still lines to read.
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                // The file lines have three pieces of info (usually):
                // [Name of data] [a lot of spaces] : [Data]
                // In this case we couldn't use a single space as the delimiter, since that'd
                // split the line into a ton of empty strings. The following code splits the
                // line by whitespace, regardless of the length of the whitespace.
                String[] split = data.split(":");

                split[0] = split[0].trim();
                if (split.length > 1) {
                    split[1] = split[1].trim();
                }

                // If the data
                if (split[0].equals("processor")) {
                    // This will be false for the first iteration. We don't want to add an empty map to the hash map.
                    if (newProcMap.containsKey("processor")) {
                        finalHashMap.put(Integer.parseInt(newProcMap.get("processor")), newProcMap);
                    }
                    newProcMap = new HashMap<>(); // Resets the map so that we're not adding duplicate information
                    newProcMap.put(split[0], split[1]);
                } else {
                    // There are empty lines in the file, which cause an index out of bounds exception if we try to
                    // access split[0] without first checking if split[0] actually exists.
                    if (!split[0].isEmpty()) {
                        newProcMap.put(split[0], split[1]); // Add the newly read line's data to the map
                    }
                }
            }

            reader.close();

            return finalHashMap;
        } catch (FileNotFoundException e) {
            // I dislike throwing errors inside deeply nested functions like this, but
            // it will do for a simple project like this.
            System.out.println("Error occured!.");
            e.printStackTrace();
        }

        // If nothing is found, return an empty string.
        return null;
    }
}
