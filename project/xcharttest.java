import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Random;

import com.sun.management.OperatingSystemMXBean;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class xcharttest {

    public static void main(String[] args) throws Exception {

        double[][] initdata = getCPUUtilisation();

// Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", initdata[0], initdata[1]);
        chart.getStyler().setCursorEnabled(true);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setXAxisMax(10.0);
// Show it
        new SwingWrapper(chart).displayChart();
    }

    private static double[][] getCPUUtilisation() throws InterruptedException {

        double startTime = System.currentTimeMillis();

        double[][] tempVal = new double[2][2];
        ArrayList<ArrayList<Double>> returnValue = new ArrayList<>(2);
        returnValue.add(new ArrayList<>());
        returnValue.add(new ArrayList<>());

        final XYChart chart = QuickChart.getChart("Free Memory Size", "Time Elapsed", "Free Memory (Bits)", "Line", tempVal[0], tempVal[1]);

        // Show it
        final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();

        int pos = 0;
        int max = 0;
        Random rand = new Random();
        int step;

        // ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        for (int counter = 0; counter < 100000; counter++) {
            // chart.getStyler().setYAxisMin(0.0);
            double currentTime = (System.currentTimeMillis() - startTime) / 1000;

            // for (int i = counter + 1; i < returnValue.length; i++) {
            //     returnValue[0][i] = currentTime;
            // }


            boolean bool = rand.nextBoolean();
            if (bool)
                step = 1;
            else
                step = -1;
            pos += step;
            max = Math.max(pos, max);

            Thread.sleep(10);

            // double CPUTime = (bean.getCpuLoad()* 100);
            // long CPUTime = bean.getFreeMemorySize();
            // double CPUTime = bean.getCommittedVirtualMemorySize();

            double CPUTime = pos;

            System.out.print(currentTime);
            System.out.print(" , ");
            System.out.print(CPUTime);
            System.out.printf("%n");
            System.out.printf("%n%d : %d%n", returnValue.get(0).size() , returnValue.get(1).size() );

            returnValue.get(0).add(currentTime);
            returnValue.get(1).add((double) CPUTime);

            if (returnValue.get(0).size() > 1000) {
                returnValue.get(0).removeFirst();
                returnValue.get(1).removeFirst();
            }

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    chart.updateXYSeries("Line", returnValue.get(0), returnValue.get(1), null);
                    sw.repaintChart();
                }
            });
        }

        double[] returnX = returnValue.get(0).stream().mapToDouble(i -> i).toArray();
        double[] returnY = returnValue.get(1).stream().mapToDouble(i -> i).toArray();

        return new double[][]{returnX, returnY};

    }
}
