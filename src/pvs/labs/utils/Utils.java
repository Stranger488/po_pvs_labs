package pvs.labs.utils;

import pvs.labs.model.Graph;

import java.util.Arrays;
import java.util.List;

public final class Utils {

    public static Graph readGraphLine(Graph graph, List<String> nodes) {
        nodes.forEach(n -> {
            int[] ints = Arrays.stream(n.split("\\s")).mapToInt(Integer::parseInt).toArray();
            graph.addEdge(ints[0], ints[1]);
            System.out.println("Edge " + Arrays.toString(ints) + " has been added.");
        });

        return graph;
    }

}
