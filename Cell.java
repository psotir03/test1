public class Cell {

    private boolean hasNest; // κατά πόσο έχει πάνω του φωλιά
    private int foodAmount; // πόσους σπόρους έχει επί του παρόντος
    private int scentAmount; // το πλήθος των μονάδων μυρωδιάς
                              // με τις οποίες έχει χριστεί και που δεν έχουν εξατμιστεί
    private int[] times; // τα χρονικά σημεία κατά τα οποία χρίστηκε με τις
                         // παραπάνω μονάδες μυρωδιάς
    private int[] antIds; // οι ταυτότητες των μυρμηγκιών που είναι στο σημείο αυτό

    public Cell() {

        hasNest = false;
        foodAmount = 0;
        scentAmount = 0;
        times = null;
        antIds = null;
    }
    public boolean hasFood(){
        return foodAmount > 0;
    }
    public void getFood(){
        foodAmount--;
    }
    public  void putFood(){
        foodAmount++;
    }
    public void putFood(int food){
        foodAmount+=food;
    }
    public boolean hasAnts(){
        return antIds!= null;
    }
    public void putNest(){
        hasNest=true;
    }
    public boolean withNest(){
        return hasNest;
    }
    public int getScent(){
        return scentAmount;
    }
    public void addAnt(int antID){
        if(antIds==null){
            antIds= new int[1];
            antIds[0]=antID;
        }
        int[] temp= antIds;
        antIds= new int[temp.length+1];
        for(int i=0; i<temp.length; i++)
            antIds[i]=temp[i];
        antIds[temp.length]=antID;
    }
    public void removeAnt(int antID){
        int[] temp= antIds;
        antIds= new int[temp.length-1];
        int cnt=0;
        for(int i=0; i<temp.length; i++){
            if(antID!=temp[i])
                antIds[cnt++]= temp[0];
        }
    }
    public void addScent(int time){
        scentAmount++;
        int[] temp=times;
        times= new int[temp.length+1];
        for(int i=0; i<temp.length; i++)
            times[i]=temp[i];
        times[temp.length]= time;
    }
    public void updateScent(int curTime, int elapsed){
        int cnt=0;
        for(int i=0 ; i<times.length; i++){
            if(curTime-times[i] < elapsed) cnt++;
        }
        int[] temp= times;
        times= new int[cnt];
        cnt=0;
        for(int i=0; i<times.length; i++){
            if(curTime-temp[i] < elapsed)
                times[cnt++]= temp[i];
        }
    }
    public String toString(){
            String text="";
            if(hasNest) text+="Cell that has a nest.\n";
            else text+="Cell that does not have a nest.\n";
            if(foodAmount>0) text+="- There are "+foodAmount+"seeds.\n";
            else text+="- There are no seeds.\n";
            if(scentAmount>0) text+= "- Currently it is annotated with "+scentAmount+" units of scent.\n";
            text+="- At present there are"+1+"ants on it.\n"; //fix  it
            return text;
    }
}