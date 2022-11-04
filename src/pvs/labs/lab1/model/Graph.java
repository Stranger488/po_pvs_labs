package pvs.labs.lab1.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int graphSize;
    private int edges;
    private int totalMessages;
    GraphNode[] graphNodes;
    private boolean adjMatrix[][];

    public Graph(int size) {
        this.graphSize = size + 1;
        adjMatrix = new boolean[this.graphSize][this.graphSize];
        graphNodes = new GraphNode[this.graphSize];
        for (int i = 0; i < this.graphSize; i++) {
            graphNodes[i] = new GraphNode(i);
        }
    }

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        graphNodes[i].incrementNeighbors();
        adjMatrix[j][i] = true;
        graphNodes[j].incrementNeighbors();
        this.edges++;

    }

    public void echo(int val) {
        GraphNode curNode = this.graphNodes[val];
        if (curNode.echo()) {
            this.graphNodes[curNode.getFather()].receiveToken();
            this.totalMessages++;
        } else {
            System.out.println("Node " + val + " stayed IDLE. (has either already echoed or is waiting to)");
        }
    }

    public GraphNode[] getNodesInfo() {
        return this.graphNodes;
    }

    public boolean[][] getAdjMatrix() {
        return this.adjMatrix;
    }

    public int getGraphSize() {
        return this.graphSize;
    }

    public int getGraphEdges() {
        return this.edges;
    }

    public int getMessages() {
        return this.totalMessages;
    }

    public void printAdjMatrix() {
        for (int i = 0 ; i < this.graphSize; i++) {
            System.out.print("______");
        }
        System.out.print(" {Adjacency Matrix} ");
        for (int i = 0 ; i < this.graphSize; i++) {
            System.out.print("______");
        }

        System.out.print("_\n|  N     ");
        for (int i = 0 ; i < this.graphSize; i++) {
            int indexSpace = (i == 0) ? 1 : (int) (Math.log10(i) + 1);
            System.out.print("|    (" + i + ")");
            for (int k = indexSpace ; k <= 4 ; k++) System.out.print(" ");
            System.out.print("|");
        }
        System.out.print("\n");
        System.out.print("|        |");
        System.out.print("\n");
        for (int i = 0 ; i < this.adjMatrix.length ; i++) {
            int indexSpace = (i == 0) ? 1 : (int) (Math.log10(i) + 1);
            System.out.print("| (" + (i) +")");
            for (int k = indexSpace ; k <= 4 ; k++) System.out.print(" ");
            for (int j = 0 ; j < this.adjMatrix.length ; j++) {
                System.out.print("|     " + (this.adjMatrix[i][j] ? 1 : 0) + "     |");
            }
            System.out.print("\n");
        }

        for (int i = 0 ; i < this.graphSize; i++) {
            System.out.print("_________");
        }
        System.out.println("____");
    }

    public String toString() {
        return "Graph has "+this.edges+" total edges and "+ this.totalMessages +" total sent messages";
    }

    public List<Integer> sentMessagesToNeighbors(int father) {
        List<Integer> children = new ArrayList<Integer>();
        for (int n = 0 ; n < this.graphSize ; n++) {
            if (n == graphNodes[father].getFather()) continue;
            if (adjMatrix[father][n]) {
                this.totalMessages++;
                GraphNode neigh = this.graphNodes[n];
                if (neigh.getFather() < -1) {
                    neigh.setFather(father);
                    children.add(n);
                } else {
                    graphNodes[father].sentToken(neigh);
                }
            }
        }


        this.graphNodes[father].setVisited();
        return children;
    }

}
