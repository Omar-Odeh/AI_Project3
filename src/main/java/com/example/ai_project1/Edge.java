package com.example.ai_project1;

public class Edge {
    private String source;
    private String destination;
    private final int cost;
    private int heuristic;

    public Edge(String source, String destination, int cost){
        this.source = source;
        this.destination = destination;
        this.cost = cost;
        heuristic=0;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getCost(){
        return this.cost;
    }

    public String getSource(){
        return this.source;
    }

    public String getDestination(){
        return this.destination;
    }

    public int getHeuristic() {
        return heuristic;
    }
}
