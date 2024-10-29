import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import javax.swing.*;

public class PieGraph {
    // All of these variables are public so that they can be situationally edited.
    PieChart graph;
    SwingWrapper<PieChart> sw;
    JFrame frame;

    public PieGraph(String chartTitle) {
        this.graph = new PieChartBuilder().width(800).height(600).title(chartTitle).build();
        this.sw = new SwingWrapper<>(this.graph);
    }

    /**
     * @param seriesName The name of the value, e.g Idle Time, Active Time, etc.
     * @param value The value itself. Feel free to change it to Double if necessary.
     */
    public void addSeries(String seriesName, Integer value) {
        this.graph.addSeries(seriesName, value);
    }

    /**
     * Actually displays the graph. Call this once all series have been added.
     */
    public void displayGraph() {
        this.frame = this.sw.displayChart();
        javax.swing.SwingUtilities.invokeLater(
                ()->this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        );
    }

    /**
     * Updates the graph when provided with new data. Can be used for live graphs.
     * @param seriesName The name of the value. <strong>Ensure that it matches the original name.</strong> e.g Idle Time, Active Time, etc.
     * @param value The value itself.
     */
    public void updateGraph(String seriesName, Integer value) {
        // Use invokeLater as Swing runs asynchronously from the main program.
        javax.swing.SwingUtilities.invokeLater(() -> {
            this.graph.updatePieSeries(seriesName, value);
            this.sw.repaintChart(); // Actually pushes these changes to the chart on the user's screen
        });
    }
}
