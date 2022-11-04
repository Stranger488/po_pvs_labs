package pvs.labs.lab1;

import pvs.labs.lab1.model.Graph;
import pvs.labs.lab1.model.GraphNode;


public class EchoWaveAlgorithm {

    public void executeEchoWave(Graph g) {
        int graphSize = g.getGraphSize();
        GraphNode[] nodes = g.getNodesInfo();

        int initiator = 0;
        nodes[initiator].setFather(-1);
        System.out.println("Initiator for graph: " + initiator);

        boolean isEnd = false;
        while (!isEnd) {
            for (int j = 0; j < graphSize; j++) {
                System.out.println("Node " + j + " will perform the protocol");

                if (!nodes[j].isVisited()) {
                    if (nodes[j].hasFather()) {
                        g.sentMessagesToNeighbors(j);
                    } else {
                        System.out.println("Node " + j + " stayed IDLE. (has not yet discovered)");
                    }
                } else {
                    if (j == initiator) {
                        if (nodes[j].canDecide()) {
                            isEnd = true;
                            System.out.println("DECISION IS MADE!");
                            if (g.getGraphEdges() * 2 != g.getMessages()) {
                                throw new IllegalStateException("THEOREM INVALID");
                            }
                            break;
                        }
                    } else {
                        g.echo(j);
                    }
                }
            }
        }
    }

}
