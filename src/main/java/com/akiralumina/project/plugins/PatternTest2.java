package com.akiralumina.project.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest2 {



//    String timeRegexS = "禁言\\d秒";
//    String timeRegexM = "禁言\\d*分钟";
//    String timeRegexH = "禁言\\d*小时";

//

//    boolean isMatchS = Pattern.matches(timeRegexS, str1);

    public static void main(String[] args) {

        String str1 = "<at qq=469803465> 禁言60秒";
        String str2 = "<at qq=469803465> 禁言60分钟";
        String str3 = "<at qq=469803465> 禁言60小时";
        String str4 = "<at qq=469803465> 禁言60秒";
        String str5 = "<at qq=469803465> 禁言60秒";
        String str6 = "<at qq=469803465> 禁言60秒";

        String timeRegexS = "(禁言(\\d*)秒)";
        String timeRegexM = "(禁言(\\d*)分钟)";
        String timeRegexH = "(禁言(\\d*)小时)";
        String countRegex = "\\d{1,6}";

        Pattern patternS = Pattern.compile(timeRegexS);
        Pattern patternM = Pattern.compile(timeRegexM);
        Pattern patternH = Pattern.compile(timeRegexH);
        Pattern patternC = Pattern.compile(countRegex);

        List<String> sentenceList = new ArrayList<>();

        System.out.println("-------------------------------------------------------");

        String waitMatchSTR = str2;
        // if匹配到禁言60秒，则进一步匹配数字部分

        String banSTR = new String();
        String timeSTR = new String();

        Matcher matcherS = patternS.matcher(waitMatchSTR);
        if(matcherS.find()) {
            System.out.println("找到秒");

        }
        Matcher matcherM = patternM.matcher(waitMatchSTR);
        if(matcherM.find(0)) {
            System.out.println("找到分钟");
            banSTR = matcherM.group();      //  BANSTR成为下一次待匹配语句
            System.out.println(banSTR);
            Matcher matcherC = patternC.matcher(banSTR);
//            timeSTR = matcherC.find() ? matcherC.group(): "error";
            if(matcherC.find()) {
                timeSTR = matcherC.group();
            }
            System.out.println(timeSTR);
        }
        Matcher matcherH = patternH.matcher(waitMatchSTR);
        if(matcherH.find()) {
            System.out.println("找到小时");

        }


    }
}
