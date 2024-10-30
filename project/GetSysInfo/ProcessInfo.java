package GetSysInfo;

import java.io.File;
import java.util.ArrayList;

import static java.util.Arrays.stream;

public class ProcessInfo {
    public static String getProcessName(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "Name");
    }

    public static String getParentProcesId(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "PPid");
    }

    public static String getProcessState(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "State");
    }

    public static String getProcessThreads(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "Threads");
    }

    public static String getProcessVirtualMemoryPeak(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "VmPeak");
    }

    public static String getProcessVirtualMemorySize(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "VmSize");
    }

    public static String getProcessVirtualMemoryHeapSize(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "VmData");
    }

    public static String getProcessVirtualMemoryStackSize(String processID) {
        return ProcReader.readData("/proc/"+processID+"/status", "VmStk");
    }

    public static ArrayList<String> getAllProcessIDs() {
        File file = new File("/proc/");
        String[] names = file.list();

        ArrayList<String> processes = new ArrayList<>(); // Used to store all the process IDs

        // Search through all the files under /proc/
        for(String name : names) {
            File possibleDirectory = new File("/proc/" + name);

            if (possibleDirectory.isDirectory()) { // Self-explanatory
                try {
                    Integer.parseInt(name); // All processes are integers. If this fails, it's not a process.
                    processes.add(name);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }

        return processes;
    }
}
