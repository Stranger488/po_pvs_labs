package pvs.labs.lab4;

import pvs.labs.lab4.model.LeeGraph;
import pvs.labs.lab4.model.LeeGraphNode;
import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeeWaveAlgorithm {

    public void executeLeeWave(Graph g, Integer start, Integer end) {
        // init
        System.out.println("Initiator for graph: " + start + "; end: " + end);
        ((LeeGraph) g).setInitiator(start);

        GraphNode[] nodes = g.getGraphNodes();
        int currentDim = 0;
        ((LeeGraphNode) nodes[start]).setMarkVal(currentDim);
        System.out.println("start wave is " + currentDim);

        // mark
        while (((LeeGraphNode) nodes[end]).getMarkVal() == -1) {
            Arrays.stream(nodes).forEach(n -> {
                var curN = (LeeGraphNode) n;
                if (curN.getMarkVal() != -1) {
                    ((LeeGraphNode) n).getSuccessors().forEach(suc -> {
                        LeeGraphNode leeSuc = (LeeGraphNode) suc;
                        if (leeSuc.getMarkVal() == -1) {
                            int val = curN.getMarkVal() + 1;
                            System.out.println("spread " + val + " in " + suc.getValue());
                            leeSuc.setMarkVal(val);
                        }
                    });
                }
            });
            currentDim++;
        }

        // trace
        LeeGraphNode curNode = (LeeGraphNode) nodes[end];
        List<GraphNode> path = new LinkedList<>();
        path.add(curNode);
        while (curNode != nodes[start]) {
            LeeGraphNode finalCurNode = curNode;
            curNode = (LeeGraphNode) curNode.getPredecessors().stream()
                    .filter(n -> ((LeeGraphNode) n).getMarkVal() == finalCurNode.getMarkVal() - 1)
                    .findFirst().orElseThrow(() -> new RuntimeException("path not found"));
            path.add(curNode);
        }

        System.out.print("Path from " + start + " to " + end + " is: ");
        for (int i = path.size() - 1; i > -1; i--) {
            System.out.print(path.get(i).getValue());
            if (i != 0) {
                System.out.print(" -> ");
            }
        }
    }

}
