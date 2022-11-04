package pvs.labs.lab1;

import pvs.labs.lab1.model.graph.Graph;

import java.util.Arrays;
import java.util.List;

public class UseAlgorithm {

    public static void main(String[] args) {

        List<String> graphStructureSimple = List.of(
                "0 1",
                "0 2",
                "0 3",
                "1 4",
                "1 5",
                "2 6",
                "3 7",
                "6 8",
                "7 9",
                "7 10"
        );

        Graph graph = readGraphLine(graphStructureSimple);
        graph.printAdjMatrix();

        EchoWaveAlgorithm echo = new EchoWaveAlgorithm();
        while (echo.executeEchoWave(graph)) {
            echo.doubleVariableK();
            graph.resetGraph();
        }

        System.out.println(graph);
    }

    public static Graph readGraphLine(List<String> nodes) {
        Graph g = new Graph(nodes.size());

        nodes.forEach(n -> {
            int[] ints = Arrays.stream(n.split("\\s")).mapToInt(Integer::parseInt).toArray();
            g.addEdge(ints[0], ints[1]);
            System.out.println("Edge " + Arrays.toString(ints) + " has been added.");
        });

        return g;
    }

}
