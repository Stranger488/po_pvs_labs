package pvs.labs.lab3.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PhaseGraph extends Graph {

    private final int D;

    private ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Message>> lst;

    public PhaseGraph(int size, int D) {
        super(size);
        this.D = D;
    }

    @Override
    public GraphNode createGraphNode(int val) {
        PhaseGraphNode graphNode = new PhaseGraphNode(val, D);
        if (lst == null) {
            lst = new ConcurrentHashMap<>();
        }
        lst.put(val, new ConcurrentLinkedQueue<>());
        graphNode.setLst(lst);
        return graphNode;
    }

    @Override
    public void setPredSubGraphNode(int val, List<GraphNode> predecessors, List<GraphNode> successors) {
        ((PhaseGraphNode) graphNodes[val]).setPredecessors(predecessors);
        ((PhaseGraphNode) graphNodes[val]).setSuccessors(successors);

        predecessors.forEach(p ->
            ((PhaseGraphNode) graphNodes[val]).getRecCount().put(p.getValue(), 0)
        );
    }

    @Override
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        this.edges++;
    }

}
