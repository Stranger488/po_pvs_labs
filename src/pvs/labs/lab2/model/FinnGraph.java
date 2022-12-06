package pvs.labs.lab2.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.List;

public class FinnGraph extends Graph {

    public FinnGraph(int size) {
        super(size);
    }

    @Override
    public GraphNode createGraphNode(int val) {
        return new FinnGraphNode(val);
    }

    @Override
    public void setPredSubGraphNode(int val, List<GraphNode> predecessors, List<GraphNode> successors) {

    }

    @Override
    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        this.edges++;
    }

    public void sentMessagesToNeighbors(int father) {
        FinnGraphNode fatherGraphNode = (FinnGraphNode) graphNodes[father];
        for (int n = 0; n < this.graphSize; n++) {
            if (adjMatrix[father][n]) {
                ((FinnGraphNode) graphNodes[n]).getNodesFrom().add(father);
            }
        }

        fatherGraphNode.setVisited();
    }

}
