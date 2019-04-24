/* Syed Khalid      cs435 1652 mp */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Graph_1652 {
    private double[][] graph;
    private int numVertices;
    private int numEdges;
    private String filename;

    public Graph_1652(String filename) {
        this.filename = filename;
        makeGraphFromFile();
    }

    public double[][] getGraph() {
        return graph;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    double get(int i, int j) {
        return graph[i][j];
    }

    private void makeGraphFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = "";
            String[] splitLine;

            /* The first line in the file is the number of numVertices and edges
             * separated by a space. Let's get them. */
            line = br.readLine();
            splitLine = line.split(" ");
            if (splitLine.length == 2) {
                numVertices = Integer.parseInt(splitLine[0]);
                numEdges = Integer.parseInt(splitLine[1]);
                graph = new double[numVertices][numVertices];

            } else {
                System.err.println("Line 1 on file is expected to have two space separated integers. " +
                        "Number of numVertices and number of edges respectively.");
                System.err.println("Example:");
                System.err.println("4 4");
            }

            /* Next lines are edges in the form of "i j" on each line. Let's build the graph. */
            while ((line = br.readLine()) != null) { //TODO:make a while loop
                if (line.equals("")) {
                    continue;
                }
                splitLine = line.split(" ");
                int i = Integer.parseInt(splitLine[0]), j = Integer.parseInt(splitLine[1]);

                graph[j][i] = 1;
            }

            /* Put probabilities in graph. */
            for (int i = 0; i < numVertices; i++) {
                int outDegree = getOutDegree(i);
                if (outDegree > 1) {
                    for (int j = 0; j < numVertices; j++) {
                        if (graph[j][i] == 1)
                            graph[j][i] = 1.0 / outDegree;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    int getOutDegree(int vertex) {
        int outDegree = 0;
        for (int k = 0; k < numVertices; k++) {
            if (graph[k][vertex] != 0)
                outDegree++;
        }
        return outDegree;
    }

    ArrayList<Integer> getInDegrees(int vertex) {
        ArrayList<Integer> inDegrees = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] != 0) {
                inDegrees.add(i);
            }
        }

        return inDegrees;
    }

    void print() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.format("%.4f ", graph[i][j]);
            }
            System.out.println();
        }
    }
}
