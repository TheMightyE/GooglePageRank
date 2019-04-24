/* Syed Khalid      cs435 1652 mp */

import java.util.Arrays;

public class PageRank_1652 {
    private Graph_1652 graph;
    private int iterations;
    private double initialValue;
    private final double ERROR_RATE = 0.0001;

    PageRank_1652(int iterations, double initialValue, String filename) {
        this.iterations = iterations;
        this.initialValue = initialValue;
        graph = new Graph_1652(filename);

        /* If initialValue=-1 -> initialValue=1/n
         * If initialValue=-2 -> initialValue=1/sqrt(n) */
        if (initialValue == -1) {
            this.initialValue = 1.0 / graph.getNumVertices();
        } else if (initialValue == -2) {
            this.initialValue = 1.0 / Math.sqrt(graph.getNumVertices());
        }

    }

    void rank() {
        int iteration = 0,
                numVertices = graph.getNumVertices(),
                numEdges = graph.getNumEdges();
        double d = 0.85, sum;
        double[] rank = new double[numVertices];
        double[] previousRank = new double[numVertices];
        Arrays.fill(previousRank, initialValue);

        System.out.print("Base:\t\t\t");
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("P[ %d]=%.6f ", i, previousRank[i]);
        }
        System.out.println();

        while (true) {
            for (int i = 0; i < numVertices; i++) {
                sum = 0;
                for (int j = 0; j < numVertices; j++) {
                    if (graph.get(i, j) != 0) {
                        int outDegree = graph.getOutDegree(j);
                        sum += previousRank[j] / outDegree;
                    }
                }
                double x = (1 - d) / numVertices;
                rank[i] = x + (d * sum);
            }

            if (iterations == 0) {
                int diff = 0;
                for (int i = 0; i < numVertices; i++) {
                    if ((previousRank[i] - rank[i]) > ERROR_RATE)
                        diff++;
                }
                if (diff == 0 && numVertices > 10) {
                    /* Print last iteration. */
                    System.out.printf("Iteration %d\n", iteration + 1);
                    for (int i = 0; i < numVertices; i++) {
                        System.out.printf("P[ %d]=%.6f\n", i, rank[i]);
                    }
                    return;
                } else if (diff != 0 && numVertices <= 10) {
                    System.out.printf("Iteration %d: \t", iteration + 1);
                    for (int i = 0; i < numVertices; i++) {
                        System.out.printf("P[ %d]=%.6f ", i, rank[i]);
                    }
                    System.out.println();
                } else if (diff != 0 && numVertices > 10) {

                }
            } else {
                System.out.printf("Iteration %d: \t", iteration + 1);
                for (int i = 0; i < numVertices; i++) {
                    System.out.printf("P[ %d]=%.6f ", i, rank[i]);
                }
                System.out.println();
            }

            for (int i = 0; i < numVertices; i++) {
                previousRank[i] = rank[i];
            }
            iteration++;
            if (iteration == iterations) {
                return;
            }
        }

    }

}
