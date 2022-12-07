package pvs.labs.lab4.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;
import pvs.labs.model.Message;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LiGraph extends Graph {

    private final int D;

    public int getInitiator() {
        return initiator;
    }

    private int initiator;

    public void setInitiator(int initiator) {
        this.initiator = initiator;
    }

    private ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Message>> lst;

    public LiGraph(int size, int D) {
        super(size);
        this.D = D;
    }

    @Override
    public GraphNode createGraphNode(int val) {
        LiGraphNode graphNode = new LiGraphNode(this, val, D);
        if (lst == null) {
            lst = new ConcurrentHashMap<>();
        }
        lst.put(val, new ConcurrentLinkedQueue<>());
        graphNode.setLst(lst);
        return graphNode;
    }

    @Override
    public void setPredSubGraphNode(int val, List<GraphNode> predecessors, List<GraphNode> successors) {
        ((LiGraphNode) graphNodes[val]).setPredecessors(predecessors);
        ((LiGraphNode) graphNodes[val]).setSuccessors(successors);

        predecessors.forEach(p ->
            ((LiGraphNode) graphNodes[val]).getRecCount().put(p.getValue(), 0)
        );
    }

    @Override
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        this.edges++;
    }

}
