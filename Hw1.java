public class Hw1 {
    private static int howManyTypes(int charTypes, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        if(useLowercase) charTypes++;
        if(useUppercase) charTypes++;
        if(useDigits) charTypes++;
        if(useSpecialChars) charTypes++;
        return charTypes;
    }
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
    private static String oneEach(String password, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        if(useLowercase) password+= (char)((int)(Math.random()*26)+97);
        if(useUppercase) password+= (char)((int)(Math.random()*26)+65);
        if(useDigits) password+= (char)((int)(Math.random()*10)+48);
        if(useSpecialChars) password+= (char)((int)(Math.random()*6)+33);
        return password;
    }
    private static String random(String password,int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        String allSelections="";
        if(useLowercase)
            for(int i=97; i<=122; i++) allSelections+= (char)i;
        if(useUppercase) 
            for(int i=65; i<=90; i++) allSelections+= (char)i;
        if(useDigits)
            for(int i=48; i<=57; i++) allSelections+= (char)i;
        if(useSpecialChars)
            for(int i=33; i<=38; i++) allSelections+= (char)i;
        while(password.length()<length)
            password+= allSelections.charAt((int)(allSelections.length()*Math.random()));
        char[] chars = password.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j= (int)((i+1)*Math.random());
            char temp = chars[j];
            chars[j] = chars[i];
            chars[i] = temp;
        }
        password = new String(chars);
        return password;
    }
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
    private static double calculatePossibilities(int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        double charSetSize=0;
        if(useLowercase) charSetSize+=26;
        if(useUppercase) charSetSize+=26;
        if(useDigits) charSetSize+=10;
        if(useSpecialChars) charSetSize+=6;
        StdOut.println(Math.pow(charSetSize, length));
        return Math.pow(charSetSize, length);
    }
    private static double[] estimatedCrackingTime(double totalPossibilities){
        final double quessesPerSec=1000000;
        double[] secAndYear= new double[2];
        secAndYear[0]=totalPossibilities/quessesPerSec;
        secAndYear[1]=secAndYear[0]/(60*60*24*365.25);
        return secAndYear;
    }
    public static void main(String[] args) {
        String password="";
        boolean useLowercase, useUppercase, useDigits, useSpecialChars;
        int length;
        StdOut.print("give the length: ");
        length= StdIn.readInt();
        StdOut.println("What do you want your password to contain: ");
        StdOut.println("Lowercase?, Uppercase?, Digits?, Special Characters?: ");
        useLowercase= StdIn.readBoolean();
        useUppercase= StdIn.readBoolean();
        useDigits= StdIn.readBoolean();
        useSpecialChars= StdIn.readBoolean();
        int charTypes=0;
        charTypes=howManyTypes(charTypes, useLowercase, useUppercase, useDigits, useSpecialChars);
        if(isValid(charTypes, length, useLowercase, useUppercase, useDigits, useSpecialChars)){
            password=oneEach(password, useLowercase, useUppercase, useDigits, useSpecialChars);
            password=random(password, length, useLowercase, useUppercase, useDigits, useSpecialChars);
            StdOut.println(password);
            isPasswordStrong(password, useLowercase, useUppercase, useDigits, useSpecialChars);
            double totalPossibilities=calculatePossibilities(length, useLowercase, useUppercase, useDigits, useSpecialChars);
            double[] resultSecAndYear=estimatedCrackingTime(totalPossibilities);
            StdOut.println("time in seconds: "+resultSecAndYear[0]);
            StdOut.println("time in years: "+resultSecAndYear[1]);
        }
    }
}
