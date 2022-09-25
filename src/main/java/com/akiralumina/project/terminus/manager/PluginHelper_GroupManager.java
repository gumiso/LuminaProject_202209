package com.akiralumina.project.terminus.manager;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.akiralumina.project.terminus.LuminaCommon.LUMINA_DEFAULT_BAN_SECONDS;
import static com.akiralumina.project.terminus.LuminaCommon.inMiuGrantedList;

@Slf4j
public class PluginHelper_GroupManager {


    //  设置群昵称
    public void groupManager_SetRankName() {

    }

    public void groupManager_SetBanMyself(Bot bot,String rawMessage, long groupID, long userID) {
        //  定义正则表达式 S：秒 M：分 H：时
        String timeRegexS = "(禁言自己(\\d*)秒)";
        String timeRegexM = "(禁言自己(\\d*)分钟)";
        String timeRegexH = "(禁言自己(\\d*)小时)";
        String countRegex = "\\d{1,6}";
        //  编译表达式
        Pattern patternS = Pattern.compile(timeRegexS);
        Pattern patternM = Pattern.compile(timeRegexM);
        Pattern patternH = Pattern.compile(timeRegexH);
        Pattern patternC = Pattern.compile(countRegex);
        //  初始化
        int banTime = 60;
        int totalTime = 0;
        //  开始处理
        Matcher matcherS = patternS.matcher(rawMessage);
        Matcher matcherM = patternM.matcher(rawMessage);
        Matcher matcherH = patternH.matcher(rawMessage);
        if(matcherH.find(0)) {
            banTime = exactTimeFromSTR(patternH, patternC, rawMessage);
            System.out.println("banTime=小时    " + banTime);
            totalTime = banTime * 3600;
        } else if (matcherM.find(0)) {
            banTime = exactTimeFromSTR(patternM, patternC, rawMessage);
            System.out.println("banTime=分钟    " + banTime);
            totalTime = banTime * 60;
        } else if (matcherS.find(0)) {
            banTime = exactTimeFromSTR(patternS, patternC, rawMessage);
            System.out.println("banTime=秒     " + banTime);
            totalTime = banTime;
        } else {
            System.out.println("均不符合，失败");
        }
        if(totalTime != 0) {

            if(inMiuGrantedList(userID)) {
                bot.sendGroupMsg(groupID, "对樱羽代行体之一的权限不够，什么都没有发生", false);
            } else {
                bot.setGroupBan(groupID, userID, totalTime);
                bot.sendGroupMsg(groupID, "你已被禁言"+totalTime+"秒", false);
            }
        } else {
            bot.sendGroupMsg(groupID, "嗯？", false);
        }

    }

    public void groupManager_DisableBan(Bot bot,String rawMessage, long groupID, long userID, String methodType) {

        Pattern serialAtPattern = Pattern.compile("(<at qq=\"([1-9][0-9]{4,11})\"/>)",Pattern.CASE_INSENSITIVE);
        Pattern singleQQPattern = Pattern.compile("[1-9][0-9]{4,11}");
        Matcher serialAtMatcher = serialAtPattern.matcher(rawMessage);

        List<String> userAtList = new ArrayList<>();
        while (serialAtMatcher.find()) {
            userAtList.add(serialAtMatcher.group());
        }
        List<Long> userQQList = new ArrayList<>();
        System.out.println("userQQList: " + userQQList);
        for (String user : userAtList) {
            Matcher singleQQMatcher = singleQQPattern.matcher(user);
            while (singleQQMatcher.find()) {
                userQQList.add(Long.parseLong(singleQQMatcher.group()));
            }
        }
        System.out.println("userQQList: " + userQQList);

        if ("SpecificUserID&AtLuminaBot".equals(methodType)) {
            //  方案1： 指定对方id解除禁言
            boolean isAtLuminaBot = rawMessage.contains("<at qq="+bot.getSelfId()+">");
            boolean atLuminaFirst = userQQList.get(0).equals(bot.getSelfId());
            long selfID = bot.getSelfId();
            for(Long userQQ : userQQList) {
                if (selfID != userQQ) {
                    bot.setGroupBan(groupID, userQQ, 0);
                }
            }
        } else if("JustSpecificUserID".equals(methodType)) {
            //  方案2： 只需要at对方就解除禁言
            bot.setGroupBan(groupID, userQQList.get(1), 0);
        }
        bot.sendGroupMsg(groupID, "樱羽的[净化术]已被启动", false);
    }


    //  用于分离指令控制
    public List<String> orderTool(String patternSTR, String sentence) {
        String pattern0 = "(<at qq=([1-9][0-9]{4,11})>)";
        Pattern pattern = Pattern.compile(patternSTR,Pattern.CASE_INSENSITIVE);
        Matcher mather = pattern.matcher(sentence);
        List<String> stringList = new ArrayList<>();
        while (mather.find()) {
            stringList.add(mather.group());
        }
        return stringList;
    }

    //  处理正则匹配 禁言时间确认
    public String banTool(String mainPattern, String subPattern, String sentence, Bot bot) {



        return "";
    }

    public int exactTimeFromSTR(@NotNull Pattern outerPattern, @NotNull Pattern innerPattern, String waitMatchSTR) {

//        boolean isOuterRegexEmpty = !"".equals(outerPattern) && outerPattern != null;
//        boolean isInnerRegexEmpty = !"".equals(innerPattern) && innerPattern != null;
          Matcher outerMatcher = outerPattern.matcher(waitMatchSTR);
          String matchedSTR,timeCountSTR = null;
          if(outerMatcher.find(0)) {
//              System.out.println("找到分钟");
              matchedSTR = outerMatcher.group();    //  matchedSTR to be the next matcher ousakira
              System.out.println(matchedSTR);
              Matcher innerMatcher = innerPattern.matcher(matchedSTR);
              if(innerMatcher.find(0)) {
                  timeCountSTR = innerMatcher.group();
                  System.out.println(timeCountSTR);
              }

          }
          if("".equals(timeCountSTR) || timeCountSTR == null) {
              return LUMINA_DEFAULT_BAN_SECONDS;
          }else {
              return Integer.parseInt(timeCountSTR);
          }
    }

}
