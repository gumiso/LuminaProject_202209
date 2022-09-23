package com.akiralumina.project.plugins;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest3 {

    private static final String REGEX = "foo";
    private static final String INPUT1 = "fooooooooooooooooo";
    private static final String INPUT2 = "ooooofoooooooooooo";
    private static final String INPUT3 = "ooooofoo";
    private static Pattern pattern;
    private static Matcher matcher1;
    private static Matcher matcher2;
    private static Matcher matcher3;

    public static void main( String[] args ){
        pattern = Pattern.compile(REGEX);
        matcher1 = pattern.matcher(INPUT1);
        matcher2 = pattern.matcher(INPUT2);
        matcher3 = pattern.matcher(INPUT3);

        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT1 is: "+INPUT1);
        System.out.println("Current INPUT2 is: "+INPUT2);
        System.out.println("Current INPUT3 is: "+INPUT3);


        System.out.println("matcher1 lookingAt(): "+matcher1.lookingAt());
        System.out.println("matcher1 matches(): "+matcher1.matches());
        System.out.println("matcher2 lookingAt(): "+matcher2.lookingAt());
        System.out.println("matcher2 matches(): "+matcher2.matches());
        System.out.println("matcher3 lookingAt(): "+matcher3.lookingAt());
        System.out.println("matcher3 matches(): "+matcher3.matches());
    }

}
