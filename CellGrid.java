public class CellGrid {

    private int size; // το μέγεθός του
    private Cell[][] grid; // τα σημεία που το απαρτίζουν

    public CellGrid(int N) {

        size = N;
        grid = new Cell[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new Cell();
            }
        }
    }
}