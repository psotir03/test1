/**
* Author: Panayiotis Sotiriou * Written: /10/2026
* Last updated: ../../2026
*/
public class Hw1 {
    //counts how many types of characters the user selected for the password
    private static int howManyTypes(int charTypes, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        if(useLowercase) charTypes++;
        if(useUppercase) charTypes++;
        if(useDigits) charTypes++;
        if(useSpecialChars) charTypes++;
        return charTypes;
    }
    //checks that the user inputs are valid so the password can be created
    private static boolean isValid(int charTypes, int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        if(charTypes==0){
             StdOut.println("error nothing was selected");
             return false;
        }
        if(charTypes>length){
            StdOut.println("error the selected length is not enough");
            return false;
        }
        return true;
    }
    //selects randomly one of each char from the char types the user selected
    private static String oneEach(String password, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        if(useLowercase) password+= (char)((int)(Math.random()*26)+97);
        if(useUppercase) password+= (char)((int)(Math.random()*26)+65);
        if(useDigits) password+= (char)((int)(Math.random()*10)+48);
        if(useSpecialChars) password+= (char)((int)(Math.random()*6)+33);
        return password;
    }
    private static String random(String password,int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        //adds all the chars form the char types selected by the user inside a string
        String allSelections="";
        if(useLowercase)
            for(int i=97; i<=122; i++) allSelections+= (char)i;
        if(useUppercase) 
            for(int i=65; i<=90; i++) allSelections+= (char)i;
        if(useDigits)
            for(int i=48; i<=57; i++) allSelections+= (char)i;
        if(useSpecialChars)
            for(int i=33; i<=38; i++) allSelections+= (char)i;
        //randomly fills the selected password length form the string made above
        while(password.length()<length)
            password+= allSelections.charAt((int)(allSelections.length()*Math.random()));
        char[] chars = password.toCharArray(); //make the string into an array of chars
        //shuffle the chars inside the password to random positions
        for (int i = chars.length - 1; i > 0; i--) {
            int j= (int)((i+1)*Math.random());
            char temp = chars[j];
            chars[j] = chars[i];
            chars[i] = temp;
        }
        password = new String(chars); //makes it a string again
        return password;
    }
    //checks how strong the password is and tells the user what it is missing
    private static void isPasswordStrong(String password, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        if(useLowercase && useUppercase && useDigits && useSpecialChars && password.length()>=8) StdOut.println("the password is strong");
        else{
            StdOut.println("the password is not strong enough, it needs: ");
            if(password.length()<8) StdOut.println("at least 8 chars");
            if(!useLowercase) StdOut.println("lowercase");
            if(!useUppercase) StdOut.println("uppercase");
            if(!useDigits) StdOut.println("digits");
            if(!useSpecialChars) StdOut.println("special chars");
        }
    }
    //calculates the amount of possible passwords 
    private static double calculatePossibilities(int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        double charSetSize=0;
        if(useLowercase) charSetSize+=26;
        if(useUppercase) charSetSize+=26;
        if(useDigits) charSetSize+=10;
        if(useSpecialChars) charSetSize+=6;
        StdOut.println(Math.pow(charSetSize, length));
        return Math.pow(charSetSize, length);
    }
    //estimates how much time on average the code will need to be cracked
    private static double[] estimatedCrackingTime(double totalPossibilities){
        final double quessesPerSec=1000000;
        double[] secAndYear= new double[2];
        secAndYear[0]=totalPossibilities/quessesPerSec; //seconds
        secAndYear[1]=secAndYear[0]/(60*60*24*365.25);  //years
        return secAndYear;
    }
    //the words given to use for the passphrase
    private static final String[] wordList = {
        "apple","brave","cloud","dance","eagle","flame","grace","house",
        "ivory","joker","kneel","lemon","mango","noble","ocean","piano",
        "queen","river","stone","tiger","ultra","vivid","waltz","xenon",
        "yacht","zebra","amber","blaze","crisp","drift","ember","frost",
        "gleam","haven","inlet","jewel","karma","lunar","maple","north",
        "orbit","pearl","quill","ridge","solar","torch","unity","vapor",
        "wheat","yield","acorn","birch","cedar","daisy","finch","glade",
        "holly","iris","junco","kelp","lilac","moose","newt","otter",
        "poppy","quail","robin","swift","tulip","vixen","wren","algae",
        "brook","clove","dunes","fjord","grove","heath","isle","jade",
        "knoll","marsh","opal","pine","quartz","reef","sage","thorn",
        "vale","willow","bison","crane","dove","egret","falcon","gecko",
        "heron","ibis","jaguar","kite","lynx","mink","raven","stoat",
        "tapir","vole","wombat","yak","antler","burrow","canopy","delta",
        "flint","gorge","harbor","jungle","lagoon","meadow","nimbus",
        "oasis","plateau","quasar","ravine","summit","tundra","valley",
        "abyss","basin","caldera","dune","fen","geyser","hammock","jetty",
        "levee","moraine","outcrop","quicksand","rift","scree","talus",
        "volcano","beacon","compass","dynamo","engine","forge","hammer",
        "iron","lantern","magnet","needle","piston","relay","signal",
        "timber","valve","winch","alloy","bolt","cable","diesel","filter",
        "gasket","hinge","impact","joint","kernel","lathe","module",
        "nozzle","pulley","rivet","spring","torque","weld","anchor",
        "brace","clasp","dowel","elbow","flange","gauge","hook","joist",
        "latch","mount","notch","pivot","ratchet","shim","zephyr","ash"
    };
    private static String generatePassphrase(String[] wordList){
        boolean capitalizeEach, appendNumber;
        int wordCnt;
        String seperator;
        do{
            StdOut.print("word count: ");
            wordCnt= StdIn.readInt();
        }while(wordCnt<=0);    // makes sure the user gave a valid amount of words
        StdOut.print("word seperator: ");
        seperator= StdIn.readString();
        StdOut.println("capitalize each?, append number?");
        capitalizeEach= StdIn.readBoolean();
        appendNumber= StdIn.readBoolean();
        String passPhrase= "", temp= "";
        for(int i=0; i<wordCnt; i++){
            temp= wordList[(int)(wordList.length * Math.random())];
            if(appendNumber) temp+= (int)(10 + 90 * Math.random()); //produces a random number between 10 and 99
            if(capitalizeEach) temp= (char)(temp.charAt(0)-32) + temp.substring(1); //makes capital letters to lower case
            if (i!=wordCnt-1) temp+=seperator; //the seperator is not used at the end
            passPhrase+=temp;
        }
        return passPhrase;
    }
    public static void main(String[] args) {
        String password="";
        boolean useLowercase, useUppercase, useDigits, useSpecialChars;
        int length;
        do{
            StdOut.print("give the length: ");
            length= StdIn.readInt();
        }while(length<=0); //only accept the length if over 0
        StdOut.println("What do you want your password to contain: ");
        StdOut.println("Lowercase?, Uppercase?, Digits?, Special Characters?: ");
        useLowercase= StdIn.readBoolean();
        useUppercase= StdIn.readBoolean();
        useDigits= StdIn.readBoolean();
        useSpecialChars= StdIn.readBoolean();
        int charTypes=0;
        charTypes=howManyTypes(charTypes, useLowercase, useUppercase, useDigits, useSpecialChars);
        // only continue with rest of the program if the inputs are valid
        if(isValid(charTypes, length, useLowercase, useUppercase, useDigits, useSpecialChars)){
            password=oneEach(password, useLowercase, useUppercase, useDigits, useSpecialChars);
            password=random(password, length, useLowercase, useUppercase, useDigits, useSpecialChars);
            StdOut.println(password);
            isPasswordStrong(password, useLowercase, useUppercase, useDigits, useSpecialChars);
            double totalPossibilities=calculatePossibilities(length, useLowercase, useUppercase, useDigits, useSpecialChars);
            double[] resultSecAndYear=estimatedCrackingTime(totalPossibilities);
            StdOut.println("time in seconds: "+resultSecAndYear[0]);
            StdOut.println("time in years: "+resultSecAndYear[1]);
            String passPhrase;
            passPhrase=generatePassphrase(wordList);
            StdOut.println("the pass phrase is: "+ passPhrase);
        }
    }
}
