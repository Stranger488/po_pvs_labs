package pvs.labs.lab4.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.List;

public class LeeGraph extends Graph {

    private final int D;

    public int getInitiator() {
        return initiator;
    }

    private int initiator;

    public void setInitiator(int initiator) {
        this.initiator = initiator;
    }

    public LeeGraph(int size, int D) {
        super(size);
        this.D = D;
    }

    @Override
    public GraphNode createGraphNode(int val) {
        return new LeeGraphNode(this, val, D);
    }

    @Override
    public void setPredSubGraphNode(int val, List<GraphNode> predecessors, List<GraphNode> successors) {
        ((LeeGraphNode) graphNodes[val]).setPredecessors(predecessors);
        ((LeeGraphNode) graphNodes[val]).setSuccessors(successors);
    }

    @Override
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        this.edges++;
    }

}
