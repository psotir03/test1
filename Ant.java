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
        return previousX==x && previousY==y;
    }
    public boolean isAt(int x, int y){
        return pos[0]==x && pos[1]==y;
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
        if (!carriesFood) searchFood(time);
       // else goBackNest(time);
    }
    private void searchFood(int time){
        int size= terain.getSize();
        int[] scent= new int[3];
        int[][] scentPos= new int[2][3];
        int maxi=-1, cnt=0, starti=-1, endi=2, startk=-1, endk=2;
        if(pos[0]==0) starti=0;
        if(pos[0]==size-1) endi=1;
        if(pos[1]==0) startk=0;
        if(pos[1]==size-1) endk=1;
        for(int i=starti; i<endi; i++){
            for(int k=startk; k<endk; k++){
                if(!wasAt(pos[0]+i, pos[1]+k) && ((i==0 && k!=0) || (k==0 && i!=0))
                && maxi<terain.getCell(pos[0]+i, pos[1]+k).getScent()){ 
                    scent[cnt]= terain.getCell(pos[0]+i, pos[1]+k).getScent();
                    maxi=scent[cnt];
                    scentPos[0][cnt]= pos[0]+i;
                    scentPos[1][cnt++]= pos[1]+k;
                }
            }
        }
        int maxiCnt=1;
        for(int i=0; i<3; i++)
            if(maxi==scent[i])
                maxiCnt++;
        maxiCnt=(int)(maxiCnt* Math.random());
        cnt=0;
        int[]  maxiPos= new int[2];
        for(int i=0; i<3; i++)
            if(maxi == scent[i] && ++cnt==maxiCnt){
                maxiPos[0]= scentPos[0][i];
                maxiPos[1]= scentPos[1][i];
            }
    
        terain.getCell(maxiPos[0], maxiPos[1]).addAnt(id);
        terain.getCell(pos[0], pos[1]).removeAnt(id);
        previousX= pos[0];
        previousY= pos[1];
        pos[0]= maxiPos[0];
        pos[1]= maxiPos[1];
    }
    public String toString(){
        if(isAtNest() && carries_food())
             return color+""+id+" at "+"("+pos[0]+","+pos[1]+")"+"It has returned to its nest and has food to deliver.";
        if(isAtNest()) 
            return color+""+id+" at "+"("+pos[0]+","+pos[1]+")"+"It is at its nest and does not hold any food.";
        if(!isAtNest() && !carries_food()) 
            return color+""+id+" at "+"("+pos[0]+","+pos[1]+")"+"It is out looking for food.";
        if(!isAtNest() && carries_food()) 
            return color+""+id+" at "+"("+pos[0]+","+pos[1]+")"+"It has found food!";
        //need to find a way to print "It is going back to its nest with food."
        return "";
    }
}