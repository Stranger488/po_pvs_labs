package pvs.labs.lab3;

import pvs.labs.lab2.FinnWaveAlgorithm;
import pvs.labs.lab2.model.FinnGraph;
import pvs.labs.lab3.model.PhaseGraph;
import pvs.labs.model.Graph;
import pvs.labs.utils.Utils;

import java.util.List;

public class Lab3Application {

    public static void main(String[] args) {
        List<String> graphStructureSimple = List.of(
                "0 1",
                "0 2",
                "1 3",
                "2 3",
                "3 0"
        );

        Graph graph = Utils.readGraphLine(new PhaseGraph(4, 3), graphStructureSimple);
        graph.initGraph();
        graph.printAdjMatrix();

        var finn = new PhaseWaveAlgorithm();
        finn.executePhaseWave(graph, 0);
    }

}
