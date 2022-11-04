package pvs.labs.lab1.model.graph;

import java.util.ArrayList;

public class Graph {
    private int graphSize = 0;
    private int edges = 0;
    private int totalMessages = 0;
    GraphNode[] graphNodes;
    private final boolean[][] adjMatrix;
    private int iterations = 0;

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

    public void resetGraph() {
        System.out.println("Graph [] has been reset");
        this.totalMessages = 0;
        this.iterations = 0;
        for (GraphNode n : this.graphNodes) {
            n.resetNode();
        }
    }

    /**
     * Fires echo on Node with value == val.
     * Then increment father's token.
     *
     * @param val is the value of the Node
     */
    public void echo(int val) {
        GraphNode curNode = this.graphNodes[val];
        if (curNode.echo()) {
            this.graphNodes[curNode.getFather()].receiveToken();
            this.totalMessages++;
        } else {
            System.out.println("Node " + val + " stayed IDLE. (has either already echoed or is waiting to)");
        }
    }

    /**
     * If all nodes are visited on the graph start sending tokens
     *
     * @return true if nodes must send the tokens to their parent
     * <p>
     * public boolean hasStartedEcho() {
     * return (this.totalMessages == this.graphSize);
     * }
     */

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

    public ArrayList<Integer> sentMessagesToNeighbors(int father) {
        ArrayList<Integer> children = new ArrayList<Integer>();
        for (int n = 0; n < this.graphSize; n++) {
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

		/*
		 * First set ALL children to the father
		 * and then check if the children can start echo

		for (int n : children) {
			this.checkForUnexploredNeighbors(n);
		}*/

        this.graphNodes[father].setVisited();
        return children;
    }

    public void setIterations(int i) {
        iterations = i;
    }

}
