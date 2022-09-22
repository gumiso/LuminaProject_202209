package com.akiralumina.project.plugins;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    public static void main(String[] args) {


        String str = "<at qq=469803465> <at qq=179223207> <at qq=2792556795>";
        String pattern = "<at qq=[1-9][0-9]{4,10}>";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());

    }

}
