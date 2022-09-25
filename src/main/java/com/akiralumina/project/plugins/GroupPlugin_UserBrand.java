package com.akiralumina.project.plugins;

import com.akiralumina.project.terminus.LuminaCommon;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class GroupPlugin_UserBrand extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        System.out.println("[UserBrand] rawMessage: " + event.getRawMessage());
        boolean isAtLuminaPresent = event.getRawMessage().contains("@LuminaBot") || event.getRawMessage().contains("<at qq=\"" + bot.getSelfId() + "\"/>");
        boolean containKeyword = event.getRawMessage().contains("设置头衔");
        String outerBrandPatternRegex = "\\u8bbe\\u7f6e\\u5934\\u8854\\u003c([0-9a-zA-Z\\u4e00-\\u9fa5]{1,6})\\u003e";
        String innerBrandPatternRegex = "\u003c[0-9a-zA-Z\\u4e00-\\u9fa5]{1,6}\u003e";
        if(LuminaCommon.inGroupAllowList(event.getGroupId()) && isAtLuminaPresent && containKeyword) {

            Pattern outerBrandPattern = Pattern.compile(outerBrandPatternRegex);
            Matcher outerMatcher = outerBrandPattern.matcher(event.getRawMessage());

            if(outerMatcher.find()) {
                String outerSTR,innerSTR,trueSTR;
                outerSTR = outerMatcher.group();
                Pattern innerBrandPattern = Pattern.compile(innerBrandPatternRegex);
                Matcher innerMatcher = innerBrandPattern.matcher(outerSTR);
                if(innerMatcher.find()) {
                    innerSTR = innerMatcher.group();
                    trueSTR = innerSTR.substring(1, innerSTR.length() - 1);
                    System.out.println(outerSTR);
                    System.out.println(innerSTR);
                    System.out.println(trueSTR);
                    bot.setGroupSpecialTitle(event.getGroupId(), event.getUserId(), trueSTR, -1);

                    String t = "已将QQ号为[" + event.getUserId() + "]的用户群头衔设置为 <" + trueSTR + ">";

                    bot.sendGroupMsg(event.getGroupId(), t, false);
                }
            }

        }


        return super.onGroupMessage(bot, event);
    }
}
