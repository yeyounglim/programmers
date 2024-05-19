import java.util.*;

class Solution {
    private int maxSheep;
    private int[] info;
    private int[][] edges;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        this.maxSheep = 0;

        Set<Integer> visitPath = new HashSet<>();
        visitPath.add(0);

        dfs(0, 0, 0, visitPath);

        return maxSheep;
    }

    private void dfs(int sheep, int wolf, int currentNode, Set<Integer> visitPath) {
        if (info[currentNode] == 0)
            sheep++;
        else
            wolf++;

        if (sheep <= wolf)
            return;

        maxSheep = Math.max(maxSheep, sheep);

        for (int[] edge : edges) {
            int nextNode = -1;
            if (visitPath.contains(edge[0]) && !visitPath.contains(edge[1]))
                nextNode = edge[1];
            else if (visitPath.contains(edge[1]) && !visitPath.contains(edge[0]))
                nextNode = edge[0];

            if (nextNode != -1) {
                visitPath.add(nextNode);
                dfs(sheep, wolf, nextNode, visitPath);
                visitPath.remove(nextNode);
            }
        }
    }
}