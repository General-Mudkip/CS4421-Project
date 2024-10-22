/*
 *  Example class containing methods to read and display CPU, PCI and USB information
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

import java.util.ArrayList;
import java.util.Random;

public class template
{
    public static void showPCI()
    {
        pciInfo pci = new pciInfo();
        pci.read();

        System.out.println("\nThis machine has "+
            pci.busCount()+" PCI buses ");

        // Iterate through each bus
        for (int i = 0; i < pci.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                pci.deviceCount(i)+" devices");

            // Iterate for up to 32 devices.  Not every device slot may be populated
            // so ensure at least one function before printing device information
            for (int j = 0; j < 32; j++) {
                if (pci.functionCount (i, j) > 0) {
                    System.out.println("Bus "+i+" device "+j+" has "+
                        pci.functionCount(i, j)+" functions");

                    // Iterate through up to 8 functions per device.
                    for (int k = 0; k < 8; k++) {
                        if (pci.functionPresent (i, j, k) > 0) {
                            System.out.println("Bus "+i+" device "+j+" function "+k+
                                " has vendor "+String.format("0x%04X", pci.vendorID(i,j,k))+
                                " and product "+String.format("0x%04X", pci.productID(i,j,k)));
                        }
                    }
                }
            }
        }
    }

    public static void showUSB()
    {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis machine has "+
            usb.busCount()+" USB buses ");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                usb.deviceCount(i)+" devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+
                    " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+
                    " and product "+String.format("0x%04X", usb.productID(i,j)));
            }
        }
    }

    public static void showCPU()
    {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        // Show CPU model, CPU sockets and cores per socket
        System.out.println("CPU " + cpu.getModel() + " has "+
            cpu.socketCount() + " sockets each with "+
            cpu.coresPerSocket() + " cores");

        // Show sizes of L1,L2 and L3 cache
        System.out.println("l1d="+cpu.l1dCacheSize()+
            ", l1i="+cpu.l1iCacheSize()+
            ", l2="+cpu.l2CacheSize()+
            ", l3="+cpu.l3CacheSize());

        // Sleep for 1 second and display the idle time percentage for
        // core 1.  This assumes 10Hz so in one second we have 100
        cpu.read(1);
        System.out.println("core 1 idle="+cpu.getIdleTime(1)+"%");
    }

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

    public static void main(String[] args) throws InterruptedException {
        getCPUOverTime();
    }
}

