package pvs.labs.lab3.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;
import pvs.labs.model.Message;

import java.util.List;
import java.util.concurrent.*;

public class PhaseGraph extends Graph {

    private final int D;

    public int getInitiator() {
        return initiator;
    }

    private int initiator;

    public void setInitiator(int initiator) {
        this.initiator = initiator;
    }

    private ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Message>> lst;

    public PhaseGraph(int size, int D) {
        super(size);
        this.D = D;
    }

    @Override
    public GraphNode createGraphNode(int val) {
        PhaseGraphNode graphNode = new PhaseGraphNode(this, val, D);
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
