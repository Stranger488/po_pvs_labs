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
        List<List<Integer>> Inc = new ArrayList<>();
        List<List<Integer>> NInc = new ArrayList<>();
        List<List<Map.Entry<Integer, Boolean>>> rec = new ArrayList<>();
        for (var i = 0; i < g.getGraphSize(); i++) {
            Inc.add(List.of(g.getGraphNodes()[i].getValue()));
            NInc.add(List.of());


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
                    List<Integer> IncNew = Stream.concat(
                                    Inc.get(j).stream(),
                                    Inc.get(node).stream()
                            ).distinct().collect(Collectors.toList());

                    List<Integer> NIncNew = Stream.concat(
                                    NInc.get(j).stream(),
                                    NInc.get(node).stream()
                            ).distinct().collect(Collectors.toList());

                    rec.get(j).stream()
                            .filter(el -> node.equals(el.getKey()))
                            .findFirst()
                            .map(el -> el.setValue(Boolean.TRUE)); // received

                    if (rec.get(j).stream()
                            .allMatch(el -> Boolean.TRUE.equals(el.getValue()))) {
                        NIncNew.add(j);
                    }

                    List<Integer> IncNewSorted = getSortedList(IncNew);
                    List<Integer> NIncNewSorted = getSortedList(NIncNew);
                    List<Integer> IncJSorted = getSortedList(Inc.get(j));
                    List<Integer> NIncJSorted = getSortedList(NInc.get(j));
                    if (!IncNewSorted.equals(IncJSorted) || !NIncNewSorted.equals(NIncJSorted)) {
                        ((FinnGraph) g).sentMessagesToNeighbors(j);
                    }

                    Inc.set(j, IncNew);
                    NInc.set(j, NIncNew);
                }
                finnNode.getNodesFrom().clear();

                List<Integer> IncSorted = getSortedList(Inc.get(initiator));
                List<Integer> NIncSorted = getSortedList(NInc.get(initiator));
                if (IncSorted.equals(NIncSorted)) {
                    isEnd = true;
                    break;
                }

                System.out.println(Arrays.toString(Inc.toArray()));
                System.out.println(Arrays.toString(NInc.toArray()));
            }
        }

        System.out.println(Arrays.toString(Inc.toArray()));
        System.out.println(Arrays.toString(NInc.toArray()));
    }

    private List<Integer> getSortedList(List<Integer> IncNew) {
        return IncNew.stream().sorted().collect(Collectors.toList());
    }

}
