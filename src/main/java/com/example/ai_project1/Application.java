package com.example.ai_project1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Project");
        stage.setScene(scene);
        stage.show();
    }

    public static Graph g = new Graph();
    public static void main(String[] args) {
        read(g);
        launch();
    }
    public static String depthFirstSearch(Graph graph, String root, String goal) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                List<String> aAdjVertices = graph.getAdjVertices(vertex);
                if(aAdjVertices.contains(goal))
                    break;
                Collections.sort(aAdjVertices);
                Collections.reverse(aAdjVertices);
                for (String v : aAdjVertices) {
                    stack.push(v);
                }
            }
        }
        StringBuilder path = new StringBuilder();
        for (String v: visited)
            path.append(v).append("->");

        return path.append(goal).toString();
    }

    public static String breadthFirstSearch(Graph graph, String root, String goal) {
        Set<String> visited = new LinkedHashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            List<String> aAdjVertices = graph.getAdjVertices(vertex);
            if(aAdjVertices.contains(goal))
                break;
            Collections.sort(aAdjVertices);
            for (String v : aAdjVertices) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        StringBuilder path = new StringBuilder();
        for (String v: visited)
            path.append(v).append("->");

        return path.append(goal).toString();
    }
    public static String aStar(String src, String goal, Graph graph) {
        Stack<String> stack = new Stack<String>();
        StringBuilder path= new StringBuilder();
        ArrayList<String> queue = new ArrayList<>();
        Map<String,String> parents = new HashMap<>();
        Map<String,Double> costs = new HashMap<>();
        queue.add(src);
        costs.put(src, 0.0);
        String last = goal;
        while (!queue.isEmpty()) {
            String u = LC(costs,queue);
            queue.remove(u);
            System.out.println("Expanding "+u);
            for (String v : graph.getAdjVertices(u)) {
                double cost = costs.get(u) + graph.getEdgeCost(u,v) + 0 - 0;
                if (costs.containsKey(v)) {
                    if (cost < costs.get(v)) {
                        costs.replace(v, costs.get(v), cost);
                        parents.replace(v, parents.get(v), u);
                    }
                } else {
                    costs.put(v, cost);
                    queue.add(v);
                    parents.put(v, u);
                }
            }
        }
        //create path
        while (!last.equals(src)) {
            stack.push(last);
            System.out.println(last);
            last = parents.get(last);
        }
        path.append(last);
        while (!stack.isEmpty())
            path.append("->").append(stack.pop());

        return path.toString();
    }
    private static String LC(Map<String, Double> costs, ArrayList<String> queue) {
        Double cost = Double.MAX_VALUE;
        String rtrn=" ";
        for (String temp : queue)
            if (costs.get(temp)<cost) {
                cost=costs.get(temp);
                rtrn = temp;
            }
        return rtrn;
    }
    public static void read(Graph g)  {
        File file = new File("D:\\Programming\\Java\\AI_Project\\src\\main\\java\\com\\example\\ai_project1\\datafile.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
            String[] vertices= in.nextLine().split(" ");;
            while (in.hasNextLine()) {
                String[] args = in.nextLine().split(" ");
                for (int i=1;i<args.length;i++) {
                    String[] hAndCost = args[i].split(",");
                    if (hAndCost.length>1) {
                        g.addEdge(args[0],vertices[i-1], Integer.parseInt(hAndCost[1]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}