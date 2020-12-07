package practice.graph;


import practice.collections.queue.Queue;
import practice.collections.stack.Stack;

public class Graph {
    private int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private Stack<Integer> theStack = new Stack<>();
    private Queue<Integer> theQueue = new Queue<>();

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int j = 0; j < MAX_VERTS; j++) {
            for(int k = 0; k < MAX_VERTS; k++) {
                adjMat[j][k] = 0;
            }
        }
    }

    public void addVertex(char vertex) {
        vertexList[nVerts] = new Vertex(vertex);
        nVerts++;
    }

    public void addEdge(int j, int k) {
        adjMat[j][k] = 1;
        adjMat[k][j] = 1;
    }

    public int getEdgeLength(int j, int k) {
        return adjMat[j][k];
    }

    public void addEdge(int j, int k, int length) {
        adjMat[j][k] = length;
    }

    public void displayMat() {
        System.out.print("\t");
        for(int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i] + "\t");
        }
        System.out.println();
        System.out.println("  ===================");
        for(int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i] + " |\t");
            for(int j = 0; j < nVerts; j++) {
                System.out.print(adjMat[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void dfs() {
        System.out.println("Обход в глубину:");
        int current = 0;
        theStack.push(current);
        vertexList[current].setWasVisited(true);
        System.out.print(vertexList[current] + " ");
        while (!theStack.isEmpty()) {
            int vertex = theStack.peek();
            int child = getAdjUnvisitedVertex(vertex);
            if (child != -1) {
                vertexList[child].setWasVisited(true);
                System.out.print(vertexList[child] + " ");
                theStack.push(child);
            } else {
                theStack.pop();
            }
        }
        resetUnvisited();
    }

    public void bfs() {
        System.out.println("Обход в ширину:");
        int current = 0;
        theQueue.enqueue(current);
        System.out.print(vertexList[current] + " ");
        vertexList[current].setWasVisited(true);
        while (!theQueue.isEmpty()) {
            int vertex = theQueue.dequeue();
            int child = -1;
            while ((child = getAdjUnvisitedVertex(vertex)) != -1) {
                vertexList[child].setWasVisited(true);
                System.out.print(vertexList[child] + " ");
                theQueue.enqueue(child);
            }
        }
        resetUnvisited();
    }

    private int getAdjUnvisitedVertex(int v) {
        for(int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] == 1 && !vertexList[j].wasVisited()) {
                return j;
            }
        }
        return -1;
    }

    private void resetUnvisited() {
        for (Vertex vertex : vertexList) {
            if (vertex != null) {
                vertex.setWasVisited(false);
            }
        }
    }

    public int dijkstraAlgorithm(int j, int k) {
        return dijkstra(adjMat, j)[k];
    }

    /**
     * Функция для нахождения вершины с минимальным значением пути
     * Которые еще не были исследованы
     */
    private int minDistance(int dist[], Boolean visited[])
    {
        // Инициализация минимальных значений
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < vertexList.length; v++)
            if (!visited[v] && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    /**
     * Функция реализует алгоритм Диекстры
     * @param graph
     * @param src
     * @return
     */
    private int[] dijkstra(int graph[][], int src)
    {
        /*
         * Будет хранить короткие пути от src к другим вершинам
         */
        int dist[] = new int[vertexList.length];

        /*
         * Будет хранить была ли вершина исследована
         * И было ли найдено короткое расстояние
         */
        Boolean visited[] = new Boolean[vertexList.length];

        /*
         * Инициализация самых коротких путей как бесконечность
         * И инициализация переменных visited как false
         */
        for (int i = 0; i < vertexList.length; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        /*
         * Расстояние к самому себе всегда 0
         */
        dist[src] = 0;

        /*
         * Нахождение самого короткого расстояния для всех вершин
         */
        for (int count = 0; count < vertexList.length-1; count++)
        {
            /*
             * Найти минимальное расстояние к вершине от списка вершин, которые еще не были исследованы
             */
            int u = minDistance(dist, visited);

            /*
             * Указание вершины как исследованной
             */
            visited[u] = true;

            /*
             * Обновление значения короткого расстояния от src к другим через текущую вершину
             */
            for (int v = 0; v < vertexList.length; v++)

                /*
                 * Обновление значения самого короткого расстояния если:
                  * он еще не был исследован
                  * имеется соединяющее ребро
                  * Сумма расстояния от src к целевому через текущую вершину меньше предыдущего значения
                 */
                if (!visited[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        return dist;
    }

}
