package com.example.ai_project1;// Java program to implement Graph with the help of Generics

import java.util.*;

class Graph{
    public final Map<String, Double> heuristic = new HashMap<>();
    // We use Hashmap to store the edges in the graph
    public final Map<String, List<String> > map = new HashMap<>();
    public final List<Edge> edges = new LinkedList<>();
    // This function adds a new vertex to the graph
    public void addVertex(String s) {
        map.put(s, new LinkedList<String>());
    }

    // This function adds the edge between source to destination
    public void addEdge(String source, String destination, int weight) {
        if (!map.containsKey(source))
            addVertex(source);
        if (!map.containsKey(destination))
            addVertex(destination);
        map.get(source).add(destination);
        map.get(destination).add(source);
        Edge e = new Edge(source, destination, weight);
        if(!edges.contains(e))
            edges.add(e);
    }

    List<String> getAdjVertices(String s) {
        return map.get(s);
    }
    public void addHeuristic (String vertex, double heuristic){
        this.heuristic.put(vertex,heuristic);
    }
    // This function gives the count of vertices
    public int getVertexCount() {
        return map.keySet().size();
    }

    // This function gives the count of edges
    public int getEdgesCount(){
        int count = 0;
        for (String v : map.keySet()) {
            count += map.get(v).size();
        }
        return count/2;
    }

    public int getEdgeCost(String s, String d){
        for(Edge e: edges){
            if(s.equals(e.getSource()) && d.equals(e.getDestination()))
                return e.getCost();
        }
        return -1;
    }

    // This function gives whether a vertex is present or not.
    public boolean hasVertex(String s) {
        return (map.containsKey(s));
    }

    // This function gives whether an edge is present or not.
    public boolean hasEdge(String s, String d) {
        return (map.get(s).contains(d));
    }

    // Prints the adjacency list of each vertex.
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (String v : map.keySet()) {
            builder.append(v).append(": ");
            for (String w : map.get(v)) {
                builder.append(w).append(" ");
            }
            builder.append("\n");
        }

        return (builder.toString());
    }
}