import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Graph {
    // These two are made public, so that they can be situationally modified. (e.g setting the y axis minimum value)
    XYChart chart;
    SwingWrapper<XYChart> sw;
    private String seriesName;

    /**
     * A constructor for creating a graph via XCharts.
     * @param chartTitle The title of the chart, displayed at the top.
     * @param xAxisTitle The X axis title. Displayed below the X axis.
     * @param yAxisTitle The Y axis title. Displayed to the left of the Y axis.
     * @param seriesName The name of the line series. Can be anything.
     * @param xData The array of X data. <b>Needs to be the same length as yData.</b>
     * @param yData The array of Y data. <b>Needs to be the same length as xData.</b>
     */
    public Graph(String chartTitle, String xAxisTitle, String yAxisTitle, String seriesName, double[] xData,
            double[] yData) {
        this.seriesName = seriesName;
        this.chart = QuickChart.getChart(chartTitle, xAxisTitle, yAxisTitle, seriesName, xData, yData);

        this.chart.getStyler().setCursorEnabled(true); // Allows users to sample different points on the chart
        this.sw = new SwingWrapper<>(this.chart); // Used for displaying and updating the chart
    }

    public void displayGraph() {
        this.sw.displayChart();
    }

    // Updates the graph when provided with new data. Can be used for live graphs.
    public void updateGraph(double[] newXData, double[] newYData) {
        // Use invokeLater as Swing runs asynchronously from the main program.
        javax.swing.SwingUtilities.invokeLater(() -> {
            this.chart.updateXYSeries(this.seriesName, newXData, newYData, null);
            this.sw.repaintChart(); // Actually pushes these changes to the chart on the user's screen
        });
    }
}
