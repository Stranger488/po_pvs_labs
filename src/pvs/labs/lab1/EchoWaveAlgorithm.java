package pvs.labs.lab1;

import pvs.labs.lab1.model.graph.Graph;
import pvs.labs.lab1.model.graph.GraphNode;

public class EchoWaveAlgorithm {

    boolean executeEchoWave(Graph g) {
        int graphSize = g.getGraphSize();
        GraphNode[] nodes = g.getNodesInfo();

        int initiator = (int) (Math.random() * (graphSize));
        nodes[initiator].setFather(-1);
        System.out.println("Инициатор: " + initiator);

        g.echo(initiator);

        if (!nodes[initiator].isVisited()) {
            if (nodes[initiator].hasFather()) {
                // Send message to neighbours
                g.sendMessagesToNeighbors(initiator);
            } else {
                System.out.println("Node " + initiator + " stayed IDLE. (has not yet discovered)");
            }
        } else {
            if (nodes[initiator].canDecide()) {
                System.out.println("DECISION IS MADE");
                if (g.getGraphEdges() * 2 != g.getMessages()) {
                    throw new IllegalStateException("THEOREM INVALID");
                }
                return false;
            }
            g.echo(initiator);
        }

        return true;
    }

}
