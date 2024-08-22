package com.booking.oa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ooo
 * @description 图的构建和遍历
 * https://leetcode.com/problems/evaluate-division/
 * @date 2024/8/22 17:27:58
 */
public class EvaluateDivision {
    Map<String, Map<String, Node>> nodeMap = new HashMap<>();
    public static void main(String[] args) {

    }
    /**
     * maybe we could use a graph to reprensent the equations;
     * then search the graph to find right route which can match the query
     * first problem is how to build a graph: Node with start,end and value
     * second one is how to search the route in this graph: BFS
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Node start = initNodes(equations, values);

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            double r = bfs(start, query);
            result[i] = r;
        }
        return result;
    }

    private double bfs(Node start, List<String> query) {
        double result = 0.0;

        return result;
    }

    private Node initNodes(List<List<String>> equations, double[] values){
        Node start = null;
        for(int i = 0; i < values.length; i++){
            List<String> equation = equations.get(i);
            double value = values[i];

            Node node = new Node(equation.get(0), equation.get(1), value);
            Node revertNode = new Node(equation.get(1), equation.get(0), div(1, value));
            node.next = revertNode;
            revertNode.next = node;

            if(i == 0){
                start = node;
            }
            /*Map<String, Node> secNode = new HashMap(){
                {
                    put(equation.get(1), node);
                }
            };
            nodeMap.put(equation.get(0), secNode);*/
        }
        return start;
    }

    private double div(double a, double b){
        BigDecimal bdA = new BigDecimal(Double.toString(a));
        BigDecimal bdB = new BigDecimal(Double.toString(b));
        BigDecimal result = bdA.divide(bdB, 5, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
    static class Node{
        private String start;
        private String end;
        private double value;

        private Node next;

        public Node(String start, String end, double value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}
