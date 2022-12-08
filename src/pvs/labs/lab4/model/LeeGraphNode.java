package pvs.labs.lab4.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class LeeGraphNode extends GraphNode {

    private final int D;

    private final Graph graph;

    private int markVal = -1;

    private List<GraphNode> predecessors = new ArrayList<>();

    private List<GraphNode> successors = new ArrayList<>();

    public int getMarkVal() {
        return markVal;
    }

    public void setMarkVal(int markVal) {
        this.markVal = markVal;
    }

    public void setPredecessors(List<GraphNode> predecessors) {
        this.predecessors = predecessors;
    }

    public void setSuccessors(List<GraphNode> successors) {
        this.successors = successors;
    }

    public List<GraphNode> getPredecessors() {
        return predecessors;
    }

    public List<GraphNode> getSuccessors() {
        return successors;
    }

    public LeeGraphNode(Graph graph, int size, int D) {
        super(size);
        this.D = D;
        this.graph = graph;
    }

}
