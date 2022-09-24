package com.akiralumina.project.terminus;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.akiralumina.project.terminus.LuminaCommon.LUMINA_DEFAULT_BAN_SECONDS;

@Slf4j
public class PluginHelper_GroupManager {


    //  设置群昵称
    public void groupManager_SetRankName() {

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
