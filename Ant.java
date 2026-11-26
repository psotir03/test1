public class Ant {

    private CellGrid terain; // το πεδίο στο οποίο βρίσκεται και μπορεί να μετακινηθεί
    private int id; // η ταυτότητά του
    private int[] pos; // οι συντεταγμένες της τρέχουσας θέσης του
    private int previousX; // η συντεταγμένη X της αμέσως προηγούμενης θέσης του
    private int previousY; // η συντεταγμένη Y της αμέσως προηγούμενης θέσης του
    private int[] nestPos; // οι συντεταγμένες της φωλιάς του
    private boolean carriesFood; // κατά πόσο μεταφέρει ένα σπόρο
    private String color; // το χρώμα του

    public Ant(CellGrid grid, int Id, String col, int[] NestPos) {

        terain = grid;
        id = Id;
        color = new String(col);

        pos = new int[2];
        nestPos = new int[2];

        nestPos[0] = NestPos[0];
        nestPos[1] = NestPos[1];

        pos[0] = nestPos[0];
        pos[1] = nestPos[1]; // αρχικά είναι στη φωλιά του

        previousX = -1;
        previousY = -1; // αρχικές εικονικές τιμές

        carriesFood = false;
    }
    public boolean isAtNest(){
        if(pos[0]==nestPos[0] && pos[1]==nestPos[1]) return true;
        return false;
    }
    public boolean wasAt(int x, int y){
        if(previousX==x && previousY==y) return true;
        return false;
    }
    public boolean isAt(int x, int y){
        if(pos[0]==x && pos[1]==y) return true;
        return false;
    }
    public int getId(){
        return id;
    }
    public int[] getPos(){
        return pos;
    }
    public int[] getNestPos(){
        return nestPos;
    }
    public boolean carries_food(){
        return carriesFood;
    }
    public void move(int time){
        
    }
}
