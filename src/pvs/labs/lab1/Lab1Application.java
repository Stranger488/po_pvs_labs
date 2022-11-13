package pvs.labs.lab1;

import pvs.labs.lab1.model.EchoGraph;
import pvs.labs.model.Graph;
import pvs.labs.utils.Utils;

import java.util.List;

public class Lab1Application {

    public static void main(String[] args) {
        List<String> rawGraph = List.of(
                "0 1",
                "0 2",
                "0 3",
                "1 4",
                "1 5",
                "2 6",
                "3 7",
                "6 8",
                "7 9",
                "7 10"
        );

        Graph echoGraph = Utils.readGraphLine(new EchoGraph(rawGraph.size()), rawGraph);
        echoGraph.printAdjMatrix();

        var echo = new EchoWaveAlgorithm();
        echo.executeEchoWave(echoGraph);

        System.out.println(echoGraph);
    }

}
