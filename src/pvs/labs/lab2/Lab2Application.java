package pvs.labs.lab2;

import pvs.labs.lab2.model.FinnGraph;
import pvs.labs.model.Graph;
import pvs.labs.utils.Utils;

import java.util.List;

public class Lab2Application {

    public static void main(String[] args) {
        List<String> graphStructureSimple = List.of(
                "0 1",
                "1 0",
                "0 2",
                "0 3",
                "1 4",
                "1 5",
                "2 6",
                "3 7",
                "6 8",
                "7 9"
        );

        Graph graph = Utils.readGraphLine(new FinnGraph(graphStructureSimple.size()), graphStructureSimple);
        graph.printAdjMatrix();

        var finn = new FinnWaveAlgorithm();
        finn.executeEchoFinn(graph, 0);
    }



}
