package pvs.labs.lab2;

import pvs.labs.lab2.model.FinnGraph;
import pvs.labs.lab2.model.FinnGraphNode;
import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinnWaveAlgorithm {

    public void executeEchoFinn(Graph g, Integer start) {
        List<Set<Integer>> Inc = new ArrayList<>();
        List<Set<Integer>> NInc = new ArrayList<>();
        List<List<Map.Entry<Integer, Boolean>>> rec = new ArrayList<>();
        for (var i = 0; i < g.getGraphSize(); i++) {
            Inc.add(Set.of(g.getGraphNodes()[i].getValue()));
            NInc.add(Set.of());

            List<Map.Entry<Integer, Boolean>> r = new ArrayList<>();
            for (var e : g.getPredecessors(i)) {
                r.add(new AbstractMap.SimpleEntry<>(e, Boolean.FALSE));
            }
            rec.add(r);
        }

        int graphSize = g.getGraphSize();
        GraphNode[] nodes = g.getGraphNodes();

        int initiator = start;
        System.out.println("Initiator for graph: " + initiator);

        boolean isEnd = false;
        while (!isEnd) {
            for (int j = 0; j < graphSize; j++) {
                FinnGraphNode finnNode = (FinnGraphNode) nodes[j];

                System.out.println("Node " + j + " will perform the protocol");

                // Ветка для инициатора, должна отработать один раз
                if (j == initiator && !finnNode.isVisited()) {
                    ((FinnGraph) g).sentMessagesToNeighbors(j);
                }

                for (var node : finnNode.getNodesFrom()) {
                    Set<Integer> IncNew = Stream.concat(Inc.get(j).stream(), Inc.get(node).stream())
                            .collect(Collectors.toSet());
                    Set<Integer> NIncNew = Stream.concat(NInc.get(j).stream(), NInc.get(node).stream())
                            .collect(Collectors.toSet());

                    rec.get(j).stream()
                            .filter(el -> node.equals(el.getKey()))
                            .findFirst()
                            .map(el -> el.setValue(Boolean.TRUE)); // received

                    if (rec.get(j).stream()
                            .allMatch(el -> Boolean.TRUE.equals(el.getValue()))) {
                        NIncNew.add(j);
                    }

                    if (!IncNew.equals(Inc.get(j)) || !NIncNew.equals(NInc.get(j))) {
                        ((FinnGraph) g).sentMessagesToNeighbors(j);
                    }

                    Inc.set(j, IncNew);
                    NInc.set(j, NIncNew);
                }
                finnNode.getNodesFrom().clear();

                if (Inc.get(j).equals(NInc.get(j))) {
                    isEnd = true;
                    break;
                }

                System.out.println(Arrays.toString(Inc.toArray()));
                System.out.println(Arrays.toString(NInc.toArray()));
            }
        }

        System.out.println(Arrays.toString(Inc.toArray()));
        System.out.println(Arrays.toString(NInc.toArray()));
        System.out.println("Decision is made!");
    }

}
