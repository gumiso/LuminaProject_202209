package com.akiralumina.project.plugins;

import com.akiralumina.project.terminus.PluginHelper_GroupManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest4 {



    public static void main(String[] args) {
        PluginHelper_GroupManager helper_groupManager = new PluginHelper_GroupManager();
        String timeRegexS = "(禁言(\\d*)秒)";
        String timeRegexM = "(禁言(\\d*)分钟)";
        String timeRegexH = "(禁言(\\d*)小时)";
        String countRegex = "\\d{1,6}";

        Pattern patternS = Pattern.compile(timeRegexS);
        Pattern patternM = Pattern.compile(timeRegexM);
        Pattern patternH = Pattern.compile(timeRegexH);
        Pattern patternC = Pattern.compile(countRegex);

        String str2 = "<at qq=469803465> 禁言120秒60分钟180小时";
        int banTime = 120;
        String banSTR = "s";
//        int banTime = helper_groupManager.exactTimeFromSTR(patternM, patternC, str2);

        Matcher matcherS = patternS.matcher(str2);
        Matcher matcherM = patternM.matcher(str2);
        Matcher matcherH = patternH.matcher(str2);
        if(matcherH.find(0)) {
            banTime = helper_groupManager.exactTimeFromSTR(patternH, patternC, str2);
            System.out.println("banTime=小时    " + banTime);
        } else if (matcherM.find(0)) {
            banTime = helper_groupManager.exactTimeFromSTR(patternM, patternC, str2);
            System.out.println("banTime=分钟    " + banTime);
        } else if (matcherS.find(0)) {
            banTime = helper_groupManager.exactTimeFromSTR(patternS, patternC, str2);
            System.out.println("banTime=秒     " + banTime);
        } else {
            System.out.println("均不符合，失败");
        }



    }

}
