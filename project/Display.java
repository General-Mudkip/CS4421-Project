import GetSysInfo.CpuInfo;
import GetSysInfo.MemInfo;
import GetSysInfo.ProcessInfo;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {
    public static void graphCPUClockSpeed() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Integer coreNumber;
        try {
            System.out.println("Readable CPU Threads: " + (CpuInfo.getThreadCount() - 2) +  "\nEnter the CPU core:");
            coreNumber = input.nextInt();

            System.out.println(CpuInfo.getThreadCount());
            if (coreNumber > CpuInfo.getThreadCount() - 2) {
                System.out.println("INVALID THREAD NUMBER!");
                return;
            }
        } catch (Exception e) {
            System.out.println("INVALID THREAD!");
            return;
        }

        // Create the line graph with all of the necessary information, providing two empty double arrays because
        // we don't have any information yet.
        LineGraph chart = new LineGraph("THREAD " + coreNumber + " CPU MHz", "Time", "MHz", "MHz", new double[2], new double[2]);
        chart.displayGraph();

        // Get the current time in milliseconds since epoch (1st January, 1970)
        double startTime = System.currentTimeMillis();

        // We use an arraylist as the total length of it must be variable.
        ArrayList<ArrayList<Double>> returnValue = new ArrayList<>(2);

        // Add the nested lists
        returnValue.add(new ArrayList<>());
        returnValue.add(new ArrayList<>());

        // While the JFrame is showing (open) on the user's screen. Without this loop, it'd only fetch the data once.
        // Cannot be a `while (true)` since then it'd keep looping even once the JFrame was closed.
        while (chart.frame.isShowing()) {
            // Get the difference between the current time and the start time.
            double elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            // Set the Y axis min and max bounds. This stops the graph view from bouncing around too much.
            // Must be done every loop for some inane reason.
            chart.chart.getStyler().setYAxisMin(0.0);
            chart.chart.getStyler().setYAxisMax(4500.0);

            // Sleeps 100 milliseconds in between each update.
            Thread.sleep(100);

            // Gets the provided core's clock speed.
            double cpuMHz = CpuInfo.getCoreClockSpeed(coreNumber);

            // Add the elapsedTime (X value) and cpuMHz (Y value) to the array list
            returnValue.get(0).add(elapsedTime);
            returnValue.get(1).add(cpuMHz);

            // If the length of returnValue is greater than 100, then remove the first value.
            // This gives us the nice scrolling effect. Without it, the graph would grow more
            // and more illegible.
            if (returnValue.get(0).size() > 100) {
                returnValue.get(0).removeFirst();
                returnValue.get(1).removeFirst();
            }

            // Convert the ArrayList<>'s to double[], as that is what the updateGraph() function requires.
            double[] returnX = returnValue.get(0).stream().mapToDouble(i -> i).toArray();
            double[] returnY = returnValue.get(1).stream().mapToDouble(i -> i).toArray();

            // Pushes the new data to the chart, and updates the graph.
            chart.updateGraph(returnX, returnY);
        }
    }

    public static void graphProcessVMSize(String PID) throws InterruptedException {
        // Create the line graph with all of the necessary information, providing two empty double arrays because
        // we don't have any information yet.
        LineGraph chart = new LineGraph(ProcessInfo.getProcessName(PID) +  " Virtual Memory", "Time", "Virtual Memory Size", "VMS", new double[2], new double[2]);
        chart.displayGraph();

        // Get the current time in milliseconds since epoch (1st January, 1970)
        double startTime = System.currentTimeMillis();

        // We use an arraylist as the total length of it must be variable.
        ArrayList<ArrayList<Double>> returnValue = new ArrayList<>(2);

        // Add the nested lists
        returnValue.add(new ArrayList<>());
        returnValue.add(new ArrayList<>());

        // While the JFrame is showing (open) on the user's screen. Without this loop, it'd only fetch the data once.
        // Cannot be a `while (true)` since then it'd keep looping even once the JFrame was closed.
        while (chart.frame.isShowing()) {
            // Get the difference between the current time and the start time. Th
            double elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

            // Sleeps 100 milliseconds in between each update.
            Thread.sleep(1);

            // Gets the provided core's clock speed.
            double cpuMHz = Double.parseDouble(ProcessInfo.getProcessVirtualMemoryHeapSize(PID).replace(" kB", ""));

            // Add the elapsedTime (X value) and cpuMHz (Y value) to the array list
            returnValue.get(0).add(elapsedTime);
            returnValue.get(1).add(cpuMHz);

            // Convert the ArrayList<>'s to double[], as that is what the updateGraph() function requires.
            double[] returnX = returnValue.get(0).stream().mapToDouble(i -> i).toArray();
            double[] returnY = returnValue.get(1).stream().mapToDouble(i -> i).toArray();

            // Pushes the new data to the chart, and updates the graph.
            chart.updateGraph(returnX, returnY);
        }
    }



    public static void graphIdleTime(cpuInfo cpu) throws InterruptedException {
        PieGraph graph = new PieGraph("Idle Times");

        graph.addSeries("Idle Time", 0);
        graph.addSeries("User Time", 0);
        graph.addSeries("System Time", 0);

        graph.displayGraph();
        while (graph.frame.isShowing()) {
            cpu.read(0);
            double idleTime = cpu.getIdleTime(0)*10;
            double userTime = cpu.getUserTime(0)*10;
            double systemTime =cpu.getSystemTime(0)*10;

            graph.updateGraph("Idle Time", (int) idleTime);
            graph.updateGraph("User Time", (int) userTime);
            graph.updateGraph("System Time", (int) systemTime);

            Thread.sleep(100);
        }
    }

    public static void graphMemoryShare() throws InterruptedException {
        PieGraph graph = new PieGraph("Memory Share");

        graph.addSeries("Available Memory", 0);
        graph.addSeries("Used Memory", 0);

        graph.displayGraph();
        while (graph.frame.isShowing()) {
            graph.updateGraph("Available Memory", MemInfo.getMemAvailable());
            graph.updateGraph("Used Memory", MemInfo.getMemTotal() - MemInfo.getMemAvailable());

            Thread.sleep(100);
        }
    }
}
