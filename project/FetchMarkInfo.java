import java.util.ArrayList;
import java.util.List;

public class FetchMarkInfo {

    public static void cpu() {

        System.out.println("\nMARK CPU INFO:");
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);
        // note the getModel() method returns a string AND a newline for absolutely no reason (thanks Mark)
        System.out.print("CPU Model: " + cpu.getModel());
        System.out.println("CPU Sockets: " + cpu.socketCount());
        System.out.println("Cores Per Socket: " + cpu.coresPerSocket());
        System.out.println("L1 Data Cache Size: " + cpu.l1dCacheSize());
        System.out.println("L1 Instruction Cache Size: " + cpu.l1iCacheSize());
        System.out.println("L2 Cache Size: " + cpu.l2CacheSize());
        System.out.println("L3 Cache Size: " + cpu.l3CacheSize());
        // Sleep for 1 second and display the idle time percentage. This assumes 10Hz so in one second we have 100
        cpu.read(1);
        // core 0 for first core, core 1 for second core, etc.
        System.out.println("Core 1 % Idle Time: "+cpu.getIdleTime(0)+"%");
        System.out.println("Core 2 % Idle Time: "+cpu.getIdleTime(1)+"%");
        System.out.println("Core 3 % Idle Time: "+cpu.getIdleTime(2)+"%");
        System.out.println("Core 4 % Idle Time: "+cpu.getIdleTime(3)+"%");
        System.out.println("Core 1 User Time: "+cpu.getUserTime(0)*10+" milliseconds");
        System.out.println("Core 2 User Time: "+cpu.getUserTime(1)*10+" milliseconds");
        System.out.println("Core 3 User Time: "+cpu.getUserTime(2)*10+" milliseconds");
        System.out.println("Core 4 User Time: "+cpu.getUserTime(3)*10+" milliseconds");
        System.out.println("Core 1 System Time: "+cpu.getSystemTime(0)*10+" milliseconds");
        System.out.println("Core 2 System Time: "+cpu.getSystemTime(1)*10+" milliseconds");
        System.out.println("Core 3 System Time: "+cpu.getSystemTime(2)*10+" milliseconds");
        System.out.println("Core 4 System Time: "+cpu.getSystemTime(3)*10+" milliseconds");
    }

    public static void pci() throws Exception {

        System.out.println("\nMARK PCI INFO:");
        pciInfo pci = new pciInfo();
        pci.read();

        // lots of nested for loops to go through devices made by skye
        System.out.println("Number of buses: "+ pci.busCount());

        for (int i = 0; i < pci.busCount(); i++) { // Iterate through each bus
            System.out.printf("Bus #%d has %d device(s):%n", i+1, pci.deviceCount(i));

            for (int j = 0; j < pci.deviceCount(i); j++) {
                System.out.printf("\tDevice #%d has %d function(s)%n", (j+1), pci.functionCount(i, j));

                for (int k = 0; k < pci.functionCount(i, j); k++) {
                    if (pci.functionPresent (i, j, k) > 0) {
                        String vendorID = String.format("%04X", pci.vendorID(i,j,k));
                        String deviceID = String.format("%04X", pci.productID(i,j,k));
                        ArrayList<String> result = (ArrayList<String>) FetchPciInfo.pciNamer(vendorID, deviceID);
                        String vendorName = result.get(0);
                        String deviceName = result.get(1);
                        System.out.printf("\t\t#%d:\tVendor: %s %s%n\t\t\tDevice: %s %s%n",
                                (k+1), vendorID, vendorName, deviceID, deviceName);
                    }
                }
            }
        }
    }

    public static void usb() throws Exception {

        System.out.println("\nMARK USB INFO:");
        usbInfo usb = new usbInfo();
        usb.read();

        System.out.println("Number of buses: "+ usb.busCount());

        for (int i = 0; i < usb.busCount(); i++) { // Iterate through each bus
            System.out.printf("Bus #%d has %d device(s):%n", i+1, usb.deviceCount(i));
            for (int j = 0; j < usb.deviceCount(i); j++) {
                String vendorID = String.format("%04X", usb.vendorID(i,j));
                String deviceID = String.format("%04X", usb.productID(i,j));
                ArrayList<String> result = (ArrayList<String>) FetchPciInfo.pciNamer(vendorID, deviceID);
                String vendorName = result.get(0);
                String deviceName = result.get(1);
                System.out.printf("\t#%d:\tVendor: %s %s%n\t\tDevice: %s %s%n",
                        (j+1), vendorID, vendorName, deviceID, deviceName);
            }
        }
    }
}
