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
    if(foodAmount > 0) return true;
    return false;
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
        if(antIds==null) return false;
        return true;
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
    
}
