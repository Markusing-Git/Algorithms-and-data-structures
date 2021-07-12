package algorithms.path_algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * An example of dijsktras algorithm using an adjency list and loading the list from graph.txt.
 * The algorithm finds the shortest or "cheapest" path from any given two nodes in a weighted graph.
 *
 * @author mIngemarsson
 */
public class DijkstrasAlgorithmList {
    public static class AdjencyList {
        private static class Edge {
            private final int endNode, weight;

            private Edge(int endNode, int weight) {
                this.endNode = endNode;
                this.weight = weight;
            }

            public boolean equals(Edge edge) {
                return edge.endNode == this.endNode;
            }
        }

        // The graph
        private ArrayList<Edge>[] graph;

        public AdjencyList(String filename) {
            this.graph = getGraph(filename);
        }

        public void getShortestPath(char startNode, char endNode){
            int start = (int) startNode - 'A';
            int end = (int) endNode - 'A';
            int x = Integer.MAX_VALUE;
            BitSet setT= new BitSet(graph.length);
            setT.set(0, graph.length);
            int[] dist = new int[graph.length];
            int[] pred = new int[graph.length];

            //initilize dist and pred
            dist[start] = 0;
            for(int i = 0; i<graph.length; i++){
                if(i!=start)
                    dist[i] = x;
            }

            //dijkstras algorithm
            for (int i = 0; i < graph.length-1; i++) {
                int u = next(dist, setT);
                setT.clear(u);
                for (Edge v: graph[u]) {
                    int weight = v.weight;
                    if (dist[v.endNode] > dist[u] + weight) {
                        dist[v.endNode] = dist[u] + weight;
                        pred[v.endNode] = u;
                    }
                }
            }
            System.out.println("Shortest path from " + startNode + " to " + endNode + " is " + dist[end] + " via: " +
                    ((pred[end]!=0) ? (char) (pred[end] +'A'): "self"));
        }


        @Override
        public String toString() {
            String info="";
            for (int i = 0; i<graph.length; i++) {
                info += "node " + (char)(i+'A') + " adjacent to [";
                for (Edge edge: graph[i]) {
                    info += (char)(edge.endNode+'A') + ":" + edge.weight + ", ";
                }
                info = info.substring(0, info.length()-2);
                info+="]\n";
            }
            return info;
        }

        private int next(int[] d, BitSet setT) {
            int dMin = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < d.length; i++)
                if (d[i] < dMin && setT.get(i)) {
                    dMin = d[i];
                    minIndex = i;
                }
            return minIndex;
        }

        /**
         * This method reads a text file representation of a graph and returns a list of edges.
         * @param filename use graph.txt as a example graph.
         * @return array of
         */
        private ArrayList<Edge>[] getGraph(String filename){
            try{
                BufferedReader in = new BufferedReader(new FileReader("src/algorithms/path_algorithms/" + filename));
                int nbrOfNodes = Integer.parseInt(in.readLine());
                ArrayList<Edge>[] bufferedNodes = new ArrayList[nbrOfNodes];

                String line = in.readLine();
                while (line !=null){
                    String[] words = line.split(" ");
                    int node = (int) words[0].charAt(0) - 65;
                    int endNode = (int) words[1].charAt(0) - 65;
                    int weight = Integer.parseInt(words[2]);
                    if(bufferedNodes[node]==null){
                        bufferedNodes[node] = new ArrayList<>();
                    }
                    bufferedNodes[node].add(new Edge(endNode, weight));
                    line = in.readLine();
                }
                return bufferedNodes;

            }   catch(IOException e){
                e.printStackTrace(System.err);
                System.exit(1);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        AdjencyList adjList = new AdjencyList("graph.txt");
        System.out.println(adjList);
        adjList.getShortestPath('B', 'F');
        adjList.getShortestPath('B', 'B');
        adjList.getShortestPath('A', 'F');
        adjList.getShortestPath('C', 'D');
        adjList.getShortestPath('F', 'C');
    }

}
