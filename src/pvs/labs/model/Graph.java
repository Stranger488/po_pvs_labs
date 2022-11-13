package pvs.labs.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph {
    protected int graphSize;
    protected GraphNode[] graphNodes;
    protected int edges;
    protected boolean[][] adjMatrix;

    public int getGraphSize() {
        return graphSize;
    }

    public GraphNode[] getGraphNodes() {
        return graphNodes;
    }

    public int getEdges() {
        return edges;
    }

    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

    public Graph(int size) {
        this.graphSize = size + 1;
        adjMatrix = new boolean[this.graphSize][this.graphSize];
        graphNodes = new GraphNode[this.graphSize];
        for (int i = 0; i < this.graphSize; i++) {
            graphNodes[i] = createGraphNode(i);
        }
    }

    public abstract GraphNode createGraphNode(int val);

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
        this.edges++;
    }

    public void printAdjMatrix() {
        for (int i = 0; i < this.graphSize; i++) {
            System.out.print("______");
        }
        System.out.print(" {Adjacency Matrix} ");
        for (int i = 0; i < this.graphSize; i++) {
            System.out.print("______");
        }

        System.out.print("_\n|  N     ");
        for (int i = 0; i < this.graphSize; i++) {
            int indexSpace = (i == 0) ? 1 : (int) (Math.log10(i) + 1);
            System.out.print("|    (" + i + ")");
            for (int k = indexSpace; k <= 4; k++) System.out.print(" ");
            System.out.print("|");
        }
        System.out.print("\n");
        System.out.print("|        |");
        System.out.print("\n");
        for (int i = 0; i < this.adjMatrix.length; i++) {
            int indexSpace = (i == 0) ? 1 : (int) (Math.log10(i) + 1);
            System.out.print("| (" + (i) + ")");
            for (int k = indexSpace; k <= 4; k++) System.out.print(" ");
            for (int j = 0; j < this.adjMatrix.length; j++) {
                System.out.print("|     " + (this.adjMatrix[i][j] ? 1 : 0) + "     |");
            }
            System.out.print("\n");
        }

        for (int i = 0; i < this.graphSize; i++) {
            System.out.print("_________");
        }
        System.out.println("____");
    }

    public List<GraphNode> getSuccessors(int val) {
        List<GraphNode> successors = new ArrayList<>();
        for (var i = 0; i < graphSize; i++) {
            boolean isConnected = this.adjMatrix[val][i];
            if (isConnected) {
                successors.add(graphNodes[i]);
            }
        }
        return successors;
    }

    public List<Integer> getPredecessors(int val) {
        List<Integer> predecessors = new ArrayList<>();
        for (var i = 0; i < graphSize; i++) {
            boolean isConnected = this.adjMatrix[i][val];
            if (isConnected) {
                predecessors.add(i);
            }
        }
        return predecessors;
    }

}
