package com.akiralumina.project.plugins;

import com.akiralumina.project.terminus.LuminaCommon;
import com.akiralumina.project.terminus.manager.PluginHelper_GroupManager;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.akiralumina.project.terminus.LuminaCommon.inMiuGrantedList;

@Slf4j
@Component
public class GroupPlugin_BanUserDisable extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        System.out.println("[BanUserDisable] rawMessage: " + event.getRawMessage());
        boolean isAtLuminaPresent = event.getRawMessage().contains("@LuminaBot") || event.getRawMessage().contains("<at qq=\"" + bot.getSelfId() + "\"/>");
        boolean containKeyword = event.getRawMessage().contains("净化");

        String patternSTR = "<at qq=\"[1-9][0-9]{4,11}\"/>.?\\u51c0\\u5316.?<at qq=\"[1-9][0-9]{4,11}\"/>";
        Pattern pattern = Pattern.compile(patternSTR);
        Matcher matcher = pattern.matcher(event.getRawMessage());
        boolean findFormat = matcher.find();

        if(LuminaCommon.inGroupAllowList(event.getGroupId()) && isAtLuminaPresent && findFormat) {
            PluginHelper_GroupManager groupManager = new PluginHelper_GroupManager();
            //  禁言净化模块
            if(inMiuGrantedList(event.getUserId()) && containKeyword) {
                bot.sendGroupMsg(event.getGroupId(), "你是樱羽的代行体之一，你说了算", false);
                groupManager.groupManager_DisableBan(bot, event.getRawMessage(), event.getGroupId(), event.getUserId(), "JustSpecificUserID");
            }else if(containKeyword){
                bot.sendGroupMsg(event.getGroupId(), "未检测到樱羽代行体序列，什么都没有发生", false);
            }

        } else {
            log.info("GroupPlugin_GroupManage 当前群["+event.getGroupId()+"]未获取允许权限或未对机器人进行[@]指定");
        }
        return super.onGroupMessage(bot, event);
    }
}
