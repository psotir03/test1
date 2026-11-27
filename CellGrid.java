import java.awt.Color;

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
    public int getSize(){
        return size;
    }
    public Cell getCell(int x, int y){
        return grid[x][y];
    }
    public void putNest(int x, int y, int[] antIds){
        grid[x][y].putNest();
        for(int i=0; i<antIds.length; i++){
            grid[x][y].addAnt(antIds[i]);
        }
    }
    public void putFood(int x, int y, int food){
        grid[x][y].putFood(food);
    }
    public void updateScent(int time, int elapsed){
        for(int i=0; i<grid.length; i++)
            for(int k=0; k<grid[0].length; k++)
                grid[i][k].updateScent(time, elapsed);
    }
    public boolean allSeedsCollected(){
        for(int i=0; i<grid.length; i++)
            for(int k=0; k<grid[0].length; k++)
                if(grid[i][k].hasFood() && !grid[i][k].withNest()) return false;
        return true;
    }
    public String toString(){
        String text="";
        for(int i=0; i<grid.length; i++)
            for(int k=0 ; k<grid[0].length; k++)
                text+=grid[i][k].toString();
        return text;
    }
    public void draw(){
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, size);
        StdDraw.setYscale(0, size);
        // Background
        StdDraw.clear(Color.DARK_GRAY);
        // Draw grid lines
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setPenRadius(0.005);
        for(int i = 0; i <= size; i++){
            StdDraw.line(i, 0, i, size);
            StdDraw.line(0, i, size, i);
        }
        // Draw nests and food
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j].withNest()){
                    StdDraw.setPenColor(Color.WHITE);
                    StdDraw.filledCircle(i, j, 0.45);
                }
                if(grid[i][j].hasFood()){
                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.filledCircle(i, j, 0.45);
                }
            }
        }
    }
}