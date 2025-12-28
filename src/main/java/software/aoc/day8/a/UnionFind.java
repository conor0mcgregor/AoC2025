package software.aoc.day8.a;

public class UnionFind {
    int[] parent;
    int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent[i]); // Path compression
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            // Union by size
            if (size[rootI] < size[rootJ]) {
                int temp = rootI; rootI = rootJ; rootJ = temp;
            }
            parent[rootJ] = rootI;
            size[rootI] += size[rootJ];
        }
    }
}
