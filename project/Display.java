import GetSysInfo.CpuInfo;
import java.util.ArrayList;

public class Display {
    public static void GraphCPUClockSpeed() throws InterruptedException {
        LineGraph chart = new LineGraph("CPU MHz", "Time", "MHz", "MHz", new double[2], new double[2]);
        chart.displayGraph();

        double startTime = System.currentTimeMillis();
        ArrayList<ArrayList<Double>> returnValue = new ArrayList<>(2);
        returnValue.add(new ArrayList<>());
        returnValue.add(new ArrayList<>());
        double previousValue = 0.0;

        while (true) {
            double currentTime = (System.currentTimeMillis() - startTime) / 1000;
            chart.chart.getStyler().setYAxisMin(0.0);
            chart.chart.getStyler().setYAxisMax(4500.0);
            Thread.sleep(100);
            double cpuMHz = CpuInfo.getCoreClockSpeed(0);
            returnValue.get(0).add(currentTime);
            returnValue.get(1).add(cpuMHz);
            if (returnValue.get(0).size() > 100) {
                returnValue.get(0).removeFirst();
                returnValue.get(1).removeFirst();
            }
            double[] returnX = returnValue.get(0).stream().mapToDouble(i -> i).toArray();
            double[] returnY = returnValue.get(1).stream().mapToDouble(i -> i).toArray();
            previousValue = cpuMHz;
            chart.updateGraph(returnX, returnY);
        }

    }
}
