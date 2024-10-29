import GetSysInfo.CpuInfo;
import java.util.ArrayList;

public class Display {
    public static void GraphCPUClockSpeed() throws InterruptedException {
        // Create the line graph with all of the necessary information, providing two empty double arrays because
        // we don't have any information yet.
        LineGraph chart = new LineGraph("CPU MHz", "Time", "MHz", "MHz", new double[2], new double[2]);
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

            // Set the Y axis min and max bounds. This stops the graph view from bouncing around too much.
            // Must be done every loop for some inane reason.
            chart.chart.getStyler().setYAxisMin(0.0);
            chart.chart.getStyler().setYAxisMax(4500.0);

            // Sleeps 100 milliseconds in between each update.
            Thread.sleep(100);

            // Gets the provided core's clock speed.
            double cpuMHz = CpuInfo.getCoreClockSpeed(0);

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
}
