import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Graph {
    XYChart chart;
    SwingWrapper<XYChart> sw;
    String seriesName;

    // Constructor for creating the graph
    public Graph(String chartTitle, String xAxisTitle, String yAxisTitle, String seriesName, double[] xData, double[] yData) {
        this.seriesName = seriesName;
        this.chart = QuickChart.getChart(chartTitle, xAxisTitle, yAxisTitle, seriesName, xData, yData);

        this.chart.getStyler().setCursorEnabled(true); // Allows users to sample different points on the chart
        this.sw = new SwingWrapper<>(this.chart); // Used for displaying and updating the chart
    }

    // Should the graph be displayed when it's instantiated?
    // Shows the Graph on the user's display
    public void displayGraph() {
        this.sw.displayChart();
    }

    // Updates the graph when provided with new data. Can be used for live graphs.
    public void updateGraph(double[] newXData, double[] newYData) {
        System.out.println(newXData);
        javax.swing.SwingUtilities.invokeLater(() -> {
            this.chart.updateXYSeries(this.seriesName, newXData, newYData, null);
            this.sw.repaintChart(); // Actually pushes these changes to the chart on the user's screen
        });
    }
}
