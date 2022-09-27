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
        //  outer和inner两种仅适用于对“设置头衔”的发起人进行设置头衔
        String outerBrandPatternRegex = "<at qq=\"([1-9][0-9]{4,11})\"/>\\s{0,5}\\u8bbe\\u7f6e\\u5934\\u8854\\s{0,5}\u003c([0-9a-zA-Z\\u4e00-\\u9fa5\\u30A0-\\u30FF\\u3040-\\u309F\\u31F0-\\u31FF]{1,6})\\u003e";
        String innerBrandPatternRegex = "\u003c[0-9a-zA-Z\\u4e00-\\u9fa5\\u30A0-\\u30FF\\u3040-\\u309F\\u31F0-\\u31FF]{1,6}\u003e";
        //  适用于受信任的管理员对他人账号设置头衔
        String getSelfBrandSetRegex = "<at qq=\"([1-9][0-9]{4,11})\"/>\\s{0,5}\\u8bbe\\u7f6e\\u5934\\u8854\\s{0,5}<[0-9a-zA-Z\\u4e00-\\u9fa5\\u30A0-\\u30FF\\u3040-\\u309F\\u31F0-\\u31FF]{1,6}>\\s{0,5}<at qq=\"([1-9][0-9]{4,11})\"/>";
        if(LuminaCommon.inGroupAllowList(event.getGroupId()) && isAtLuminaPresent && containKeyword) {
            //  初始化Pattern和Matcher
            Pattern outerBrandPattern = Pattern.compile(outerBrandPatternRegex);
            Pattern getSelfBrandSetPattern = Pattern.compile(getSelfBrandSetRegex);
            Matcher outerBrandMatcher = outerBrandPattern.matcher(event.getRawMessage());
            Matcher getSelfBrandSetMatcher = getSelfBrandSetPattern.matcher(event.getRawMessage());
            //  对设置请求发起人鉴定
            if(getSelfBrandSetMatcher.find()) {
                //  给别人头衔
                String outerSTR, innerSTR, trueSTR, userIDOuterSTR, userIDInnerSTR, userIDSTR;
                long userID = 0;
                outerSTR = getSelfBrandSetMatcher.group();
                Pattern innerBrandPattern = Pattern.compile(innerBrandPatternRegex);
                Pattern atUserIDOuterPattern = Pattern.compile("\\u8bbe\\u7f6e\\u5934\\u8854\\s{0,5}\\u003c([0-9a-zA-Z\\u4e00-\\u9fa5\\u30A0-\\u30FF\\u3040-\\u309F\\u31F0-\\u31FF]{1,6})\\u003e\\s{0,5}<at qq=\"([1-9][0-9]{4,11})\"/>");
                Pattern atUserIDInnerPattern = Pattern.compile("<at qq=\"([1-9][0-9]{4,11})\"/>");
                Pattern userIDPattern = Pattern.compile("[1-9][0-9]{4,11}");
                Matcher innerBrandMatcher = innerBrandPattern.matcher(outerSTR);
                Matcher atUserIDOuterMatcher = atUserIDOuterPattern.matcher(outerSTR);
                if(innerBrandMatcher.find() && atUserIDOuterMatcher.find()) {
                    //  提取群头衔字段 trueSTR
                    innerSTR = innerBrandMatcher.group();
                    trueSTR = innerSTR.substring(1, innerSTR.length() - 1);
                    System.out.println("他人头衔[outerSTR]= " + outerSTR);
                    System.out.println("他人头衔[innerSTR]= " + innerSTR);
                    System.out.println("他人头衔[trueSTR]=" + trueSTR);
                    //  提取目标设定人字段 userID
                    userIDOuterSTR = atUserIDOuterMatcher.group();
                    Matcher atUserIDInnerMatcher = atUserIDInnerPattern.matcher(userIDOuterSTR);
                    if(atUserIDInnerMatcher.find()) {
                        userIDInnerSTR = atUserIDInnerMatcher.group();
                        Matcher userIDMatcher = userIDPattern.matcher(userIDInnerSTR);
                        if(userIDMatcher.find()) {
                            userIDSTR = userIDMatcher.group();
                            System.out.println("userIDSTR: " + userIDSTR);
                            userID = Long.parseLong(userIDSTR);
                        }
                    }
                    bot.setGroupSpecialTitle(event.getGroupId(), userID, trueSTR, -1);
                    bot.sendGroupMsg(event.getGroupId(), "已将QQ号为[" + userID + "]的用户群头衔设置为 <" + trueSTR + ">", false);
                }
            } else if(outerBrandMatcher.find()) {
                //  给自己头衔
                String outerSTR,innerSTR,trueSTR;
                outerSTR = outerBrandMatcher.group();
                Pattern innerBrandPattern = Pattern.compile(innerBrandPatternRegex);
                Matcher innerBrandMatcher = innerBrandPattern.matcher(outerSTR);
                if(innerBrandMatcher.find()) {
                    innerSTR = innerBrandMatcher.group();
                    trueSTR = innerSTR.substring(1, innerSTR.length() - 1);
                    System.out.println("自助头衔[outerSTR]= " + outerSTR);
                    System.out.println("自助头衔[innerSTR]= " + innerSTR);
                    System.out.println("自助头衔[trueSTR]= " + trueSTR);
                    bot.setGroupSpecialTitle(event.getGroupId(), event.getUserId(), trueSTR, -1);
//                    String t = "已将QQ号为[" + event.getUserId() + "]的用户群头衔设置为 <" + trueSTR + ">";
                    bot.sendGroupMsg(event.getGroupId(), "已将QQ号为[" + event.getUserId() + "]的用户群头衔设置为 <" + trueSTR + ">", false);
                }
            }
        }
        return super.onGroupMessage(bot, event);
    }
}
