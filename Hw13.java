/**
* Author: Panayiotis Sotiriou * Written: /10/2026
* Last updated: ../../2026
*/
public class Hw13{

    final static String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    final static String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final static String DIGITS = "0123456789";
    
    final static String SPECIAL = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~\"\\";
    //checks if the password given by the user is using only acceptable chars
    private static boolean isAcceptablePassword(String password){
        boolean found;
        for(int i=0; i<password.length(); i++){
            found= false;
            if(UPPERCASE.indexOf(password.charAt(i))!=-1) found= true;
            if(LOWERCASE.indexOf(password.charAt(i))!=-1) found= true;
            if(DIGITS.indexOf(password.charAt(i))!=-1) found= true;
            if(SPECIAL.indexOf(password.charAt(i))!=-1) found= true;
            if(!found) return false;
        }
        return true;
    }

    private static String generatePassword (int length, boolean useLowercase, boolean useUppercase, boolean useDigits, boolean useSpecialChars){
        //counts how many types of characters the user selected for the password
        int lengthNeeded = 0;
        if(useLowercase) lengthNeeded++;
        if(useUppercase) lengthNeeded++;
        if(useDigits) lengthNeeded++;
        if(useSpecialChars) lengthNeeded++;

        //checks that the user inputs are valid so the password can be created
        if(lengthNeeded == 0){
            return "Length is too short to include all selected character types!";
        }
        if(length < lengthNeeded){
            return "At least one character type must be selected!";
        }

        String password = "";
        String allSelections = "";
        //selects randomly one of each char from the char types the user selected
        //and puts all the chars that were selcted in a string for later use
        if(useLowercase){
            password += LOWERCASE.charAt((int)(LOWERCASE.length()* Math.random()));
            allSelections += LOWERCASE;
        }
        if(useUppercase){
            password += UPPERCASE.charAt((int)(UPPERCASE.length()* Math.random()));
            allSelections += UPPERCASE;
        }
        if(useDigits){
            password += DIGITS.charAt((int)(DIGITS.length()* Math.random()));
            allSelections += DIGITS;
        }
        if(useSpecialChars){
            password += SPECIAL.charAt((int)(SPECIAL.length()* Math.random()));
            allSelections += SPECIAL;
        }

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
    private static String enforceStrongPasswordRequirements (String password){
    
        //checks the password given for unwanted chars
        if(!isAcceptablePassword(password)) return "\n ERROR use acceptable chars only";
        boolean lower=false, upper=false, digits=false, special=false;
        String message="";
        for(int i = 0; i < password.length(); i ++){
            if(password.charAt(i)>='a' && password.charAt(i)<='z') lower=true;
            else if(password.charAt(i)>='A' && password.charAt(i)<='Z') upper=true;
            else if(password.charAt(i)>='0' && password.charAt(i)<='9') digits=true;
            else special=true;
        }
        if(!lower) message+= "\n At least one lowercase letter";
        if(!upper) message+= "\n At least one uppercase letter";
        if(!digits) message+= "\n At least one digit";
        if(!special) message+="\n At least one special character    ";
        if(password.length() < 8) message+= "\n smaller than 8 chars";
        if(message.equals("")) message="\n Your password is strong and meets all requirements!";
        else message="\n Your password is missing: "+message;

        return message;
    }
    //calculates the amount of possible passwords 
    private static long calculatePossibilities (String password){
        //checks the password given for unwanted chars
        if(!isAcceptablePassword(password)){
            StdOut.println("\n ERROR use acceptable chars only");
            return 0;
        }
        boolean lower=false, upper=false, digits=false, special=false;
        int amountOfChars=0;
         for(int i = 0; i < password.length(); i ++){
            if(!lower && password.charAt(i)>='a' && password.charAt(i)<='z'){
                amountOfChars+=LOWERCASE.length();
                lower=true;
            }
            else if(!upper && password.charAt(i)>='A' && password.charAt(i)<='Z'){
                amountOfChars+=UPPERCASE.length();
                upper=true;
            }
            else if(!digits && password.charAt(i)>='0' && password.charAt(i)<='9'){
                amountOfChars+=DIGITS.length();
                digits=true;
            }
            else if(!special){amountOfChars+=SPECIAL.length(); special=true;}
        }
        
        long totalPossibilities = (long)Math.pow(amountOfChars, password.length());

        return totalPossibilities;
    }

    private static double[] estimatePasswordCrackingTime (String password){

        long total_possibilities = calculatePossibilities(password);
        double[] secondsYears=new double[2];
        if(total_possibilities==0) return secondsYears;
        final int guesses_per_second = 1000000;
        secondsYears[0]= (double) total_possibilities / guesses_per_second;
        secondsYears[1]= (double) secondsYears[0] / (60 * 60 * 24 * 365.25);

        return secondsYears;
    }

    private static final String[] WORD_LIST = {
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

    private static String generatePassphrase (int wordCount, String separator, boolean capitalzeEach, boolean appendNumber){
        
        if(wordCount < 3) return "Passphrase must contain at least 3 words";

        String passPhrase = "";
        for(int i = 1; i <= wordCount; i ++){
            String temp = WORD_LIST[(int)(Math.random() * WORD_LIST.length)];
            //from lowercase to uppercase(32 is the difference in the ascii table)
            if(capitalzeEach) temp= (char)(temp.charAt(0)-32) + temp.substring(1);
            passPhrase += temp;
            if(i < wordCount) passPhrase += separator;
        }

        if(appendNumber) passPhrase += separator + (10 + (int)(Math.random() * 90));

        return passPhrase;
    }
    //overloading method
    //allows the user to call a function with preselected values on variables
    private static String generatePassphrase (int wordCount) {
        return generatePassphrase(wordCount, "-", true, true);
    }
    public static void main(String[] args) {
        System.out.println("=== Part 1: Generate Password ===");
        String pwd = generatePassword(12, true, true, true, true);
        System.out.println("Generated (12 chars, all types): " + pwd);
        System.out.println(generatePassword(3, true, true, true, true));
        System.out.println(generatePassword(8, false, false, false, false));
        System.out.println("\n=== Part 2: Enforce Strong Password Requirements ===");
        System.out.println(enforceStrongPasswordRequirements("P@ssw0rd"));
        System.out.println(enforceStrongPasswordRequirements("password"));
        System.out.println("\n=== Part 3: Calculate Password Possibilities ===");
        long possibilities = calculatePossibilities("P@ssw0rd");
        System.out.println("Total possible combinations: " + possibilities);
        System.out.println("\n=== Part 4: Estimate Time to Crack ===");
        double[] times = estimatePasswordCrackingTime("P@ssw0rd");
        System.out.printf("Estimated time to crack: %.6f seconds (~%.8f years)%n", times[0], times[1]);
        //System.out.println("\n=== Part 5: Dictionary Attack ===");
        //Object[] result = dictionaryAttack("password123");
        //if (result[0] != null) {
        //System.out.printf("Password found in %.2f seconds after %d attempts.%n",
        //result[0], result[1]);
        //} else {
        //System.out.printf("Password not found after %d attempts.%n", result[1]);
        //}
        System.out.println("\n=== Part 6: Generate Memorable Passphrase ===");
        // 4 words, hyphen-separated, capitalised, with number (default style)
        System.out.println(generatePassphrase(4));
        // 3 words, space-separated, lowercase, no number
        System.out.println(generatePassphrase(3, " ", false, false));
        // 5 words, underscore-separated, capitalised, with number
        System.out.println(generatePassphrase(5, "_", true, true));
        // Error: too few words
        System.out.println(generatePassphrase(2));
    }
}