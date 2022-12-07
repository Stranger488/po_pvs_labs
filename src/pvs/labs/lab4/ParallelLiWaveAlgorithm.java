package pvs.labs.lab4;

import pvs.labs.lab3.model.PhaseGraph;
import pvs.labs.model.Graph;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelLiWaveAlgorithm {

    public void executeLiWave(Graph g, Integer start) {
        System.out.println("Initiator for graph: " + start);
        ((PhaseGraph) g).setInitiator(start);

        ExecutorService taskExecutor = Executors.newFixedThreadPool(g.getGraphSize());
        Arrays.stream(g.getGraphNodes()).forEach(task -> taskExecutor.execute((Runnable) task));
        taskExecutor.shutdown();
    }

}
