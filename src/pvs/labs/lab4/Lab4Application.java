package pvs.labs.lab4;

import pvs.labs.lab3.ParallelPhaseWaveAlgorithm;
import pvs.labs.lab3.model.PhaseGraph;
import pvs.labs.lab4.model.LiGraph;
import pvs.labs.model.Graph;
import pvs.labs.utils.Utils;

import java.util.List;
import java.util.Map;

public class Lab4Application {

    static class GraphInput {
        List<String> graph;
        int size;
        int D;

        public GraphInput(List<String> graph, int size, int D) {
            this.graph = graph;
            this.size = size;
            this.D = D;
        }
    }

    static Map<String, GraphInput> graphs = Map.of(
        "fullGraph", new GraphInput(
                List.of(
                        "0 1",
                        "0 2",
                        "0 3",
                        "1 4",
                        "1 5",
                        "2 6",
                        "3 7",
                        "6 8",
                        "7 9",
                        "4 0",
                        "9 3"
                ),
            10, 4
            ),
        "simpleGraph", new GraphInput(
                List.of(
                        "0 1",
                        "0 2",
                        "1 3",
                        "2 3",
                        "3 0"
                ),
                4, 3
            )
    );

    public static void main(String[] args) {
        GraphInput graphInput = graphs.get(args[0]);
        Graph graph = Utils.readGraphLine(
                new LiGraph(graphInput.size, graphInput.D),
                graphInput.graph
        );
        graph.initGraph();
        graph.printAdjMatrix();

        var finn = new ParallelPhaseWaveAlgorithm();
        finn.executePhaseWave(graph, 0);
    }

}
