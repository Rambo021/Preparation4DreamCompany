package com.booking.oa.dfs;

import javafx.util.Pair;

import java.util.*;

/**
 * @author ooo
 * @description 图的构建和遍历
 * https://leetcode.com/problems/evaluate-division/
 * @date 2024/8/22 17:27:58
 */
public class EvaluateDivision {
    // 使用邻接图表示图
    Map<String, List<Pair<String, Double>>> nodeMap = new HashMap<>();
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>(Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b","c"), Arrays.asList("bc","cd"), Arrays.asList("cd","ef")));
        double[] values = {1.5,2.5,5.0,3.0};
        List<List<String>> queries = new ArrayList<>(Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c","b"), Arrays.asList("bc","cd"), Arrays.asList("cd","bc")));

        EvaluateDivision e = new EvaluateDivision();
        double[] result = e.calcEquation(equations, values, queries);
        for (double v : result) {
            System.out.println("" + v);
        }
    }
    /**
     * maybe we could use a graph to reprensent the equations;
     * then search the graph to find right route which can match the query
     * first problem is how to build a graph: Node with start,end and value
     * second one is how to search the route in this graph: BFS
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // init map to represent the graph
        initNodes(equations, values);

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            Set<String> visited = new HashSet<>();
            // dfs to find the path from start to end; calculate the temp through the search
            double r = dfs(query.get(0), query.get(1), visited);
            result[i] = r;
        }
        return result;
    }

    private double dfs(String start, String end, Set<String> visited) {
        double result = -1.0;

        if (!nodeMap.containsKey(start) || !nodeMap.containsKey(end)){
            return result;
        }

        if (start.equals(end)){
            return -result;
        }

        visited.add(start);

        for (Pair<String, Double> pair : nodeMap.get(start)) {
            String next = pair.getKey();
            if (visited.contains(next)){
                continue;
            }

            double temp = dfs(next, end, visited);

            if (temp != -1.0){
                return temp * pair.getValue();
            }
        }

        return result;
    }

    /**
     * initiate the map to represent a graph:
     * a -> (b, 2.0)  b -> (a, 0.5)  b -> (c, 1.5)  c -> (b, 0.66667)
     * so this is a path: a -> (c, 3.0)
     * @param equations
     * @param values
     */
    private void initNodes(List<List<String>> equations, double[] values){
        for(int i = 0; i < values.length; i++){
            List<String> equation = equations.get(i);
            double value = values[i];

            Pair pair = new Pair(equation.get(1), value);
            Pair revertPair = new Pair(equation.get(0), 1.0 / value);
            nodeMap.putIfAbsent(equation.get(0), new ArrayList<>());
            nodeMap.putIfAbsent(equation.get(1), new ArrayList<>());

            nodeMap.get(equation.get(0)).add(pair);
            nodeMap.get(equation.get(1)).add(revertPair);
        }
    }
}
