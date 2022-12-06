package pvs.labs.lab3;

import pvs.labs.lab3.model.PhaseGraphNode;
import pvs.labs.model.Graph;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhaseWaveAlgorithm {

    public void executePhaseWave(Graph g, Integer start) {
        int initiator = start;
        System.out.println("Initiator for graph: " + initiator);

        ExecutorService taskExecutor = Executors.newFixedThreadPool(g.getGraphSize());
        ((PhaseGraphNode) g.getGraphNodes()[start]).runMain();
        Arrays.stream(g.getGraphNodes()).forEach(task -> taskExecutor.execute((Runnable) task));
        taskExecutor.shutdown();
    }

}
