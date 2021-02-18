package com.mariciucioan.PALab1optional;

/*
    ~~ Made by Mariciuc Ioan ~~
    ~~ RandomMatrix class has the scope to generate and work with an random adjacency matrix of a graph
 */

public class RandomMatrix {
    private final int n;
    private final int[] vertexCount;

    private final boolean[][] matrix;
    private final boolean[] visited;
    private boolean connected;

    private final StringBuilder components = new StringBuilder();

    // Matrix constructor
    // If printingCondition = true then prints the generated matrix
    public RandomMatrix(int n, boolean printingCondition) {
        this.n = n;
        this.matrix = new boolean[n][n];
        this.visited = new boolean[n];
        this.vertexCount = new int[n];

        generateRandomMatrix(printingCondition);
    }

    // Generate the graph's matrix
    // If printingCondition = true then prints the generated matrix
    public void generateRandomMatrix(boolean addPrinting) {
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
               boolean value = (int) Math.round(Math.random()) == 1;
               matrix[i][j] = value;
               matrix[j][i] = value;
               if(value) {
                   vertexCount[i]++;
                   vertexCount[j]++;
               }
            }
        }

        if(addPrinting)
            print();
    }

    // Prints the graph
    public void print() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++)
                System.out.print((matrix[i][j] ? "1" : "0" ) + " ");

            System.out.print('\n');
        }
    }

    // Checks connectivity of the graph
    private void DFS_ConnectionCheck(int node) {
        visited[node] = true;
        if(n<=50)
            components.append(node).append(" ");

        for (int j = 0; j < n; j++) {
            if (matrix[node][j] && !visited[j]) {
                DFS_ConnectionCheck(j);
            }
        }
    }

    // Runs the DFS on the matrix and prints the connected components
    public void showComponents() {
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(n<=50)
                    components.append("Component ").append(count + 1).append(": ");
                DFS_ConnectionCheck(i);
                if(n<=50)
                    components.append("\n");
                count ++;
            }
        }
        if(count == 1) {
            connected = true;
            System.out.println("The graph is connected.");
        } else {
            if(n<=50)
                System.out.println(components);
            else
                System.out.println("There are " + count + "components in the graph. Can't print because of stack memory :(.");
        }
    }

    // Makes a partial tree using the matrix and prints it if printIt = true
    public void makePartialTree(boolean printIt) {
        if(!isConnected()) {
            System.out.println("You can't make a partial tree because the graph is not connected.");
            return;
        }

        int root=0;
        for(int i=0; i<n; i++) {
            root = root < vertexCount[i] ? i : root;
        }

        for(int i=0; i<n; i++) {
            if(i==root)
                continue;

            for (int j = 0; j < n; j++) {
                if (matrix[i][j]) {
                    if (j == root)
                        continue;

                    if (vertexCount[i] > 1 && vertexCount[j] > 1) {
                        matrix[i][j] = false;
                        matrix[j][i] = false;
                        vertexCount[i]--;
                        vertexCount[j]--;
                    }
                }
            }
        }

        if(printIt) {
            System.out.println("The resulting adjacency matrix of the partial tree:");
            print();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
