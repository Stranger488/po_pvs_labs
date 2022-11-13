package pvs.labs.lab1;

import pvs.labs.lab1.model.EchoGraph;
import pvs.labs.lab1.model.EchoGraphNode;
import pvs.labs.model.Graph;
import pvs.labs.model.GraphNode;


public class EchoWaveAlgorithm {

    public void executeEchoWave(Graph g) {
        int graphSize = g.getGraphSize();
        GraphNode[] nodes = g.getGraphNodes();
        EchoGraph graph = (EchoGraph) g;

        int initiator = 0;
        ((EchoGraphNode) nodes[initiator]).setFather(-1);
        System.out.println("Initiator for graph: " + initiator);

        boolean isEnd = false;
        while (!isEnd) {
            for (int j = 0; j < graphSize; j++) {
                System.out.println("Node " + j + " will perform the protocol");

                if (!nodes[j].isVisited()) {
                    if (((EchoGraphNode) nodes[j]).hasFather()) {
                        graph.sentMessagesToNeighbors(j);
                    } else {
                        System.out.println("Node " + j + " stayed IDLE. (has not yet discovered)");
                    }
                } else {
                    if (j == initiator) {
                        if (((EchoGraphNode) nodes[j]).canDecide()) {
                            isEnd = true;
                            System.out.println("DECISION IS MADE!");
                            if (graph.getEdges() * 2 != graph.getMessages()) {
                                throw new IllegalStateException("THEOREM INVALID");
                            }
                            break;
                        }
                    } else {
                        graph.echo(j);
                    }
                }
            }
        }
    }

}
