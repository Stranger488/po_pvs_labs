package pvs.labs.lab1.model;

import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class EchoGraph extends Graph {
    private int totalMessages;

    public EchoGraph(int size) {
        super(size);
    }

    @Override
    public GraphNode createGraphNode(int val) {
        return new EchoGraphNode(val);
    }

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        ((EchoGraphNode) graphNodes[i]).incrementNeighbors();
        adjMatrix[j][i] = true;
        ((EchoGraphNode) graphNodes[j]).incrementNeighbors();
        this.edges++;

    }

    public void echo(int val) {
        EchoGraphNode curNode = (EchoGraphNode) this.graphNodes[val];
        if (curNode.echo()) {
            ((EchoGraphNode) this.graphNodes[curNode.getFather()]).receiveToken();
            this.totalMessages++;
        } else {
            System.out.println("Node " + val + " stayed IDLE. (has either already echoed or is waiting to)");
        }
    }

    public int getMessages() {
        return this.totalMessages;
    }

    @Override
    public String toString() {
        return "Graph has " + this.edges + " total edges and " + this.totalMessages + " total sent messages";
    }

    public void sentMessagesToNeighbors(int father) {
        EchoGraphNode fatherGraphNode = (EchoGraphNode) graphNodes[father];
        for (int n = 0; n < this.graphSize; n++) {
            if (n == fatherGraphNode.getFather()) continue;
            if (adjMatrix[father][n]) {
                this.totalMessages++;
                EchoGraphNode neigh = (EchoGraphNode) this.graphNodes[n];
                if (neigh.getFather() < -1) {
                    neigh.setFather(father);
                } else {
                    fatherGraphNode.sentToken(neigh);
                }
            }
        }

        fatherGraphNode.setVisited();
    }

}
